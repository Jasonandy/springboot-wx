package cn.ucaner.wx.app.data.elasticsearch.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


public class Order {

    private long id;

    @JSONField(name="store_id")
    private int storeId;//店铺ID

    @JSONField(name="store_name")
    private String storeName;//店铺名字

    @JSONField(name="category_id")
    private int categoryId;//类目ID

    @JSONField(name="category_code")
    private String categoryCode;//类目名称

    @JSONField(name="product_code")
    private String productCode;//货号

    private int quantity;//销售件数

    private double amount;//销售金额

    @JSONField(name="pay_date")
    private Date payDate;//

    public Order() {
    }



    public Order(long id, int storeId, String storeName, int categoryId, String categoryCode, String productCode, int quantity, double amount, Date payDate) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.categoryId = categoryId;
        this.categoryCode = categoryCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.amount = amount;
        this.payDate = payDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
