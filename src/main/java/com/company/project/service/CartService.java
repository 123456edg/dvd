package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.Cart;
import com.company.project.core.Service;


/**
* Created by CodeGenerator on 2021/05/15.
*/
public interface CartService extends Service<Cart> {

    Result list(Cart cart);

    Result batchDelete(Cart cart);
}
