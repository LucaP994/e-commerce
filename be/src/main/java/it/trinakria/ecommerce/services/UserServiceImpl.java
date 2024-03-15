package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.model.dto.UserDto;
import it.trinakria.ecommerce.model.entities.KcUser;
import it.trinakria.ecommerce.model.entities.Product;
import it.trinakria.ecommerce.model.entities.User;
import it.trinakria.ecommerce.repository.ProductRepo;
import it.trinakria.ecommerce.repository.UserRepo;
import it.trinakria.ecommerce.utily.ExceptionHanlder;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleDatabaseException;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    KcService kcService;

    @Override
    public List<User> getAll() {
        List<User> userLists = new ArrayList<>();
        for (User u : userRepo.findAll()) {
            userLists.add(u);
        }
        return userLists;
    }

    @Override
    public User getUserById(Long id) {
        User newUser = userRepo.findById(id).orElseGet(() -> {
            log.error("No user found for id {}", id);
            return new User();
        });
        return newUser;
    }

    @Override
    public List<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User create(UserDto user) {
        User newUser = new User();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        try {
            Date dateOfBirth = formatter.parse(user.getDateOfBirthStr());
            newUser.setUsername(user.getUsername());
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setRole(user.getRole());
            newUser.setProfileImg(user.getProfileImg());
            newUser.setAddress(user.getAddress());
            newUser.setDateOfBirth(dateOfBirth);
            addressService.create(newUser.getAddress());
            User saved = userRepo.saveAndFlush(newUser);
            KcUser kcUser = new KcUser();
            List<String> roles = new ArrayList<>();
            roles.add(String.valueOf(saved.getRole()).toLowerCase());
            if(!roles.contains("user")){
                roles.add("user");
            }
            kcUser.setUserName(saved.getUsername());
            kcUser.setPassword(saved.getPassword());
            kcUser.setEmailId(saved.getEmail());
            kcUser.setFirstname(saved.getFirstname());
            kcUser.setLastName(saved.getLastname());
            kcUser.setRoles(roles);
            kcService.addUser(kcUser);
            kcService.setRole(roles, saved.getEmail());

        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }catch (Exception ex){
            log.error("Attenzione! Username o Email già in uso.");
            newUser = null;
        }
        return newUser;
    }

    @Override
    public User update(UserDto user, Long id) {
        User userToUpdate = userRepo.findById(id).get();
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setProfileImg(user.getProfileImg());
        return userRepo.saveAndFlush(userToUpdate);
    }

    @Override
    public User purchaseProduct(Long productId, Long id) {
        Product product = productRepo.findById(productId).get();
        return userRepo.findById(id)
                .map(user -> {
                    List<Product> products = user.getBoughtProducts();
                    products.add(product);
                    user.setBoughtProducts(products);
                    return userRepo.save(user);
                }).get();
    }

    @Override
    public String purchase(Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    List<Product> products = user.getBoughtProducts();
                    Double amount = 0.0;
                    for(Product p : products){
                        amount += p.getPrice();
                    }
                    String res = "Purchased "+products.size()+" items for a value of "+amount+" €.";
                    products.removeAll(products);
                    user.setBoughtProducts(products);
                    userRepo.save(user);
                    return res;
                }).get();
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

}
