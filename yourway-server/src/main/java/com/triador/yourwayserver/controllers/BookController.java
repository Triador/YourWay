package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
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

    @GetMapping(path = {"/{id}"})
    public Book findById(@PathVariable("id") int id) {
        return bookService.findById(id);
    }

    @GetMapping(path = {"/search/{titlePiece}"})
    public List<BookTitle> searchTitles(@PathVariable String titlePiece) {
        return bookService.findMatchByTitlePiece(titlePiece);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

}
