package com.triador.yourwayserver.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ShortBookDescriptionResponse {

    private Integer id;
    private String title;
    private String imageLink;
}
