package it.trinakria.ecommerce.controller;

import it.trinakria.ecommerce.entities.Product;
import it.trinakria.ecommerce.entities.User;
import it.trinakria.ecommerce.repository.UserRepo;
import it.trinakria.ecommerce.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/users")
    public List<User> all() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepo.findById(id).get();
    }

    @PostMapping("/user")
    public User newUser(@RequestBody User user) {
        User newUser = userService.create(user);
        return newUser;
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setProfileImg(newUser.getProfileImg());
                    user.setBoughtProducts(newUser.getBoughtProducts());
                    return userRepo.save(user);
                }).orElseGet(() -> {
                    newUser.setId(id);
                    return userRepo.save(newUser);
                });
    }

    @PutMapping("user/{id}/purchase")
    public User purchaseProduct(@RequestBody Product product, @PathVariable Long id){
        return userRepo.findById(id)
                .map(user ->{
                    Set<Product> products = user.getBoughtProducts();
                    products.add(product);
                    user.setBoughtProducts(products);
                    return userRepo.save(user);
                }).get();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepo.deleteById(id);
    }
}
