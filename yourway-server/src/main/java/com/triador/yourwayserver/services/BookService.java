package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);

    Book delete(int id);

    List<Book> findAll();

    Book findById(int id);
}
