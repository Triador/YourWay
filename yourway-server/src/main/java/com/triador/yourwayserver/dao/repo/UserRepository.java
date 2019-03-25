package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByName(String name);
}
