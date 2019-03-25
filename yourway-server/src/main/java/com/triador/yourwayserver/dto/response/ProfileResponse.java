package com.triador.yourwayserver.dto.response;

import com.triador.yourwayserver.dao.model.BookResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(builderClassName = "ProfileBuilder")
public class ProfileResponse {

    private String username;
    private String imageLink;
    private List<BookResponse> progressBookResponses;
    private List<BookResponse> futureBookResponses;
    private List<BookResponse> finishedBookResponses;
}
