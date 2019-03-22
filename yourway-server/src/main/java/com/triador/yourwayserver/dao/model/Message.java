package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public class Message {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private LocalDate dateSent;
    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
