package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {

}
