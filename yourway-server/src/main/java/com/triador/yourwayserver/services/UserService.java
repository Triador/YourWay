package com.triador.yourwayserver.services;

import com.triador.yourwayserver.models.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User delete(int id);

    List<User> findAll();

    User findById(int id);

    User findOne(String name);
}
