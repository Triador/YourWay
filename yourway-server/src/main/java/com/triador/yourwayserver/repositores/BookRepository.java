package com.triador.yourwayserver.repositores;

import com.triador.yourwayserver.models.Book;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookRepository extends Repository<Book, Integer> {

    void delete();

    List<Book> findAll();

    Book findOne(int id);

    Book save(Book book);
}
