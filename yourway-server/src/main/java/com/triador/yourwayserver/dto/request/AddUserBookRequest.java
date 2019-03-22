package com.triador.yourwayserver.dto.request;

import com.triador.yourwayserver.enumeration.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AddUserBookRequest {

    private int userId;
    private int bookId;
    private BookStatus bookStatus;
}
