package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Book;
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
        return repository.save(book);
    }

    @Override
    public Book delete(int id) {
        Book book = findById(id);
        if (book != null) {
            repository.delete(book);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(int id) {
        return repository.findById(id);
    }
}

