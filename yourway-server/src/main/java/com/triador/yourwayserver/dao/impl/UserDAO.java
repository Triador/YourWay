package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.User;

import java.util.List;

public interface UserDAO {

    User save(User user);

    int delete(int userId);

    List<User> findAll();

    User findById(int userId);

    User findByName(String name);
}
