package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.impl.ProfileDAO;
import com.triador.yourwayserver.dao.model.Profile;
import com.triador.yourwayserver.dao.model.UserBook;
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
    public void save(UserBook userBook) {
        profileDAO.save(userBook);
    }

    @Override
    public UserBook findUserBookRelationship(UserBook userBook) {
        return profileDAO.findUserBookRelationship(userBook);
    }

    @Override
    public Profile findById(int userId) {
        return profileDAO.buildProfile(userId);
    }

    @Override
    public int deleteBook(int userId, int bookId) {
        return profileDAO.deleteBook(userId, bookId);
    }
}
