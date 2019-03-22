package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.Book;
import com.triador.yourwayserver.dto.response.BookTitleResponse;
import com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("SELECT new com.triador.yourwayserver.dto.response.BookTitleResponse(b.id, b.title) FROM book b " +
            "WHERE b.title LIKE %?1%")
    List<BookTitleResponse> retrieveBookTitles(String titlePart);

    @Query("SELECT new com.triador.yourwayserver.dto.response.ShortBookDescriptionResponse(b.id, b.title, b.imageLink) FROM book b")
    List<ShortBookDescriptionResponse> retrieveShortBookDescriptions();
}
