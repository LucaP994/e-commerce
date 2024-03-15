package it.trinakria.ecommerce.controller;

import it.trinakria.ecommerce.config.AppConfiguration;
import it.trinakria.ecommerce.model.dto.UserDto;
import it.trinakria.ecommerce.model.entities.Product;
import it.trinakria.ecommerce.model.entities.User;
import it.trinakria.ecommerce.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConfiguration.API_BASE + "/user", produces = {"application/json"})
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> all() {
        return userService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public List<User> getByUsername(@RequestBody String username){
        return userService.getUserByUsername(username);
    }
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User newUser(@RequestBody UserDto user) {
        User newUser = userService.create(user);
        return newUser;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody UserDto newUser, @PathVariable Long id) {
        return userService.update(newUser, id);
    }

    @RequestMapping(value = "{id}/purchase/{productId}", method = RequestMethod.PUT)
    public User purchaseProduct(@PathVariable Long id, @PathVariable Long productId){
        return userService.purchaseProduct(productId, id);
    }
    @RequestMapping(value = "{id}/purchase", method = RequestMethod.POST)
    public String purchase(@PathVariable Long id){
        return userService.purchase(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }
}
