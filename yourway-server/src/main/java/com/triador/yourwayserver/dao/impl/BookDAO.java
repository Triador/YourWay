package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Book;

import java.util.List;

public interface BookDAO {
    int save(Book book);

    int delete(Book book);

    List<Book> findAll();

    Book findByRussianTitle(String russianTitle);

    Book findById(int id);

    List<String> findMatchByTitlePiece(String titlePiece);
}
