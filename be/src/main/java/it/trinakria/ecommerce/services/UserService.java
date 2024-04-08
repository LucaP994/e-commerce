package it.trinakria.ecommerce.services;


import it.trinakria.ecommerce.model.dto.UserDto;
import it.trinakria.ecommerce.model.entities.Product;
import it.trinakria.ecommerce.model.entities.User;
import it.trinakria.ecommerce.utily.ExceptionHanlder;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getUserById(Long id);
    List<User> getUserByUsername(String username);
    User create(UserDto user) throws ExceptionHanlder;
    User update(UserDto user,Long id);
    User purchaseProduct(Long productId, Long id);
    String purchase(Long id);
    void delete(Long id);

}
