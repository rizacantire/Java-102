package entities;

public class Product {
    private int productId;
    private String productName;
    private Category category;
    private double unitPrice;
    private double discount;
    private int unitsInStock;
    private Brand brandName;

    public Product(int productId, String productName, Category category, double unitPrice, double discount, int unitsInStock, Brand brandName) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.unitsInStock = unitsInStock;
        this.brandName = brandName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrandName() {
        return brandName;
    }

    public void setBrandName(Brand brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategoryId(Category category) {
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Brand getBrand() {
        return brandName;
    }

    public void setBrand(Brand  brandName) {
        this.brandName = brandName;
    }

}