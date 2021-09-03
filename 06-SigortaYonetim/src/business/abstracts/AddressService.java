package business.abstracts;

import entities.addresses.Address;
import entities.addresses.BusinessAddress;
import entities.addresses.HomeAddress;

import java.util.List;

public interface AddressService {
    void addHomeAddress(HomeAddress address);
    void deleteHomeAddress(HomeAddress address);
    List<Address> getAddresses();
    void addBusinessAddress(BusinessAddress businessAddress);
    void deleteBusinessAddress(BusinessAddress businessAddress);

}
