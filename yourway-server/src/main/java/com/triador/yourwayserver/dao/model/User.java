package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private String imageLink;
}
