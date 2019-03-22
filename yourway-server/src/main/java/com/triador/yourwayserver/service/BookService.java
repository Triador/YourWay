package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.model.BookResponse;
import com.triador.yourwayserver.dao.model.UserBook;
import com.triador.yourwayserver.dao.model.UserBookKey;
import com.triador.yourwayserver.dao.repo.BookRepository;
import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dao.repo.UserBookRepository;
import com.triador.yourwayserver.dto.request.BookRequest;
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

    public BookService(BookRepository bookRepository,
                       UserBookRepository userBookRepository) {
        this.bookRepository = bookRepository;
        this.userBookRepository = userBookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<ShortBookDescriptionResponse> getShortBookDescriptions() {
        return bookDAO.findAll();
    }

    public BookResponse getBook(BookRequest bookRequest) {
        Book book = bookRepository.findById(bookRequest.getBookId())
                .orElseThrow(() -> new CommonException("book not found"));

        BookResponse bookResponse = new BookResponse();
        BeanUtils.copyProperties(book,bookResponse);

        UserBookKey userBookKey = new UserBookKey(bookRequest.getUserId(), bookRequest.getBookId());
        Optional<UserBook> userBook = userBookRepository.findById(userBookKey);
        if (userBook.isPresent()) {
            bookResponse.setDisable(true);
            bookResponse.setStatus(userBook.get().getStatus());
            bookResponse.setNotes(userBook.get().getNotes());
        } else {
            bookResponse.setStatus(BookStatus.UNLOCK);
        }

        return bookResponse;
    }

    @Override
    public List<BookTitleResponse> findMatchByTitlePiece(String titlePiece) {
        System.out.println(bookDAO.findByTitleContaining(titlePiece));
        return bookDAO.findByTitleContaining(titlePiece);
    }
}

