package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.MomentComment;
import com.company.project.core.Service;

import java.util.List;


/**
* Created by CodeGenerator on 2021/05/13.
*/
public interface MomentCommentService extends Service<MomentComment> {

    Result add(MomentComment momentComment);

    List<MomentComment> selectByOrderId(Long orderId);
    
    List<MomentComment> selectByGoodsId(Long goodsId);

    Result detail(Long id);

    Result list(MomentComment momentComment);
}
