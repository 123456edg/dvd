package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.utils.Logger;
import com.company.project.vo.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2021/03/03.
*/
@RestController
@RequestMapping("/user")
@Api(tags = {"/user"}, description = "用户管理模块")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public Result logout(@RequestParam Long userId) {
        Logger.info(this,"/user/logout 用户退出接口入参 : " + userId);
        return userService.logout(userId);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Result login(@RequestBody LoginVo vo, HttpServletRequest request) {
        Logger.info(this, "/user/login 用户登录接口入参 :" + vo);
        return userService.login(vo);
    }

    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @RequestMapping(value = "/captcha", method = {RequestMethod.GET})
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return userService.captcha();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody User user) {
        return userService.add(user);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        User user=new User();
        user.setId(id);
        user.setIsDelete(true);
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result updateUser(@RequestBody User user) {
        return  userService.updateUser(user);
    }

    @ApiOperation(value = "修改用户金额", notes = "修改用户金额")
    @RequestMapping(value = "/updateMoneyById", method = {RequestMethod.POST})
    public Result updateMoneyById(@RequestBody User user) {
        return userService.updateMoneyById(user);
    }

    @ApiOperation(value = "获取用户单个详情", notes = "获取用户单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) User user) {
        PageHelper.startPage(user.getPage(), user.getLimit());
        user.setIsDelete(false);
        List<User> list = userService.findByModel(user);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
