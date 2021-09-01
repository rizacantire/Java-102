package business.concretes;


import business.abstracts.StoreService;
import entities.Category;
import entities.CellPhone;
import entities.NoteBook;

public class StoreManager implements StoreService {

    private BrandManager brandManager;
    private CategoryManager categoryManager;
    private ProductManager productManager;

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
    public void addNoteBook() {
        NoteBook huawei = new NoteBook("HUAWEI Matebook 14", 1,7000,0,5,this.brandManager.getBrands().get(3),1,16,512,14);
        NoteBook lenovo = new NoteBook("LENOVO V14 IGL",1,3699,0,5,this.brandManager.getBrands().get(1),2,8,1024,14);
        NoteBook asus = new NoteBook("Asus Tuf Gaming",1,8199,0,6,this.brandManager.getBrands().get(5),3,32,2048,15.6);
        this.productManager.addNoteBook(lenovo);
        this.productManager.addNoteBook(huawei);
        this.productManager.addNoteBook(asus);

    }

    @Override
    public void addCellPhone() {
        CellPhone samsung = new CellPhone("Samsung Galaxy A51",2,3199,0,3,this.brandManager.getBrands().get(0),1,128,6.5,32,4000,6,"Siyah");
        CellPhone iphone= new CellPhone("iPhone 11 64 GB",2,7329,0,3, this.brandManager.getBrands().get(2), 2,64,6.5,5,3046,6,"Mavi");
        CellPhone redmi = new CellPhone("Redmi Note 10 Pro 8GB",2,4012,0,8, this.brandManager.getBrands().get(7), 3,128,6.5,35,4000,12,"Beyaz");

        this.productManager.addCellPhone(samsung);
        this.productManager.addCellPhone(iphone);
        this.productManager.addCellPhone(redmi);
    }

    @Override
    public void addNewNoteBook() {

    }

    @Override
    public void addNewCellPhone() {

    }

    public void listNoteBook(){
        this.productManager.listNoteBook();
    }

    public void listCellPhone(){
        this.productManager.listCellPhone();
    }


}
