package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
import com.triador.yourwayserver.dao.model.ShortBookDescription;
import com.triador.yourwayserver.dao.model.UserBook;

import java.util.List;

public interface BookDAO {
    Book save(Book book);

    List<ShortBookDescription> findAll();

    Book findById(UserBook userBook);

    List<BookTitle> findMatchByTitlePiece(String titlePiece);
}
