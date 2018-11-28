package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.model.BookTitle;
import com.triador.yourwayserver.dao.model.ShortBookDescription;
import com.triador.yourwayserver.dao.model.UserBook;
import com.triador.yourwayserver.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"books"})
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = {"/{bookId}"})
    public Book getBook(@PathVariable("bookId") int bookId,
                         @RequestParam("userId") int userId) {
        return bookService.findById(bookId, userId);
    }

    @GetMapping(path = {"/search/{titlePiece}"})
    public List<BookTitle> searchTitles(@PathVariable String titlePiece) {
        return bookService.findMatchByTitlePiece(titlePiece);
    }

    @GetMapping
    public List<ShortBookDescription> getBooks() {
        return bookService.findAll();
    }
}
