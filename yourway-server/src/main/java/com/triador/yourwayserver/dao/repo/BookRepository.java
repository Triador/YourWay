package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dto.response.BookTitleResponse;
import com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT new com.triador.yourwayserver.dto.response.BookTitleResponse(b.id, b.title) FROM Book b " +
            "WHERE b.title LIKE %?1%")
    List<BookTitleResponse> retrieveBookTitles(String titlePart);

    @Query("SELECT new com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse(b.id, b.title, b.imageLink) FROM Book b")
    List<ShortBookDescriptionResponse> retrieveShortBookDescriptions();
}
