package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Marathon;

import java.util.List;

public interface MarathonDAO {

    Marathon saveMarathon(Marathon marathon);

    Marathon getMarathon(int marathonId);

    List<Marathon> getMarathons();
}
