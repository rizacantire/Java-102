package business.concretes;

import business.abstracts.ProductService;
import entities.CellPhone;
import entities.NoteBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductManager implements ProductService {
    private List<NoteBook> noteBooks = new ArrayList<>();
    private List<CellPhone> cellPhones = new ArrayList<>();

    @Override
    public void addNoteBook(NoteBook noteBook) {
        this.noteBooks.add(noteBook);
    }

    @Override
    public void addCellPhone(CellPhone cellPhone) {
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

    }

    @Override
    public void deleteProductWithId(int id) {

    }

    @Override
    public void listProductByCategory(int categoryId) {

    }

    @Override
    public void listProductWihId(int id) {

    }
}
