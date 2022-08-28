package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.GoodsMapper;
import com.company.project.model.Goods;
import com.company.project.model.User;
import com.company.project.service.*;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;



@Service
@Transactional
public class GoodsServiceImpl extends AbstractService<Goods> implements GoodsService {
    

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private UserService userService;

    @Resource
    private MomentCommentService momentCommentService;

    @Resource
    private GoodsTypeService goodsTypeService;

    @Resource
    private OrderDetailsService orderDetailsService;

    @Override
    public int findGoodsCount(Long goodsId) {
        return goodsMapper.findGoodsCount(goodsId);
    }

    @Override
    public List<Goods> selectByGoodsTypeId(Long goodsTypeId) {
        return goodsMapper.selectByGoodsTypeId(goodsTypeId);
    }

    @Override
    public Result detail(Long id) {
        Goods goods = goodsMapper.detail(id);
        if (null != goods){
            goods.setSalesVolume(orderDetailsService.getCountByGoodsId(goods.getId()));
            goods.setGoodsTypeName(goodsTypeService.getNameById(goods.getGoodsType()));
            goods.setMomentCommentList(momentCommentService.selectByGoodsId(goods.getId()));

        }
        return ResultGenerator.genSuccessResult(goods);
    }

    @Override
    public Result list(Goods goods) {
        PageHelper.startPage(goods.getPage(), goods.getLimit());
        List<Goods> list = goodsMapper.list(goods);
        if (list.size() > 0){
            for (Goods d:list) {
                d.setSalesVolume(orderDetailsService.getCountByGoodsId(d.getId()));
                d.setGoodsTypeName(goodsTypeService.getNameById(d.getGoodsType())); //通过影碟类型id获取类型名
                d.setMomentCommentList(momentCommentService.selectByGoodsId(d.getId()));
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result add(Goods goods) {
        goods.setCreatedAt(new Date());
        goods.setIsDelete(false);
        save(goods);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(goods);
        return result;
    }

    @Override //逻辑删除
    public Result delete(Long id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setIsDelete(true);
        update(goods);
        return ResultGenerator.genSuccessResult();
    }

    @Override  //更改单个商品
    public Result updateGoods(Goods goods) {
        goods.setUpdatedAt(new Date());
        update(goods);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(goods);
        return result;
    }

    @Override //其实是为你推荐模块
    public Result findByNumber(Goods goods) {
        PageHelper.startPage(goods.getPage(), goods.getLimit());
        goods.setIsDelete(false);
        List<Goods> list = goodsMapper.list(goods);
        Collections.shuffle(list);  //为你推荐模块的随机顺序生成
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result findByAllIds(Goods goods) {
        String[] array = goods.getIds().split(",");
        List<Goods> list = goodsMapper.findByAllIds(array);
        return ResultGenerator.genSuccessResult(list);
    }

}
