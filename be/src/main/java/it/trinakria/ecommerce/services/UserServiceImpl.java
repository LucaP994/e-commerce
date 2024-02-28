package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.entities.Address;
import it.trinakria.ecommerce.entities.KcUser;
import it.trinakria.ecommerce.entities.User;
import it.trinakria.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    KcService kcService;
    @Override
    public List<User> getAll() {
        List<User> userLists = new ArrayList<>();
        for(User u: userRepo.findAll()){
            userLists.add(u);
        }
        return userLists;
    }

    @Override
    public User getUserById(Long id) {
        User user = new User();
        return userRepo.findById(id).get();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).get();
    }

    @Override
    public User create(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        newUser.setProfileImg(user.getProfileImg());
        newUser.setAddress(user.getAddress());
        addressService.create(newUser.getAddress());
        userRepo.save(newUser);
        KcUser kcUser = new KcUser();
        kcUser.setUserName(newUser.getUsername());
        kcUser.setPassword(newUser.getPassword());
        kcUser.setEmailId(newUser.getEmail());
        kcUser.setFirstname(newUser.getFirstname());
        kcUser.setLastName(newUser.getLastname());
        kcService.addUser(kcUser);
        return newUser;
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


}
