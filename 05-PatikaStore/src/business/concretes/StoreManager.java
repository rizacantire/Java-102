package business.concretes;


import business.abstracts.StoreService;
import entities.Brand;
import entities.Category;
import entities.CellPhone;
import entities.NoteBook;

import java.util.Scanner;

public class StoreManager implements StoreService {
    Scanner scanner = new Scanner(System.in);
    private BrandManager brandManager;
    private CategoryManager categoryManager;
    private ProductManager productManager;
    private int productSize;
    private int noteBookListSize;
    private int cellPhoneListSize;

    public int getNoteBookListSize() {
        return this.productManager.noteBooks.size();
    }



    public int getCellPhoneListSize() {
        return this.productManager.cellPhones.size();
    }


    public int getProductSize() {

        return this.productManager.allProducts.size();
    }


    public StoreManager(BrandManager brandManager, CategoryManager categoryManager, ProductManager productManager) {
        this.brandManager = brandManager;
        this.categoryManager = categoryManager;
        this.productManager = productManager;
    }

    public void addBrand(){
        String[] brands = {"Samsung", "Lenovo", "Apple", "Huawei", "Casper", "Asus", "HP", "Xiaomi", "Monster"};
        this.brandManager.addBrand(brands);

    }
    public void listBrand(){
        this.brandManager.listBrand();
    }

    @Override
    public void addCategory() {
        Category noteBook = new Category(1,"Notebook");
        Category cellPhone = new Category(2,"Cep Telefonu");
        this.categoryManager.addCategory(noteBook);
        this.categoryManager.addCategory(cellPhone);
    }

    @Override
    public void listProducts() {
        this.productManager.listProduct();
    }

    @Override
    public void addNoteBook() {
        NoteBook huawei = new NoteBook(4,"HUAWEI Matebook 14", this.categoryManager.categories.get(0),7000,0,5,this.brandManager.getBrands().get(4),1,16,512,14);
        NoteBook lenovo = new NoteBook(5,"LENOVO V14 IGL",this.categoryManager.categories.get(0),3699,0,5,this.brandManager.getBrands().get(5),2,8,1024,14);
        NoteBook asus = new NoteBook(6,"Asus Tuf Gaming",this.categoryManager.categories.get(0),8199,0,6,this.brandManager.getBrands().get(1),3,32,2048,15.6);
        this.productManager.addNoteBook(lenovo);
        this.productManager.addNoteBook(huawei);
        this.productManager.addNoteBook(asus);

    }

    @Override
    public void addCellPhone() {
        CellPhone samsung = new CellPhone(1,"Samsung Galaxy A51",this.categoryManager.categories.get(1),3199,0,3,this.brandManager.getBrands().get(7),1,128,6.5,32,4000,6,"Siyah");
        CellPhone iphone= new CellPhone(2,"iPhone 11 64 GB",this.categoryManager.categories.get(1),7329,0,3, this.brandManager.getBrands().get(0), 2,64,6.5,5,3046,6,"Mavi");
        CellPhone redmi = new CellPhone(3,"Redmi Note 10 Pro 8GB",this.categoryManager.categories.get(1),4012,0,8, this.brandManager.getBrands().get(8), 3,128,6.5,35,4000,12,"Beyaz");

        this.productManager.addCellPhone(samsung);
        this.productManager.addCellPhone(iphone);
        this.productManager.addCellPhone(redmi);
    }

    @Override
    public void listProductWihId(int id) {
        this.productManager.listProductWihId(id);
    }

    @Override
    public void listProductWihBrand(int brandId) {
        this.productManager.listProductWihBrand(brandId);
    }

    @Override
    public void listProductByCategory(int categoryId) {
        this.productManager.listProductByCategory(categoryId);
    }

    @Override
    public void addNewNoteBook(NoteBook noteBook) {

        this.productManager.addNoteBook(noteBook);
    }

    @Override
    public void addNewCellPhone(CellPhone cellPhone) {
        this.productManager.addCellPhone(cellPhone);
    }

    @Override
    public void deleteNoteBook(int id) {

        this.productManager.deleleteNotebookById(id);
    }

    @Override
    public void deleteCellPhone(int id) {
        this.productManager.deleleteCellPhoneById(id);
    }

    public void listNoteBook(){
        this.productManager.listNoteBook();
    }

    public void listCellPhone(){
        this.productManager.listCellPhone();
    }

    public void listBrandById(){
        this.brandManager.listBrandById();
    }


    public Brand getBrandById(int brandId){
        return this.brandManager.getBrandById(brandId);
    }


}
