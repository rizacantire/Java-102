package entities.accounts;

import business.abstracts.Insurance;
import entities.addresses.Address;

import java.util.List;
import java.util.Objects;

public abstract class Account implements Comparable<Account> {
    private User user;
    private AuthenticationStatus authenticationStatus;
    private List<Insurance> insurances;

    public Account() {

    }
    public abstract void insuranceAdd(Insurance insurance);

    public AuthenticationStatus getAuthenticationStatus() {
        return authenticationStatus;
    }

    public Account(User user) {
        this.user = user;
    }

    public void setAuthenticationStatus(AuthenticationStatus authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }


    final void showUserInfo() {
        System.out.println(user.getFirstName()+" "+user.getLastName()+" "+user.getEMail()+" "+user.getProfession()+" "+user.getAge());
        for(Address address : this.user.getAddresses())
            System.out.println(address);
    }



    public enum AuthenticationStatus{
        SUCCESS,
        FAIL
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    @Override
    public int compareTo(Account o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

}
