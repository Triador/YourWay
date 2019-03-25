package com.triador.yourwayserver.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "marathon")
public class Marathon {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column
    private String description;
}
