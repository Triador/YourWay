package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Profile;
import com.triador.yourwayserver.dao.model.UserBook;

public interface ProfileDAO {
    void save(UserBook userBook);
    UserBook findUserBookRelationship(UserBook userBook);
    Profile buildProfile(int userId);
}
