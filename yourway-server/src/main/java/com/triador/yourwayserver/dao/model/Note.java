package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Note {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int bookId;
    @Column
    private int userId;
    @Column
    private String text;
}
