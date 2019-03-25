package com.triador.yourwayserver.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BookTitleResponse {

    private Integer bookId;
    private String value;
}
