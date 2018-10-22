package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;

import java.util.List;

public interface BookDAO {
    int save(Book book);

    int delete(Book book);

    List<Book> findAll();

    Book findByRussianTitle(String russianTitle);

    Book findById(int id);

    List<BookTitle> findMatchByTitlePiece(String titlePiece);
}
