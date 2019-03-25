package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.UserBook;
import com.triador.yourwayserver.dao.model.UserBookKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends CrudRepository<UserBook, UserBookKey> {

    List<UserBook> findByIdUserId(Integer id);
}
