package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Goods;
import com.company.project.core.Service;

import java.util.List;


/**
* Created by CodeGenerator on 2021/05/13.
*/
public interface GoodsService extends Service<Goods> {

    int findGoodsCount(Long goodsId);

    List<Goods> selectByGoodsTypeId(Long id);

    Result detail(Long id);

    Result list(Goods goods);

    Result add(Goods goods);

    Result delete(Long id);

    Result updateGoods(Goods goods);

    Result findByNumber(Goods goods);

    Result findByAllIds(Goods goods);
}
