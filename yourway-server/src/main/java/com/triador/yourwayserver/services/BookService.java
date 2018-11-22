package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
import com.triador.yourwayserver.dao.model.ShortBookDescription;
import com.triador.yourwayserver.dao.model.UserBook;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<ShortBookDescription> findAll();

    Book findById(int bookId, int userId);

    List<BookTitle> findMatchByTitlePiece(String titlePiece);
}
