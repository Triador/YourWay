package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.model.User;

public interface UserService {

    User save(User user);

    User findById(int userId);

    User findByName(String name);
}
