package com.triador.yourwayserver.dao.model;

import com.triador.yourwayserver.enumeration.BookStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
public class BookResponse {

    private Integer id;
    private String title;
    private String author;
    private Integer pageAmount;
    private Integer publicationYear;
    private String isbn;
    private String description;
    private String imageLink;
    private Boolean disable;
    private BookStatus status;
    private List<Note> notes;
}
