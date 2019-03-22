package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.UserBook;
import com.triador.yourwayserver.dao.model.UserBookKey;
import org.springframework.data.repository.CrudRepository;

public interface UserBookRepository extends CrudRepository<UserBook, UserBookKey> {
}
