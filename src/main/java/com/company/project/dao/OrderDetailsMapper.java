package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.OrderDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsMapper extends Mapper<OrderDetails> {

    List<OrderDetails> selectByOrderId(Long orderId);

    int getCountByGoodsId(Long goodsId);
}
