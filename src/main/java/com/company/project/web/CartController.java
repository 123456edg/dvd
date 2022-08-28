package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Cart;
import com.company.project.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
* Created by CodeGenerator on 2021/03/21.
*/
@RestController
@RequestMapping("/cart")
@Api(tags = {"/cart"}, description = "购物车管理模块")
public class CartController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Resource
    private CartService cartService;

    @ApiOperation(value = "新增购物车", notes = "新增购物车")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Cart cart) {
        cart.setCreatedAt(new Date());
        cart.setCreatedBy(super.getUserId());  //获取用户id
        cart.setIsDelete(false);
        cartService.save(cart);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(cart);
        return result;
    }

    @ApiOperation(value = "删除购物车", notes = "删除购物车")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        Cart cart=new Cart();
        cart.setId(id);
        cart.setIsDelete(true);
        cartService.update(cart);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改购物车", notes = "修改购物车")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody Cart cart) {
        cart.setUpdatedAt(new Date());
        cartService.update(cart);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(cart);
        return result;
    }

    @ApiOperation(value = "获取购物车单个详情", notes = "获取购物车单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        Cart cart = cartService.findById(id);
        return ResultGenerator.genSuccessResult(cart);
    }

    @ApiOperation(value = "分页查询购物车", notes = "分页查询购物车")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Cart cart) {
        String id = super.getUserId();
        logger.info(id);
        cart.setCreatedBy(id);
        return cartService.list(cart);
    }

    @ApiOperation(value = "批量删除购物车", notes = "批量删除购物车")
    @RequestMapping(value = "/batchDelete", method = {RequestMethod.POST})
    public Result batchDelete(@RequestBody() Cart cart) {
        return cartService.batchDelete(cart);
    }

}
