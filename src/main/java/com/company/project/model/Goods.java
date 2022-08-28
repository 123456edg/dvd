package com.company.project.model;

import com.company.project.common.PageParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "t_goods")  //这个注解是用来建立实体类和数据库表之间的对应关系。默认通用mapper是开启驼峰命名的，但有时，实体类名和表名并不只是驼峰转下划线的形式，所以要使用这个注解的name属性来指定
public class Goods extends PageParam {
    /** 上面的继承PageParam意义是拥有单页显示数量和页数属性，便于实体类通过Get方法处理参数
     * 记录ID
     */
    @Id
    /* 主键回填 作用是让通用 Mapper 在执行 insert 操作之后将数据库自动生成的主键值回写到实体类对象中。
    通常和@Id注解一起使用。 */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建人
     */
    @Column(name = "created_by") //这个注解的作用是建立实体类字段和数据库表字段之间的对应关系 。
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

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
     * 业务状态(1上架;2下架)
     */
    private Integer status;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品种类
     */
    @Column(name = "goods_type")
    private Integer goodsType;

    /**
     * 库存
     */
    private BigDecimal repertory;

    /**
     * 售价
     */
    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    /**
     * 图片
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 热销状态
     */
    @Column(name = "sell_state")
    private Integer sellState;

    /**
     * 新品上市状态
     */
    @Column(name = "new_state")
    private Integer newState;

    /**
     * 规格
     */
    private String specifi;

    /**
     * 单位
     */
    private String units;

    /**
     * 正品
     */
    @Column(name = "quality_goods")
    private String qualityGoods;

    /**
     * 场地
     */
    private String site;

    /**
     * 曝光数
     */
    @Column(name = "exposure_number")
    private Integer exposureNumber;

    /**
     * 导演
     */
    private String director;

    /**
     * 编剧
     */
    private String scriptwriter;

    /**
     * 主演
     */
    @Column(name = "jade_laroche")
    private String jadeLaroche;

    /**
     * 制片国家/地区
     */
    @Column(name = "producer_country")
    private String producerCountry;

    /**
     * 语言
     */
    private String language;

    /**
     * 上映日期
     */
    @Column(name = "release_date")
    private String releaseDate;

    /**
     * 片长
     */
    private String footage;

    /**
     * 又名
     */
    @Column(name = "alternate_name")
    private String alternateName;

    /**
     * 图文详情
     */
    @Column(name = "graphic_details")
    private String graphicDetails;

    /**
     * 评分
     */
    private String grade;

    /**
     * 张数
     */
    @Column(name = "amount_of_sheets")
    private int amountOfSheets;

    /**
     * 保证金
     */
    @Column(name = "earnest_money")
    private BigDecimal earnestMoney;

    @Transient
    private Long cartId; //购物车id 用于用户获取购物车数据时，根据两个参数：商品id
    // 和租赁天数，连接商品表和购物车表，把购物车里的主键和租赁天数提取出来，放在商品实体类里的两个特殊字段内（不存在于数据库中的商品表）
    //分别是 cartid 和 number ,最后把携带这两个字段和商品固有属性放在Goods对象集合中，因为购物车的列表实际上掺杂着两个表的数据，但返回值类型为Goods(以Goods为主体)
    /**
     * 购物车数量
     */
    @Transient
    private Integer number; //租赁天数

    @Transient
    private String ids;

    @Transient
    private String goodsTypeName; //影碟类型名字

    @Transient
    private String keyword;

    /**
     * 商品销量
     * @return
     */
    @Transient
    private Integer salesVolume;

    @Transient
    private List<User> userLikeDtoList;

    @Transient
    private List<MomentComment> momentCommentList;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public BigDecimal getEarnestMoney() {
        return earnestMoney;
    }

    public void setEarnestMoney(BigDecimal earnestMoney) {
        this.earnestMoney = earnestMoney;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getAmountOfSheets() {
        return amountOfSheets;
    }

    public void setAmountOfSheets(int amountOfSheets) {
        this.amountOfSheets = amountOfSheets;
    }

    public List<MomentComment> getMomentCommentList() {
        return momentCommentList;
    }

    public void setMomentCommentList(List<MomentComment> momentCommentList) {
        this.momentCommentList = momentCommentList;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public List<User> getUserLikeDtoList() {
        return userLikeDtoList;
    }

    public void setUserLikeDtoList(List<User> userLikeDtoList) {
        this.userLikeDtoList = userLikeDtoList;
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
     * 获取业务状态(1上架;2下架)
     *
     * @return status - 业务状态(1上架;2下架)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态(1上架;2下架)
     *
     * @param status 业务状态(1上架;2下架)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品种类
     *
     * @return goods_type - 商品种类
     */
    public Integer getGoodsType() {
        return goodsType;
    }

    /**
     * 设置商品种类
     *
     * @param goodsType 商品种类
     */
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 获取库存
     *
     * @return repertory - 库存
     */
    public BigDecimal getRepertory() {
        return repertory;
    }

    /**
     * 设置库存
     *
     * @param repertory 库存
     */
    public void setRepertory(BigDecimal repertory) {
        this.repertory = repertory;
    }

    /**
     * 获取售价
     *
     * @return selling_price - 售价
     */
    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    /**
     * 设置售价
     *
     * @param sellingPrice 售价
     */
    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * 获取图片
     *
     * @return img_url - 图片
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片
     *
     * @param imgUrl 图片
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取热销状态
     *
     * @return sell_state - 热销状态
     */
    public Integer getSellState() {
        return sellState;
    }

    /**
     * 设置热销状态
     *
     * @param sellState 热销状态
     */
    public void setSellState(Integer sellState) {
        this.sellState = sellState;
    }

    /**
     * 获取新品上市状态
     *
     * @return new_state - 新品上市状态
     */
    public Integer getNewState() {
        return newState;
    }

    /**
     * 设置新品上市状态
     *
     * @param newState 新品上市状态
     */
    public void setNewState(Integer newState) {
        this.newState = newState;
    }

    /**
     * 获取规格
     *
     * @return specifi - 规格
     */
    public String getSpecifi() {
        return specifi;
    }

    /**
     * 设置规格
     *
     * @param specifi 规格
     */
    public void setSpecifi(String specifi) {
        this.specifi = specifi;
    }

    /**
     * 获取单位
     *
     * @return units - 单位
     */
    public String getUnits() {
        return units;
    }

    /**
     * 设置单位
     *
     * @param units 单位
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * 获取正品
     *
     * @return quality_goods - 正品
     */
    public String getQualityGoods() {
        return qualityGoods;
    }

    /**
     * 设置正品
     *
     * @param qualityGoods 正品
     */
    public void setQualityGoods(String qualityGoods) {
        this.qualityGoods = qualityGoods;
    }

    /**
     * 获取场地
     *
     * @return site - 场地
     */
    public String getSite() {
        return site;
    }

    /**
     * 设置场地
     *
     * @param site 场地
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * 获取曝光数
     *
     * @return exposure_number - 曝光数
     */
    public Integer getExposureNumber() {
        return exposureNumber;
    }

    /**
     * 设置曝光数
     *
     * @param exposureNumber 曝光数
     */
    public void setExposureNumber(Integer exposureNumber) {
        this.exposureNumber = exposureNumber;
    }

    /**
     * 获取导演
     *
     * @return director - 导演
     */
    public String getDirector() {
        return director;
    }

    /**
     * 设置导演
     *
     * @param director 导演
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * 获取编剧
     *
     * @return scriptwriter - 编剧
     */
    public String getScriptwriter() {
        return scriptwriter;
    }

    /**
     * 设置编剧
     *
     * @param scriptwriter 编剧
     */
    public void setScriptwriter(String scriptwriter) {
        this.scriptwriter = scriptwriter;
    }

    /**
     * 获取主演
     *
     * @return jade_laroche - 主演
     */
    public String getJadeLaroche() {
        return jadeLaroche;
    }

    /**
     * 设置主演
     *
     * @param jadeLaroche 主演
     */
    public void setJadeLaroche(String jadeLaroche) {
        this.jadeLaroche = jadeLaroche;
    }

    /**
     * 获取制片国家/地区
     *
     * @return producer_country - 制片国家/地区
     */
    public String getProducerCountry() {
        return producerCountry;
    }

    /**
     * 设置制片国家/地区
     *
     * @param producerCountry 制片国家/地区
     */
    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取上映日期
     *
     * @return release_date - 上映日期
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 设置上映日期
     *
     * @param releaseDate 上映日期
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 获取片长
     *
     * @return footage - 片长
     */
    public String getFootage() {
        return footage;
    }

    /**
     * 设置片长
     *
     * @param footage 片长
     */
    public void setFootage(String footage) {
        this.footage = footage;
    }

    /**
     * 获取又名
     *
     * @return alternate_name - 又名
     */
    public String getAlternateName() {
        return alternateName;
    }

    /**
     * 设置又名
     *
     * @param alternateName 又名
     */
    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    /**
     * 获取图文详情
     *
     * @return graphic_details - 图文详情
     */
    public String getGraphicDetails() {
        return graphicDetails;
    }

    /**
     * 设置图文详情
     *
     * @param graphicDetails 图文详情
     */
    public void setGraphicDetails(String graphicDetails) {
        this.graphicDetails = graphicDetails;
    }
}
