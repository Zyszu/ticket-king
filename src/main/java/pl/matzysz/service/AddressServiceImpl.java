package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Address;
import pl.matzysz.repository.AddressRepository;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    public Address editAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    public List<Address> listAddress() {
        return addressRepository.findAll();
    }

    @Transactional
    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }

    @Transactional
    public Address getAddress(long id) {
        return addressRepository.getOne(id);
    }
}
