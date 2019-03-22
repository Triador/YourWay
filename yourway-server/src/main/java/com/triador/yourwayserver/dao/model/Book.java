package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private int pageAmount;
    @Column
    private int publicationYear;
    @Column
    private String isbn;
    @Column
    private String description;
    @Column
    private String imageLink;
}
