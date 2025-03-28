package pl.matzysz.service;


import pl.matzysz.domain.Address;

import java.util.List;

public interface AddressService {
    public Address addAddress(Address address);
    public Address editAddress(Address address);
    public List<Address> listAddress();
    public void deleteAddress(Address address);
    public Address getAddress(long id);
}
