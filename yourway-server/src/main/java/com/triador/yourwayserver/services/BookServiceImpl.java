package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.impl.BookDAO;
import com.triador.yourwayserver.dao.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public int create(Book book) {
        return bookDAO.save(book);
    }

    @Override
    public List<Book> findAll() {
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
    public List<String> findMatchByTitlePiece(String titlePiece) {
        return bookDAO.findMatchByTitlePiece(titlePiece);
    }
}

