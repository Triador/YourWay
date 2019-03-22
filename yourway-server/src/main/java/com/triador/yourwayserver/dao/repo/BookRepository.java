package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByTitleContaining(String titlePiece);
}
