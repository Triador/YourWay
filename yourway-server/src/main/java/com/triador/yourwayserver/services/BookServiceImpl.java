package com.triador.yourwayserver.services;

import com.triador.yourwayserver.models.Book;
import com.triador.yourwayserver.repositores.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book create(Book book) {
        return null;
    }

    @Override
    public Book delete(int id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book findById() {
        return null;
    }

    @Override
    public Book update() {
        return null;
    }
}
