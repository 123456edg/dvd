package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.GoodsType;
import com.company.project.core.Service;


/**
* Created by CodeGenerator on 2021/05/13.
*/
public interface GoodsTypeService extends Service<GoodsType> {

    Result list(GoodsType goodsType);

    String getNameById(Integer goodsType);
}
