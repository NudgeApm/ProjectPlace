package com.nla.service;

import com.nla.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public void add(User user) {
        //Persist the user object here.
        System.out.println("User added successfully");
    }
}
