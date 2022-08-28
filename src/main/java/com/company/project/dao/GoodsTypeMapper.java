package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.GoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeMapper extends Mapper<GoodsType> {

    List<GoodsType> list(GoodsType goodsType);

    String getNameById(Integer goodsType);
}
