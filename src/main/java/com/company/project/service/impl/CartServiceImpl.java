package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.CartMapper;
import com.company.project.model.Cart;
import com.company.project.model.MomentComment;
import com.company.project.service.CartService;
import com.company.project.core.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



@Service
@Transactional
public class CartServiceImpl extends AbstractService<Cart> implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Override
    public Result list(Cart cart) {
        PageHelper.startPage(cart.getPage(), cart.getLimit());
        cart.setIsDelete(false);
        List<Cart> list = null;
        list = cartMapper.list(cart);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result batchDelete(Cart cart) {
        String[] array = cart.getIds().split(",");
        // String的split()方法用于按传入的字符或字符串对String进行拆分，返回拆分之后的数组。
        for (int i = 0; i <array.length; i++){
            cart.setIsDelete(true);
            cart.setId(Long.valueOf(array[i]));
            update(cart);
        }
        return ResultGenerator.genSuccessResult();
    }

}
