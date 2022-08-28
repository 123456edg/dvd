package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper extends Mapper<Cart> {

    List<Cart> list(Cart cart);

    void batchDelete(String[] array);
}