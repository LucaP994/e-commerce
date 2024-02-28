package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.model.entities.Address;
import it.trinakria.ecommerce.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepo addressRepo;

    @Override
    public Address getById(Long id) {
        return addressRepo.findById(id).get();
    }

    @Override
    public Address create(Address address) {
        addressRepo.save(address);
        return address;
    }

    @Override
    public Address update(Long id, Address address) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
