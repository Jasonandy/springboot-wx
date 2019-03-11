package cn.ucaner.wx.app.data.elasticsearch.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @ClassName：Order
 * @Description： <p> Order </p>
 * @Author： - Jason
 * @CreatTime：2019/3/11 - 8:53
 * @Modify By：
 * @ModifyTime： 2019/3/11
 * @Modify marker：
 * @version V1.0
*/
public class Order {

    /**
     * id
     */
    private long id;

    /**
     * 店铺ID
     */
    @JSONField(name="store_id")
    private int storeId;

    /**
     * 店铺名字
     */
    @JSONField(name="store_name")
    private String storeName;

    /**
     * 类目ID
     */
    @JSONField(name="category_id")
    private int categoryId;

    /**
     * 类目名称
     */
    @JSONField(name="category_code")
    private String categoryCode;//

    /**
     * 货号
     */
    @JSONField(name="product_code")
    private String productCode;//

    /**
     * 销售件数
     */
    private int quantity;

    /**
     * 销售金额
     */
    private double amount;

    /**
     * 支付日期
     */
    @JSONField(name="pay_date")
    private Date payDate;


    public Order() {}


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