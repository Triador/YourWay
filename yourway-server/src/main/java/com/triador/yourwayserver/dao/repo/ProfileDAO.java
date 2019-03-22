package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dto.response.ProfileResponse;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;

public interface ProfileDAO {

    void save(AddUserBookRequest userBook);

    AddUserBookRequest findUserBookRelationship(AddUserBookRequest userBook);

    ProfileResponse buildProfile(int userId);

    int deleteBook(int userId, int bookId);
}
