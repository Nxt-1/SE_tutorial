package be.uantwerpen.fti.se.tutorial.service;

import be.uantwerpen.fti.se.tutorial.model.User;
import be.uantwerpen.fti.se.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public void saveSomeAttributes(final User user) {
        User tempUser = user.getId() == null?null: userRepository.findById( user.getId()).orElse(null);
        if (tempUser != null){
            tempUser.setRoles(user.getRoles());
            tempUser.setUserName(user.getUserName());
            userRepository.save(tempUser);
        }
        else{
            userRepository.save(user);
        }
    }

    public User findByUserName(String userName){
        return this.userRepository.findByUserName(userName);
    }

    public void save(final User role) {
        this.userRepository.save(role);
    }
}

