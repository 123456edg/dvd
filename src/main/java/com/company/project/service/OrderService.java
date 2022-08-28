package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Order;
import com.company.project.core.Service;


/**
* Created by CodeGenerator on 2021/05/13.
*/
public interface OrderService extends Service<Order> {

    Result add(Order order);

    Result list(Order order);

    Result detail(Long id);

    Result updateGoods(Order order);
}
