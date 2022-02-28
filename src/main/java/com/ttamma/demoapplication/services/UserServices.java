package com.ttamma.demoapplication.services;

import com.ttamma.demoapplication.model.User;
import com.ttamma.demoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository repo;

    public User findUserByUsername(String username){
        return repo.getUserByUsername(username);
    }
}
