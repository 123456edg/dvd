package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.OrderMapper;
import com.company.project.model.Goods;
import com.company.project.model.Order;
import com.company.project.model.OrderDetails;
import com.company.project.model.User;
import com.company.project.service.*;
import com.company.project.core.AbstractService;
import com.company.project.utils.DigitUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



@Service
@Transactional
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailsService orderDetailsService;

    @Resource
    private UserService userService;

    @Resource
    private MomentCommentService momentCommentService;

    @Resource
    private GoodsService goodsService;

    @Override
    public Result add(Order order) {

        User user = userService.selectUserById(order.getCreatedBy()); //获取订单创建者的对象（即用户）
        if (null == user){
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
        }

        if (-1 == user.getBalance().compareTo(order.getTotalMoney())){
            return ResultGenerator.genFailResult(ResultCode.USER_MONEY_INSUFFICIEN,"用户余额不足，请联系管理员充值");
        }

        Goods goods = goodsService.findById(order.getOrderDetailsList().get(0).getGoodsId()); //根据参数中订单详情表中的商品id找到对应商品对象
        if (null == goods){
            return ResultGenerator.genFailResult(ResultCode.GOODS_NULL_ERROR,"影碟不存在，请重新下单");
        }

        if (null == goods.getRepertory()){
            return ResultGenerator.genFailResult(ResultCode.GOODS_REPERTORY_ERROR,"影碟库存不足，请联系管理员补货");
        }

        int count = orderDetailsService.getCountByGoodsId(order.getOrderDetailsList().get(0).getGoodsId());
        if (goods.getRepertory().intValue() < count){
            return ResultGenerator.genFailResult(ResultCode.GOODS_REPERTORY_ERROR,"影碟库存不足，请联系管理员补货");
        }
        //按照算法生成订单号
        order.setId(DigitUtil.generatorLongId());

        //批量添加订单详情表
        if (order.getOrderDetailsList().size() > 0){
            for (OrderDetails d: order.getOrderDetailsList()) {
                d.setOrderId(order.getId());
                d.setCreatedAt(new Date());
                d.setIsDelete(false);
            } //把影碟id 天数 每日租金（忽略）保存进订单详情表
            orderDetailsService.save(order.getOrderDetailsList());  //save 即 insert
        }
      //设置订单表属性
        order.setCreatedAt(new Date());
        order.setIsDelete(false);
        order.setStatus(1); //初始状态值为 1
        save(order);

        //下单成功库存-1
        goods.setRepertory(goods.getRepertory().subtract(BigDecimal.valueOf(1)));
        goodsService.update(goods);

        //减去用户余额
        user.setBalance(user.getBalance().subtract(order.getTotalMoney()));
        userService.update(user);

        Result result= ResultGenerator.genSuccessResult();
        result.setData(order);
        return result;
    }

    @Override
    public Result list(Order order) {

        User user = userService.selectUserById(order.getCreatedBy());
        if (user != null){
            if (0 == user.getRole()){
                order.setCreatedBy(null); //管理员可查看所有订单
            }
        }

        PageHelper.startPage(order.getPage(), order.getLimit());
        order.setIsDelete(false);
        List<Order> list = orderMapper.list(order);
        for (Order d: list) {
            d.setOrderDetailsList(orderDetailsService.selectByOrderId(d.getId()));
            d.setUser(userService.selectUserById(d.getCreatedBy()));
            d.setMomentCommentList(momentCommentService.selectByOrderId(d.getId()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result detail(Long id) {
        Order order = orderMapper.detail(id);
        if (null != order){
            order.setOrderDetailsList(orderDetailsService.selectByOrderId(order.getId()));
            order.setUser(userService.selectUserById(order.getCreatedBy()));
            order.setMomentCommentList(momentCommentService.selectByOrderId(order.getId()));
        }
        return ResultGenerator.genSuccessResult(order);
    }

    @Override //发货或者归还都执行此方法 （发货时再插入订单状态：2 和物流编号）
    public Result updateGoods(Order order) {

        Goods goods = goodsService.findById(orderDetailsService.selectByOrderId(order.getId()).get(0).getGoodsId());
        if (null == goods){
            return ResultGenerator.genFailResult(ResultCode.GOODS_NULL_ERROR,"影碟不存在，请重新归还");
        }

        order.setUpdatedAt(new Date());
        update(order);

        //如果归还成功，影碟库存+1
        if (7 == order.getStatus()){
            
            goods.setRepertory(goods.getRepertory().add(BigDecimal.valueOf(1)));
            goodsService.update(goods);
        }

        Result result= ResultGenerator.genSuccessResult();
        result.setData(order);
        return result;
    }

}
