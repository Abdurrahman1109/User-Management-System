package com.example.UserManagementSystem.service;

import com.example.UserManagementSystem.entity.User;
import com.example.UserManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public  List<User> allUsers(){
        return (List<User>) userRepository.findAll();
    };

    public User addUser(User user){
        User u1=userRepository.save(user);
        return u1;
    };

    public int deleteUserById(int id){
        userRepository.deleteById(id);
        return id;
    };

    public int userById(int id){
        userRepository.findById(id);
        return id;
    }
}
