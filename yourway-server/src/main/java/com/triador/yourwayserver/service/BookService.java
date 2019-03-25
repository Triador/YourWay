package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.model.*;
import com.triador.yourwayserver.dao.model.BookResponse;
import com.triador.yourwayserver.dao.repo.BookRepository;
import com.triador.yourwayserver.dao.repo.UserBookRepository;
import com.triador.yourwayserver.dto.response.BookTitleResponse;
import com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse;
import com.triador.yourwayserver.enumeration.BookStatus;
import com.triador.yourwayserver.exception.CommonException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private UserBookRepository userBookRepository;

    //todo delete this variable and retrieve token from jwt
    private static final Integer ID_FROM_JWT_TOKEN = 1;

    public BookService(BookRepository bookRepository,
                       UserBookRepository userBookRepository) {
        this.bookRepository = bookRepository;
        this.userBookRepository = userBookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<ShortBookDescriptionResponse> getShortBookDescriptions() {
        return bookRepository.retrieveShortBookDescriptions();
    }

    public BookResponse getBook(Integer bookId) {
        UserBookKey userBookKey = new UserBookKey(ID_FROM_JWT_TOKEN, bookId);
        Optional<UserBook> fullBookInfo = userBookRepository.findById(userBookKey);
        BookResponse bookResponse = new BookResponse();
        if (fullBookInfo.isPresent()) {
            BeanUtils.copyProperties(bookResponse, fullBookInfo.get().getBook());
            bookResponse.setDisable(true);
            bookResponse.setStatus(fullBookInfo.get().getStatus());
            bookResponse.setNotes(fullBookInfo.get().getNotes());
        } else {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new CommonException("book not found"));
            BeanUtils.copyProperties(bookResponse, book);
            bookResponse.setStatus(BookStatus.UNLOCK);
        }

        return bookResponse;
    }

    public List<BookTitleResponse> getBookTitles(String titlePart) {
        return bookRepository.retrieveBookTitles(titlePart);
    }
}

