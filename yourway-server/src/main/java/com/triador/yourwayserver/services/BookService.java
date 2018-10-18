package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Book;

import java.util.List;

public interface BookService {
    int create(Book book);

    Book delete(int id);

    List<Book> findAll();

    Book findById(int id);

    List<String> findMatchByTitlePiece(String titlePiece);
}
