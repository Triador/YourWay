package com.triador.yourwayserver.repositores;

import com.triador.yourwayserver.dao.model.Book;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookRepository extends Repository<Book, Integer> {

    void delete(Book book);

    List<Book> findAll();

    Book findById(int id);

    Book save(Book book);
}
