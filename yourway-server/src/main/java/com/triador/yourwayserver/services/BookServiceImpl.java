package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.impl.BookDAO;
import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
import com.triador.yourwayserver.dao.model.ShortBookDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book save(Book book) {
        return bookDAO.save(book);
    }

    @Override
    public int delete(int id) {
        return bookDAO.delete(id);
    }

    @Override
    public List<ShortBookDescription> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book findByRussianTitle(String russianTitle) {
        return bookDAO.findByRussianTitle(russianTitle);
    }

    @Override
    public Book findById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<BookTitle> findMatchByTitlePiece(String titlePiece) {
        System.out.println(bookDAO.findMatchByTitlePiece(titlePiece));
        return bookDAO.findMatchByTitlePiece(titlePiece);
    }
}

