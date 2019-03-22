package com.triador.yourwayserver.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BookTitleResponse {

    private int bookId;
    private String value;
}
