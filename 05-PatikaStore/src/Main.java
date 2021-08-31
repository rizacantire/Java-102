import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        String[] brands = {"Samsung", "Lenovo", "Apple", "Huawei", "Casper", "Asus", "HP", "Xiaomi", "Monster"};
        StoreManager manager = new StoreManager();
        manager.add(brands);
        Collections.sort(manager.getBrands());
        manager.listBrand();
    }
}
