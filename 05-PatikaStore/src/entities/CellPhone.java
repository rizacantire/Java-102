package entities;

public class CellPhone extends Product implements Comparable<CellPhone>{
    private int cellPhoneId;
    private int storageSize;
    private double screenSize;
    private int camera;
    private int batteryPower;
    private int memorySize;
    private String color;

    public CellPhone(String productName, int categoryId, double unitPrice, double discount, int unitsInStock, Brand brandName, int cellPhoneId, int storageSize, double screenSize, int camera, int batteryPower, int memorySize, String color) {
        super(productName, categoryId, unitPrice, discount, unitsInStock, brandName);
        this.cellPhoneId = cellPhoneId;
        this.storageSize = storageSize;
        this.screenSize = screenSize;
        this.camera = camera;
        this.batteryPower = batteryPower;
        this.memorySize = memorySize;
        this.color = color;
    }

    public int getCellPhoneId() {
        return cellPhoneId;
    }

    public void setCellPhoneId(int cellPhoneId) {
        this.cellPhoneId = cellPhoneId;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(int batteryPower) {
        this.batteryPower = batteryPower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int compareTo(CellPhone o) {
        return 0;
    }
}
