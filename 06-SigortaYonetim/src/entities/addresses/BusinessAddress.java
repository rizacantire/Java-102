package entities.addresses;

public class BusinessAddress implements Address{
    private String address;

    public BusinessAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
}
