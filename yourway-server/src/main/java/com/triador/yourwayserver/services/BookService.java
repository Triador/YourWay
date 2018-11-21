package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
import com.triador.yourwayserver.dao.model.ShortBookDescription;

import java.util.List;

public interface BookService {
    Book save(Book book);

    int delete(int id);

    List<ShortBookDescription> findAll();

    Book findByRussianTitle(String russianTitle);

    Book findById(int id);

    List<BookTitle> findMatchByTitlePiece(String titlePiece);
}
