package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dto.response.ProfileResponse;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;

public interface ProfileService {

    void save(AddUserBookRequest userBook);

    AddUserBookRequest findUserBookRelationship(AddUserBookRequest userBook);

    ProfileResponse findById(int userId);

    int deleteBook(int userId, int bookId);
}
