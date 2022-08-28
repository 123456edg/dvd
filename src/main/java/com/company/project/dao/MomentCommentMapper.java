package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.MomentComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentCommentMapper extends Mapper<MomentComment> {

    List<MomentComment> selectByOrderId(Long orderId);
    
    List<MomentComment> selectByGoodsId(Long goodsId);

    List<MomentComment> detail(Long goodsId);

    List<MomentComment> list(MomentComment momentComment);
}
