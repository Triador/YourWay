package com.triador.yourwayserver.dto.response;

import com.triador.yourwayserver.dao.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileResponse {

    private String username;
    private String imageLink;
    private List<Book> progressBooks;
    private List<Book> futureBooks;
    private List<Book> finishedBooks;
}
