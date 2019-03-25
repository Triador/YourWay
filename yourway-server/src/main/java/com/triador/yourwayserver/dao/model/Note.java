package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "user_id")
    private Integer userId;
    @Column
    private String text;
}
