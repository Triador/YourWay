package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.model.Marathon;

import java.util.List;

public interface MarathonService {

    Marathon saveMarathon(Marathon marathon);

    Marathon getMarathon(int marathonId);

    List<Marathon> getMarathons();
}
