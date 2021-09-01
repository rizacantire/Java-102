package business.concretes;

import business.abstracts.ProductService;
import entities.CellPhone;
import entities.NoteBook;
import entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductManager implements ProductService {
    List<NoteBook> noteBooks = new ArrayList<>();
    List<CellPhone> cellPhones = new ArrayList<>();
    List<Product> allProducts = new ArrayList<>();

    @Override
    public void addNoteBook(NoteBook noteBook) {

        this.noteBooks.add(noteBook);
        this.allProducts.add(noteBook);
    }

    @Override
    public void addCellPhone(CellPhone cellPhone) {
        this.allProducts.add(cellPhone);
        this.cellPhones.add(cellPhone);
    }

    @Override
    public void listNoteBook() {
        System.out.println("Notebook Listesi");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("| %-4s | %-30s | %-10s | %-10s | %-10s | %-12s | %-10s |","ID","ürün Adı","Fiyat","Marka","Depolama","Ekran","RAM");
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");

        Collections.sort(this.noteBooks);
        for (var noteBook : noteBooks){
            System.out.format("\n| %-4s | %-30s | %-10s | %-10s | %-10s | %-12s | %-10s |",noteBook.getNoteBookId(),noteBook.getProductName(),noteBook.getUnitPrice(),noteBook.getBrand().getBrandName(),noteBook.getStorageSize(),noteBook.getScreenSize(),noteBook.getMemorySize());
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void listCellPhone() {
        System.out.println("Cep Telefonu Listesi");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("| %-4s | %-30s | %-10s | %-10s | %-10s | %-12s | %-10s | %-10s | %-10s | %-10s |","ID","Ürün Adı","Fiyat","Marka","Hafıza","Erkan","RAM","Kamera","Batarya","Renk");
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");

        Collections.sort(this.cellPhones);
        for (var cellPhone : cellPhones){
            System.out.format("\n| %-4s | %-30s | %-10s | %-10s | %-10s | %-12s | %-10s | %-10s | %-10s | %-10s |",cellPhone.getCellPhoneId(),cellPhone.getProductName(),cellPhone.getUnitPrice(),cellPhone.getBrand().getBrandName(),cellPhone.getStorageSize(),cellPhone.getScreenSize(),cellPhone.getMemorySize(),
                    cellPhone.getCamera(),cellPhone.getBatteryPower(),cellPhone.getColor());
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void listProduct() {
        System.out.println("Ürünler");
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("| %-25s |%-10s | %-15s \n","Ürün Adı","Marka","Kategori");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

        for (var product : allProducts){
            System.out.format("\n| %-25s |%-10s | %-15s ",product.getProductName() ,product.getBrand().getBrandName(),product.getCategory().getCategoryName());
        }
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");

    }

    @Override
    public void deleleteNotebookById(int id) {
        var deleteItem = noteBooks.stream().filter(n->n.getNoteBookId()==id).findAny();
        noteBooks.remove(deleteItem.get());
        allProducts.remove(deleteItem.get());
        System.out.println("-----------\tÜrün başarıyla silindi\t---------------");
        System.out.format("| %-25s |%-10s | %-15s \n","Ürün Adı","Marka","Kategori");
        System.out.format("| %-25s |%-10s | %-15s ",deleteItem.get().getProductName() ,deleteItem.get().getBrand().getBrandName(),deleteItem.get().getCategory().getCategoryName());

    }

    @Override
    public void deleleteCellPhoneById(int id) {
        var deleteItem = cellPhones.stream().filter(n->n.getCellPhoneId()==id).findAny();
        cellPhones.remove(deleteItem.get());
        allProducts.remove(deleteItem.get());
        System.out.println("-----------\tÜrün başarıyla silindi\t---------------");
        System.out.format("| %-25s |%-10s | %-15s \n","Ürün Adı","Marka","Kategori");
        System.out.format("| %-25s |%-10s | %-15s ",deleteItem.get().getProductName() ,deleteItem.get().getBrand().getBrandName(),deleteItem.get().getCategory().getCategoryName());

    }


    @Override
    public void listProductByCategory(int categoryId) {
        System.out.println("Ürünler");
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("| %-25s |%-10s | %-15s \n","Ürün Adı","Marka","Kategori");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

        for (var product : allProducts){
            if (product.getCategory().getCategoryId() == categoryId){
                System.out.format("\n| %-25s |%-10s | %-15s ",product.getProductName() ,product.getBrand().getBrandName(),product.getCategory().getCategoryName());
            }
        }
    }

    @Override
    public void listProductWihId(int id) {
        var product = allProducts.stream().filter(p->p.getProductId()==id).findAny();
        System.out.format("| %-4s | %-30s | %-10s | %-10s | %-10s | ","ID","Ürün Adı","Fiyat","Marka","Kategori");
        System.out.format("\n| %-4s | %-30s | %-10s | %-10s | %-10s |",product.get().getProductId(),product.get().getProductName(),product.get().getUnitPrice(),product.get().getBrand().getBrandName(),product.get().getCategory().getCategoryName());

    }

    @Override
    public void listProductWihBrand(int brandId) {
        for (var product : allProducts){
            if (product.getBrand().getId() == brandId){
                System.out.format("\n| %-25s |%-10s | %-15s ",product.getProductName() ,product.getBrand().getBrandName(),product.getCategory().getCategoryName());
            }
        }
    }
}
