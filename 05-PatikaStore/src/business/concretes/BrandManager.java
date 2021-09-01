package business.concretes;

import business.abstracts.BrandService;
import entities.Brand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrandManager implements BrandService {
    private List<Brand> brands;

    public BrandManager() {
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    @Override
    public void addBrand(String[] brandNames){
        this.brands = new ArrayList<>();
        int id = 1 ;
        for (String brand : brandNames){
            this.brands.add(new Brand(id,brand));
            id++;
        }

    }
    @Override
    public void listBrand(){
        Collections.sort(this.brands);
        for (var brand : this.brands){
            System.out.println("- " +brand.getBrandName());
        }
    }
}
