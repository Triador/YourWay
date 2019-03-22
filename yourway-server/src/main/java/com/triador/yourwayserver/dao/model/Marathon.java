package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public class Marathon {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private String description;
}
