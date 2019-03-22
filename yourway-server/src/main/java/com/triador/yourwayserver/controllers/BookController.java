package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dto.request.BookRequest;
import com.triador.yourwayserver.dto.response.BookTitleResponse;
import com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse;
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
    public Book getBook(@RequestBody BookRequest bookRequest) {
        return bookService.getBook(bookRequest);
    }

    @GetMapping(path = {"/search/{titlePiece}"})
    public List<BookTitleResponse> searchTitles(@PathVariable String titlePiece) {
        return bookService.findMatchByTitlePiece(titlePiece);
    }

    @GetMapping
    public List<ShortBookDescriptionResponse> getBooks() {
        return bookService.findAll();
    }
}
