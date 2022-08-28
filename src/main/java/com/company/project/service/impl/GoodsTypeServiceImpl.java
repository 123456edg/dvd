package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.GoodsTypeMapper;
import com.company.project.model.GoodsType;
import com.company.project.service.GoodsService;
import com.company.project.service.GoodsTypeService;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



@Service
@Transactional
public class GoodsTypeServiceImpl extends AbstractService<GoodsType> implements GoodsTypeService {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    @Resource
    private GoodsService goodsService;

    @Override
    public Result list(GoodsType goodsType) {
        PageHelper.startPage(goodsType.getPage(), goodsType.getLimit());
        goodsType.setIsDelete(false);
        List<GoodsType> list = goodsTypeMapper.list(goodsType);
        for (GoodsType d:list) {
            d.setGoodsList(goodsService.selectByGoodsTypeId(d.getId()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public String getNameById(Integer goodsType) {
        return goodsTypeMapper.getNameById(goodsType);
    }

}
