package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.service.SysMenuService;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;
import com.company.project.utils.*;
import com.company.project.vo.CaptchaVo;
import com.company.project.vo.LoginVo;
import com.company.project.vo.SysUserVo;
import com.company.project.vo.VerfiyCodeVo;
import com.wf.captcha.GifCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;



@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MagConfig magConfig;

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public Result logout(Long userId) {
        SysUserVo sysUserVo = null;
        String token=(String)redisService.get(userId+"USERID");
        try {
            sysUserVo = (SysUserVo)redisService.get(Constants.REDIS_KEY_LOGIN + token);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("redis异常");
        }

        redisService.delete(userId+"USERID");

        if (sysUserVo != null){
            redisService.delete(Constants.REDIS_KEY_LOGIN + token);

            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(ResultCode.NOT_LOGIN_EXCEPTION,"用户未登录,请重新登录");
    }

    @Override
    public Result login(LoginVo vo) {
        readWriteLock.writeLock().lock();

        try {
            //用户登录
            return userLogin(vo);
        } catch (Exception e){
            e.printStackTrace();
            Logger.info(this,"登录错误：" + e.getMessage());
        }finally {
            if(readWriteLock.isWriteLocked()){
                readWriteLock.writeLock().unlock();
            }
        }
        return ResultGenerator.genFailResult(ResultCode.USER_LOGIN_ERROR,"登录错误，请联系管理员");
    }

    @Override
    public Result captcha() {
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        System.out.print("登录验证码" + verCode);
        String verifyToken = TokenUtil.getToken();
        // 存入redis并设置过期时间为30秒
        redisService.setWithExpire(Constants.REDIS_KEY_VERFIY + verifyToken, new VerfiyCodeVo(verCode,System.currentTimeMillis() + Constants.verifyCodeForTempValidTime)  , Constants.verifyCodeForTempValidTime);
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setVerifyToken(verifyToken);
        captchaVo.setData(specCaptcha.toBase64());
        // 将key和base64返回给前端
        return ResultGenerator.genSuccessResult(captchaVo);
    }

    @Override
    public List<User> findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public User selectUserById(String createdBy) {
        return userMapper.selectUserById(createdBy);
    }

    @Override
    public Result updateMoneyById(User user) {

        //根据用户ID查询是否存在
        User newUser = userMapper.selectUserById(String.valueOf(user.getId()));

        if (null == newUser){
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
        }

        newUser.setBalance(newUser.getBalance().add(user.getTotalMoney()));
        newUser.setUpdatedAt(new Date());
        update(newUser);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

    @Override
    public Result updateUser(User user) {

        //根据用户名查询是否存在
        User newUser = userMapper.findUserByUserName(user.getUserName(),user.getId());

        //用户名不可重复
        if (null != newUser){
            return ResultGenerator.genFailResult(ResultCode.USER_ALREADY_EXIST,"用户名已存在，请登录");
        }

        user.setUpdatedAt(new Date());
        update(user);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

    @Override
    public Result add(User user) {

        User newUser = userMapper.findUserByUserName(user.getUserName(),null);

        if (null != newUser){
            return ResultGenerator.genFailResult(ResultCode.USER_ALREADY_EXIST,"用户名已存在，请登录");
        }

        user.setCreatedAt(new Date());
        user.setIsDelete(false);
        user.setBalance(BigDecimal.valueOf(1000));  //用户钱包初始余额为1000元
        save(user);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

    /**
     * 用户登录
     * @param vo
     * @return
     */
    public Result userLogin(LoginVo vo){

        SysUserVo sysUserVo = new SysUserVo();

        User user = new User();

        user = userMapper.findUserByUserName(vo.getUserName(),null);

        if (null == user){
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
        }

        if(!vo.getPassword().equals(user.getPassword())){
            return ResultGenerator.genFailResult(ResultCode.PASSWORD_ERROR,"密码输入错误，请重新输入");
        }

        List<Object> sysMenuList = new ArrayList<Object>();
        if (null != user.getRole()){ //不同的角色对应不同的菜单
            sysMenuList = sysMenuService.selectMenuByRoleId(user.getRole());
        }

        //获取登录用户之前存入的token
        String token= (String) redisService.get(user.getId() + "USERID");

        Boolean loginFlag = false;
        //如果为空，说明用户刚注册后初次登录
        if(StringUtils.isNotBlank(token)){
            //如果用户已有token，则删除存入redis的token(用户在客户端退出，删除了localStorage的token)
            redisService.delete(Constants.REDIS_KEY_LOGIN + token);
            redisService.delete(user.getId()+"USERID");
        }else{
            //没有token的用户 loginflag为 true
            loginFlag=true;
        }
        //为用户生成新的token(本来就没有token或者token刚被清除)
        token = TokenUtil.getToken();

        try {
            sysUserVo.setUserId(user.getId());
            sysUserVo.setPhone(user.getPhone());
            sysUserVo.setEmail(user.getEmail());
            sysUserVo.setToken(token);
            //设置该token的过期时间点
            sysUserVo.setTokenExpireTime(System.currentTimeMillis() + magConfig.getExpireTime());
            sysUserVo.setUserName(user.getUserName());
            sysUserVo.setSysMenuList(sysMenuList);
            sysUserVo.setRoleId(user.getRole().toString());
            //redisService.put(Constant.REDIS_KEY_LOGIN, token, new RedisModel(su.getId(), System.currentTimeMillis() + magConfig.getExpireTime()), magConfig.getExpireTime());
            redisService.setWithExpire(Constants.REDIS_KEY_LOGIN + token, sysUserVo , 43200000L); //以毫秒计（86400000为一天）
            redisService.set(user.getId()+"USERID",token);
        }catch (Exception e){
            e.printStackTrace();
            Logger.info(this,"登录token存入redis产生异常："+e.getMessage());
            throw new RuntimeException("存入redis异常");
        }
        return ResultGenerator.genSuccessResult(sysUserVo);
    }

}
