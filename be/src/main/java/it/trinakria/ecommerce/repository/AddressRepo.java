package it.trinakria.ecommerce.repository;

import it.trinakria.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
