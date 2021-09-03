package business.concretes;

import business.abstracts.AddressService;
import entities.accounts.User;
import entities.addresses.Address;
import entities.addresses.BusinessAddress;
import entities.addresses.HomeAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressManager implements AddressService {
    private User user;
    private ArrayList<Address> addresses;

    public AddressManager(User user) {
        this.user = user;
    }

    @Override
    public void addHomeAddress(HomeAddress homeAddress) {
        addresses.add(homeAddress);
        user.setAddresses(addresses);

    }

    @Override
    public void deleteHomeAddress(HomeAddress homeAddress) {
        addresses.remove(homeAddress);
        user.setAddresses(addresses);
    }


    @Override
    public List<Address> getAddresses() {

        return this.addresses;
    }

    @Override
    public void addBusinessAddress(BusinessAddress businessAddress) {
        addresses.add(businessAddress);
        user.setAddresses(addresses);
    }

    @Override
    public void deleteBusinessAddress(BusinessAddress businessAddress) {
        addresses.remove(businessAddress);
        user.setAddresses(addresses);

    }

}
