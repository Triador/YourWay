package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.repo.ProfileDAO;
import com.triador.yourwayserver.dto.response.ProfileResponse;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileDAO profileDAO;

    @Autowired
    public ProfileServiceImpl(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    public void save(AddUserBookRequest userBook) {
        profileDAO.save(userBook);
    }

    @Override
    public AddUserBookRequest findUserBookRelationship(AddUserBookRequest userBook) {
        return profileDAO.findUserBookRelationship(userBook);
    }

    @Override
    public ProfileResponse findById(int userId) {
        return profileDAO.buildProfile(userId);
    }

    @Override
    public int deleteBook(int userId, int bookId) {
        return profileDAO.deleteBook(userId, bookId);
    }
}
