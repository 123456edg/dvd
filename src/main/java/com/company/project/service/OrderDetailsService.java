package com.company.project.service;
import com.company.project.model.OrderDetails;
import com.company.project.core.Service;

import java.util.List;


/**
* Created by CodeGenerator on 2021/05/13.
*/
public interface OrderDetailsService extends Service<OrderDetails> {

    List<OrderDetails> selectByOrderId(Long id);

    int getCountByGoodsId(Long goodsId);
}
