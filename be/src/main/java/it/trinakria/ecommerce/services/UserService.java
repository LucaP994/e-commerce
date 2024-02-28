package it.trinakria.ecommerce.services;


import it.trinakria.ecommerce.entities.Address;
import it.trinakria.ecommerce.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User create(User user);
    User update(Long id,User user);
    void delete(Long id);

}
