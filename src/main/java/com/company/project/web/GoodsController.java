package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.model.Goods;
import com.company.project.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
* Created by CodeGenerator on 2021/03/21.
*/
@RestController
@RequestMapping("/goods")
@Api(tags = {"/goods"}, description = "影碟商品管理模块")  //Api标记一个Controller类作为swagger文档资源，以及该Controller的描述
public class GoodsController {
    
    private final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private GoodsService goodsService;  //创建服务层对象

    @ApiOperation(value = "新增影碟商品", notes = "新增影碟商品")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody Goods goods) {
        return goodsService.add(goods);
    }
    
    @ApiOperation(value = "删除影碟商品", notes = "删除影碟商品")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        return goodsService.delete(id);
    }

    @ApiOperation(value = "修改影碟商品", notes = "修改影碟商品")
    @RequestMapping(value = "/updateGoods", method = {RequestMethod.POST})
    public Result updateGoods(@RequestBody Goods goods) {
        return goodsService.updateGoods(goods);
    }

    @ApiOperation(value = "获取影碟商品单个详情", notes = "获取影碟商品单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam Long id) {
        return goodsService.detail(id);
    }

    @ApiOperation(value = "分页查询影碟商品", notes = "分页查询影碟商品")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) Goods goods) {
        return goodsService.list(goods);
    }

    @ApiOperation(value = "为你推荐影碟商品", notes = "为你推荐影碟商品")
    @RequestMapping(value = "/findByNumber", method = {RequestMethod.POST})
    public Result findByNumber(@RequestBody(required =false) Goods goods) {
        return goodsService.findByNumber(goods);
    }

    @ApiOperation(value = "根据ids集合查询商品", notes = "根据ids集合查询商品")
    @RequestMapping(value = "/findByAllIds", method = {RequestMethod.POST})
    public Result findByAllIds(@RequestBody(required =false) Goods goods) {
        return goodsService.findByAllIds(goods);
    }
}
