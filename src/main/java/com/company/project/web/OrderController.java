package com.company.project.web;

import com.company.project.common.BaseController;
import com.company.project.common.PageParam;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Order;
import com.company.project.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
* Created by CodeGenerator on 2021/03/21.
*/
@RestController
@RequestMapping("/order")
@Api(tags = {"/order"}, description = "订单管理模块")
public class OrderController extends BaseController {
    @Resource
    private OrderService orderService;

    @ApiOperation(value = "下单", notes = "下单")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Order order) {
        order.setCreatedBy(super.getUserId());
        return orderService.add(order);
    }

    @ApiOperation(value = "删除订单", notes = "删除订单")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) { //参数为订单号
        Order order=new Order();
        order.setId(id);
        order.setIsDelete(true);
        orderService.update(order);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "归还影碟", notes = "归还影碟")
    @RequestMapping(value = "/updateGoods", method = {RequestMethod.POST})
    public Result updateGoods(@RequestBody Order order) {
        return orderService.updateGoods(order);
    }

    @ApiOperation(value = "获取订单单个详情", notes = "获取订单单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        return orderService.detail(id);
    }

    @ApiOperation(value = "分页查询订单", notes = "分页查询订单")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Order order) {
        order.setCreatedBy(super.getUserId());
        return orderService.list(order);
    }
}
/*  @RequestParam 获取请求参数 @RequestBody 获取请求体    */
