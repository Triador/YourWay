package com.triador.yourwayserver.dao.model;

import com.triador.yourwayserver.enumeration.BookStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
public class BookResponse {

    private int id;
    private String title;
    private String author;
    private int pageAmount;
    private int publicationYear;
    private String isbn;
    private String description;
    private String imageLink;
    private boolean disable;
    private BookStatus status;
    private List<Note> notes;
}
