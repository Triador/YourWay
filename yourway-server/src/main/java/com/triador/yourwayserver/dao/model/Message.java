package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "date_sent")
    private LocalDate dateSent;
    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
