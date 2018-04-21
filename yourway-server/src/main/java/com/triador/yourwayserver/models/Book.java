package com.triador.yourwayserver.models;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setBookName(String name) {
        this.name = name;
    }
}
