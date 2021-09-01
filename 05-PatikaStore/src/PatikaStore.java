import business.concretes.BrandManager;
import business.concretes.CategoryManager;
import business.concretes.ProductManager;
import business.concretes.StoreManager;
import entities.Category;
import entities.CellPhone;
import entities.NoteBook;

import java.util.Scanner;

public class PatikaStore {
    private final Scanner scanner = new Scanner(System.in);

    public void start(){

        System.out.println("PatikaStore Ürün Yönetim Sistemi !");
        System.out.println("1 - Notebook İşlemleri\n" +
                "2 - Cep Telefonu İşlemleri\n" +
                "3 - Marka Listele\n" +
                "4 - Bütün ürünleri listele\n"+
                "0 - Çıkış Yap" );
        System.out.println("Markalarımız\n" +
                "----------------------");
        BrandManager brandManager = new BrandManager();
        CategoryManager categoryManager = new CategoryManager();
        ProductManager productManager = new ProductManager();
        StoreManager manager = new StoreManager(brandManager,categoryManager,productManager);

        manager.addBrand();
        manager.listBrand();
        manager.addCategory();
        manager.addNoteBook();
        manager.addCellPhone();

        while (true){
            System.out.println("----------------------------------------------------------------");
            System.out.println("\n1 - Notebook İşlemleri\n" +
                    "2 - Cep Telefonu İşlemleri\n" +
                    "3 - Marka Listele\n" +
                    "4 - Bütün ürünleri listele\n"+
                    "5 - Markaya göre filtreleme\n"+
                    "0 - Çıkış Yap" );
            System.out.print("Lütfen işlem seçiniz : ");
            int input = scanner.nextInt();
            switch (input){
                case 1:
                    System.out.println("Notebook işlem bölümüne hoş geldiniz.");
                    System.out.println("1 - Notebook'ları listeleme\n" +
                            "2 - Yeni Notebook ekleme\n" +
                            "3 - Notebook silme");
                    System.out.println("İşlem seçiniz");
                    int newInput = scanner.nextInt();

                    if (newInput == 1){
                        manager.listNoteBook();
                    }else if (newInput == 2){
                        System.out.println("Ürün ekleme ekranına hoşgeldiniz");
                        System.out.print("Lütfen ürün adı giriniz : ");
                        String productName = scanner.next();
                        System.out.print("Ürün fiyatını giriniz : ");
                        int unitPrice = scanner.nextInt();
                        System.out.print("Stok adenini giriniz : ");
                        int unitsInStock = scanner.nextInt();
                        manager.listBrandById();
                        System.out.print("Marka giriniz : ");
                        int brandId = scanner.nextInt();
                        System.out.print("Ram bilgisi girin : ");
                        int ram = scanner.nextInt();
                        System.out.print("Harddisk kapasitesi girin : ");
                        int storage = scanner.nextInt();
                        System.out.print("Ekran ölçüsü girin : ");
                        double screenSize = scanner.nextDouble();
                        var note = new NoteBook((manager.getProductSize()+1),productName,categoryManager.getCategory(0),unitPrice,0,unitsInStock,manager.getBrandById(brandId),
                                manager.getNoteBookListSize()+1,
                                ram,storage,screenSize);
                        manager.addNewNoteBook(note);
                    }else if(newInput == 3){
                        manager.listNoteBook();
                        System.out.print("Silinecek Notebook (id) sini giriniz : ");
                        int noteBookId = scanner.nextInt();
                        manager.deleteNoteBook(noteBookId);
                    }else {
                        System.out.println("Hatalı işlem gerçekleştirdinz...");
                    }
                    break;
                case 2:
                    System.out.println("Cep Telefonu işlem bölümüne hoş geldiniz.");
                    System.out.println("1 - Telefonları listeleme\n" +
                            "2 - Yeni Telefon ekleme\n" +
                            "3 - Telefon silme");
                    System.out.println("İşlem seçiniz");
                    int newPhoneInput = scanner.nextInt();

                    if (newPhoneInput == 1){
                        manager.listNoteBook();
                    }else if (newPhoneInput == 2){
                        System.out.println("Ürün ekleme ekranına hoşgeldiniz");
                        System.out.print("Lütfen ürün adı giriniz : ");
                        String productName = scanner.next();
                        System.out.print("Ürün fiyatını giriniz : ");
                        int unitPrice = scanner.nextInt();
                        System.out.print("Stok adenini giriniz : ");
                        int unitsInStock = scanner.nextInt();
                        manager.listBrandById();
                        System.out.print("Marka giriniz : ");
                        int brandId = scanner.nextInt();
                        System.out.print("Ram bilgisi girin : ");
                        int ram = scanner.nextInt();
                        System.out.print("Hafıza kapasitesi girin : ");
                        int storage = scanner.nextInt();
                        System.out.print("Ekran ölçüsü girin : ");
                        double screenSize = scanner.nextDouble();
                        System.out.print("Batarya değerini girin : ");
                        int batery = scanner.nextInt();
                        System.out.print("Kamera megapixel değerini girin : ");
                        int cameraSize = scanner.nextInt();
                        System.out.print("Renk Girin : ");
                        String phoneColor = scanner.next();

                        var phone = new CellPhone(manager.getProductSize()+1,productName,categoryManager.getCategory(1),unitPrice,0,unitsInStock,manager.getBrandById(brandId),
                                manager.getCellPhoneListSize()+1,storage,screenSize,cameraSize,batery,ram,phoneColor);
                        manager.addNewCellPhone(phone);
                    }else if(newPhoneInput == 3){
                        manager.listCellPhone();
                        System.out.print("Silinecek Telefon (id) sini giriniz : ");
                        int cellPhone = scanner.nextInt();
                        manager.deleteCellPhone(cellPhone);
                    }else {
                        System.out.println("Hatalı işlem gerçekleştirdinz...");
                    }
                    manager.listCellPhone();
                    break;
                case 3:
                    manager.listBrand();
                    break;
                case 4:
                    manager.listProducts();
                    break;
                case 5:
                    System.out.print("Id filtre için  (1) - Marka Filtre için (2) : ");
                    int selection = scanner.nextInt();
                    if (selection == 1){
                        System.out.print("Ürün Id'si girin : ");
                        int productId = scanner.nextInt();
                        manager.listProductWihId(productId);
                    }else if (selection == 2){
                        System.out.print("Marka Id'si girin : ");
                        int brandId = scanner.nextInt();
                        manager.listProductWihBrand(brandId);
                    }else {
                        System.out.println("Hatalı değer girdiniz....");
                    }
                    break;
                case 0:
                    System.out.println("Patika Store'dan çıkış yaptınız");
                    break;
            }
            if (input == 0){
                break;
            }
        }
    }


}
