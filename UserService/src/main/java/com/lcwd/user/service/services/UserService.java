package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    // user operations

    // create user
    User saveUser(User user);

    // get all user
    List<User> getAllUser();

    // get single user with given userId
    User getSingleUser(String userId);

    // TODO: delete
    // TODO: update
}
