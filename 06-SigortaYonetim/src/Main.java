import business.concretes.AccountManager;
import business.concretes.CarInsurance;
import business.concretes.InvalidAuthenticationException;
import entities.accounts.Account;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{
       AccountManager manager = new AccountManager();

        try {
            manager.login("ali@gmail.com","passs");
        }catch (InvalidAuthenticationException e){
            System.out.println(e.getError());

        }

        AccountManager accountManager = new AccountManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("E-posta adresini giriniz: ");
        String email = scanner.nextLine();
        System.out.print("Şifre giriniz: ");
        String password = scanner.nextLine();
        Account account = accountManager.login(email,password);
        System.out.println("Hoşgeldin "+account.getUser().getFirstName()+" "+account.getUser().getLastName());


    }
}
