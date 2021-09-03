package business.concretes;

import entities.accounts.Account;
import entities.accounts.Enterprise;
import entities.accounts.Individual;
import entities.accounts.User;

import java.util.Objects;
import java.util.TreeSet;

public class AccountManager {

    private TreeSet<Account> accounts;

    public AccountManager() {
        accounts = new TreeSet<>();
        var a = (new Individual(new User("Ali","Çakar","ali@gmail.com","pass","memur",34)));
        var b = (new Enterprise(new User("Mehmet","Serdar","mehmet@gmail.com","pass","mühendis",41)));
        accounts.add(a);
        accounts.add(b);
        this.setAccounts(accounts);
    }
    public Account login(String mail, String pass) throws InvalidAuthenticationException {
        var account = accounts.stream().filter(a->a.getUser().getEMail().equals(mail)&&a.getUser().getPassword().equals(pass)).findAny();
        if (account.isEmpty()){
            throw new InvalidAuthenticationException();
        }else {
            account.get().setAuthenticationStatus(Account.AuthenticationStatus.SUCCESS);
            System.out.println("Başarılı şekilde giriş yaptınız");
        }
        System.out.println(account.get().getAuthenticationStatus());
        return account.get();
    }

    public TreeSet<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(TreeSet<Account> accounts) {
        this.accounts = accounts;
    }

    public void showInfo(){

    }



}
