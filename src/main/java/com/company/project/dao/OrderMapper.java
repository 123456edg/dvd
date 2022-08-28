package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper extends Mapper<Order> {

    List<Order> list(Order order);

    Order detail(Long id);

}
