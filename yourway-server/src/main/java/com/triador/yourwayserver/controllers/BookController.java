package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.Book;
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
    public Book create(@RequestBody Book book) {
        int id =  bookService.create(book);
        return bookService.findById(id);
    }

//    @GetMapping(path = {"/{id}"})
//    public Book findOne(@PathVariable("id") int id) {
//        return bookService.findById(id);
//    }

    @DeleteMapping(path = {"/{id}"})
    public Book delete(@PathVariable("id") int id) {
        return bookService.delete(id);
    }

    @GetMapping(path = {"/search/{titlePiece}"})
    public List<String> searchTitles(@PathVariable String titlePiece) {
        return bookService.findMatchByTitlePiece(titlePiece);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

}
