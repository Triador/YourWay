package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;

import java.util.List;

public interface BookService {
    int create(Book book);

    List<Book> findAll();

    Book findByRussianTitle(String russianTitle);

    Book findById(int id);

    List<BookTitle> findMatchByTitlePiece(String titlePiece);
}
