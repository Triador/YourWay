package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Book;

import java.util.List;

public interface BookDAO {
    int save(Book book);

    int delete(Book book);

    List<Book> findAll();

    Book findById(int id);

    List<Book> findMatchByTitlePiece(String titlePiece);
}
