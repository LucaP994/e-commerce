package it.trinakria.ecommerce.repository;

import it.trinakria.ecommerce.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
