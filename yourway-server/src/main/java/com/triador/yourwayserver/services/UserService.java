package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(int id);

    User findByName(String name);
}
