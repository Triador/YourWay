package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Profile;
import com.triador.yourwayserver.dao.model.UserBook;

public interface ProfileService {

    void save(UserBook userBook);

    UserBook findUserBookRelationship(UserBook userBook);

    Profile findById(int userId);
}
