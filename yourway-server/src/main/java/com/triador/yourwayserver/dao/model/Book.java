package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column
    private String author;
    @Column(name = "page_amount")
    private int pageAmount;
    @Column(name = "publication_year")
    private int publicationYear;
    @Column
    private String isbn;
    @Column
    private String description;
    @Column(name = "image_link")
    private String imageLink;
}
