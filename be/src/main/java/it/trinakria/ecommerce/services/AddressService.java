package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.entities.Address;

public interface AddressService {
    Address getById(Long id);
    Address create(Address address);
    Address update(Long id, Address address);
    void delete(Long id);
}
