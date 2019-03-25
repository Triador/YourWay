package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.BookResponse;
import com.triador.yourwayserver.dto.response.BookTitleResponse;
import com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse;
import com.triador.yourwayserver.service.BookService;
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
    public BookResponse getBook(@PathVariable Integer bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping(path = {"/search/{titlePart}"})
    public List<BookTitleResponse> searchTitles(@PathVariable String titlePart) {
        return bookService.getBookTitles(titlePart);
    }

    @GetMapping
    public List<ShortBookDescriptionResponse> getBooks() {
        return bookService.getShortBookDescriptions();
    }
}
