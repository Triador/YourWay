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

    @GetMapping(path = {"/{russianTitle}"})
    public Book findOne(@PathVariable("russianTitle") String russianTitle) {
        return bookService.findByRussianTitle(russianTitle);
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
