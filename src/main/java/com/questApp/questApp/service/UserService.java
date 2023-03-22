package com.questApp.questApp.service;

import com.questApp.questApp.entity.User;
import com.questApp.questApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User newUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }else {
            return null;
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
