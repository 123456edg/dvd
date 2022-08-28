package com.company.project.model;

import com.company.project.common.PageParam;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_moment_comment")
public class MomentComment extends PageParam {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

   
    @Column(name = "created_at")
    private Date createdAt;

    
    @Column(name = "updated_by")
    private String updatedBy;

    
    @Column(name = "updated_at")
    private Date updatedAt;

    
    @Column(name = "is_delete")
    private Boolean isDelete;
    
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name = "goods_id")
    private Long goodsId;
    
    @Column(name = "comment")
    private Long comment;
    
    
    
    @Transient
    private String userName;
    
    @Transient
    private Order order;
    
    @Transient
    private Goods goods;
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public Goods getGoods() {
        return goods;
    }
    
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

   
    public Date getUpdatedAt() {
        return updatedAt;
    }

   
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

   
    public Boolean getIsDelete() {
        return isDelete;
    }

   
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Long getGoodsId() {
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    
    public Long getComment() {
        return comment;
    }
    
    public void setComment(Long comment) {
        this.comment = comment;
    }
}
