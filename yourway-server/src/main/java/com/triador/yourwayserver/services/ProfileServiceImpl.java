package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.impl.ProfileDAO;
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
    public UserBook findByIds(UserBook userBook) {
        return profileDAO.findByIds(userBook);
    }
}
