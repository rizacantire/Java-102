package entities;

public class Product {

    private String productName;
    private int categoryId;
    private double unitPrice;
    private double discount;
    private int unitsInStock;
    private Brand brandName;

    public Product(String productName, int categoryId, double unitPrice, double discount, int unitsInStock, Brand brandName) {

        this.productName = productName;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.unitsInStock = unitsInStock;
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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