package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper extends Mapper<Goods> {

    int findGoodsCount(Long goodsId);

    List<Goods> selectByGoodsTypeId(Long goodsTypeId);

    Goods detail(Long id);

    List<Goods> list(Goods goods);

    List<Goods> findByAllIds(String[] array);
}
