package com.triador.yourwayserver.dao.model;

import com.triador.yourwayserver.enumeration.BookStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class UserBook {

    @EmbeddedId
    private UserBookKey id;

    @Column
    private BookStatus status;

    @ManyToOne
    @JoinColumns({
          @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
          @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    })
    private List<Note> notes;
}
