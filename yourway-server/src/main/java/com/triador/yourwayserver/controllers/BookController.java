package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.models.Book;
import com.triador.yourwayserver.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"books"})
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book create (@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping(path = {"/{id}"})
    public Book findOne(@PathVariable("id") int id) {
        return bookService.findById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public Book delete(@PathVariable("id") int id) {
        return bookService.delete(id);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

}
