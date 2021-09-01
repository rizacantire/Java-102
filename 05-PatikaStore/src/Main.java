import business.concretes.BrandManager;
import business.concretes.CategoryManager;
import business.concretes.ProductManager;
import business.concretes.StoreManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BrandManager brandManager = new BrandManager();
        StoreManager manager = new StoreManager(brandManager,new CategoryManager(),new ProductManager());
        System.out.println("PatikaStore Ürün Yönetim Sistemi !");
        System.out.println("1 - Notebook İşlemleri\n" +
                "2 - Cep Telefonu İşlemleri\n" +
                "3 - Marka Listele\n" +
                "0 - Çıkış Yap" );
        System.out.println("Markalarımız\n" +
                "----------------------");
        manager.addBrand();
        manager.listBrand();
        manager.addCategory();
        manager.addNoteBook();
        manager.addCellPhone();


        Scanner scanner = new Scanner(System.in);
        System.out.print("Yapacağınız işlemi seçiniz : ");
        int input = scanner.nextInt();
        switch (input){
            case 1:
                manager.listNoteBook();
                break;
            case 2:
                manager.listCellPhone();
                break;
            case 3:
                manager.listBrand();
                break;
            case 0:
                System.out.println("Patika Store'dan çıkış yaptınız");
                break;
        }

    }

}
