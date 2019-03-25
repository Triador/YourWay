package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String role;
    @Column(name = "image_link")
    private String imageLink;
}
