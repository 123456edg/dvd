package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.MomentCommentMapper;
import com.company.project.model.MomentComment;
import com.company.project.model.OrderDetails;
import com.company.project.model.User;
import com.company.project.service.*;
import com.company.project.core.AbstractService;
import com.company.project.utils.DigitUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;



@Service
@Transactional
public class MomentCommentServiceImpl extends AbstractService<MomentComment> implements MomentCommentService {

    @Resource
    private MomentCommentMapper momentCommentMapper;

    @Resource
    private UserService userService;
    
    @Resource
    private OrderDetailsService orderDetailsService;
    
    @Resource
    private OrderService orderService;
    
    @Resource
    private GoodsService goodsService;

    @Override
    public Result add(MomentComment momentComment) {
        momentComment.setCreatedAt(new Date());
        momentComment.setIsDelete(false);
        
    
        List<OrderDetails> orderDetailsList = orderDetailsService.selectByOrderId(momentComment.getOrderId());
        if (orderDetailsList.size() > 0){
            momentComment.setGoodsId(orderDetailsList.get(0).getGoodsId());
            
            }
            save(momentComment);
            Result result= ResultGenerator.genSuccessResult();
            result.setData(momentComment);
            return result;
    }
    
    @Override
    public List<MomentComment> selectByOrderId(Long orderId) {
        List<MomentComment> list = momentCommentMapper.selectByOrderId(orderId);
        for (MomentComment d:list) {
            d.setUserName(userService.selectUserById(d.getCreatedBy()).getUserName());
        }
        return list;
    }
    
    @Override
    public List<MomentComment> selectByGoodsId(Long goodsId) {
        List<MomentComment> list = momentCommentMapper.selectByGoodsId(goodsId);
        for (MomentComment d:list) {
            d.setUserName(userService.selectUserById(d.getCreatedBy()).getUserName());
        }
        return list;
    }
    
    @Override
    public Result detail(Long goodsId) {
        List<MomentComment> list = momentCommentMapper.detail(goodsId);
        for (MomentComment d:list) {
            d.setUserName(userService.selectUserById(d.getCreatedBy()).getUserName());
        }
        return ResultGenerator.genSuccessResult(list);
    }

    @Override
    public Result list(MomentComment momentComment) {

        User user = userService.selectUserById(momentComment.getCreatedBy());

        if (user != null){
            if (0 == user.getRole()){ //如果登陆者身份为管理员
                momentComment.setCreatedBy(null);
            }
        }

        PageHelper.startPage(momentComment.getPage(), momentComment.getLimit());
        momentComment.setIsDelete(false);
    
        List<MomentComment> list = momentCommentMapper.list(momentComment);
        for (MomentComment d:list) {
            d.setUserName(userService.selectUserById(d.getCreatedBy()).getUserName());//评论表里的userid转化为userName
            d.setOrder(orderService.findById(d.getOrderId()));
            d.setGoods(goodsService.findById(d.getGoodsId()));
        }
        
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
