import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreManager {
    private List<Brand> brands;

    public StoreManager() {
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    void add(String[] brandNames){
        this.brands = new ArrayList<>();
        for (String brand : brandNames){
            int id = 1 ;
            this.brands.add(new Brand(id,brand));
            id++;
        }


    }

    void listBrand(){

        for (var brand : this.brands){
            System.out.println(brand.getBrandName());
        }
    }
}
