package com.company.project.model;

import com.company.project.common.PageParam;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order_details")
public class OrderDetails extends PageParam {
    /**
     * 记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 是否删除(0false未删除 1true已删除)
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 业务状态
     */
    private Integer status;

    /**
     * 商品
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 订单的id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 商品名称
     */
    @Transient
    private String goodsName;

    /**
     * 商品种类
     */
    @Transient
    private Integer goodsType;

    /**
     * 库存
     */
    @Transient
    private BigDecimal repertory;

    /**
     * 售价
     */
    @Transient  //临时属性 影碟每日租金
    private BigDecimal sellingPrice;

    /**
     * 图片
     */
    @Transient
    private String imgUrl;

    /**
     * 排序
     */
    @Transient
    private Integer sort;

    /**
     * 热销状态
     */
    @Transient
    private Integer sellState;

    /**
     * 新品上市状态
     */
    @Transient
    private Integer newState;

    /**
     * 图文详情
     */
    @Transient
    private String graphicDetails;

    @Transient
    private Long cartId;

    /**
     * 商品销量
     * @return
     */
    @Transient
    private Integer salesVolume;

    /**
     * 场地
     */
    @Transient
    private String site;

    /**
     * 曝光数
     */
    @Transient
    private Integer exposureNumber;

    /**
     * 导演
     */
    @Transient
    private String director;

    /**
     * 编剧
     */
    @Transient
    private String scriptwriter;

    /**
     * 主演
     */
    @Transient
    private String jadeLaroche;

    /**
     * 制片国家/地区
     */
    @Transient
    private String producerCountry;

    /**
     * 语言
     */
    @Transient
    private String language;

    /**
     * 上映日期
     */
    @Transient
    private String releaseDate;

    /**
     * 片长
     */
    @Transient
    private String footage;

    /**
     * 又名
     */
    @Transient
    private String alternateName;

    /**
     * 评分
     */
    @Transient
    private String grade;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public BigDecimal getRepertory() {
        return repertory;
    }

    public void setRepertory(BigDecimal repertory) {
        this.repertory = repertory;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSellState() {
        return sellState;
    }

    public void setSellState(Integer sellState) {
        this.sellState = sellState;
    }

    public Integer getNewState() {
        return newState;
    }

    public void setNewState(Integer newState) {
        this.newState = newState;
    }

    public String getGraphicDetails() {
        return graphicDetails;
    }

    public void setGraphicDetails(String graphicDetails) {
        this.graphicDetails = graphicDetails;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getExposureNumber() {
        return exposureNumber;
    }

    public void setExposureNumber(Integer exposureNumber) {
        this.exposureNumber = exposureNumber;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScriptwriter() {
        return scriptwriter;
    }

    public void setScriptwriter(String scriptwriter) {
        this.scriptwriter = scriptwriter;
    }

    public String getJadeLaroche() {
        return jadeLaroche;
    }

    public void setJadeLaroche(String jadeLaroche) {
        this.jadeLaroche = jadeLaroche;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFootage() {
        return footage;
    }

    public void setFootage(String footage) {
        this.footage = footage;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取记录ID
     *
     * @return id - 记录ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录ID
     *
     * @param id 记录ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取修改人
     *
     * @return updated_by - 修改人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置修改人
     *
     * @param updatedBy 修改人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取是否删除(0false未删除 1true已删除)
     *
     * @return is_delete - 是否删除(0false未删除 1true已删除)
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除(0false未删除 1true已删除)
     *
     * @param isDelete 是否删除(0false未删除 1true已删除)
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取业务状态
     *
     * @return status - 业务状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态
     *
     * @param status 业务状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取商品
     *
     * @return goods_id - 商品
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品
     *
     * @param goodsId 商品
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取订单的id
     *
     * @return order_id - 订单的id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单的id
     *
     * @param orderId 订单的id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
