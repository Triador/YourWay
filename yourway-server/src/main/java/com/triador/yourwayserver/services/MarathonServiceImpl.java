package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.impl.MarathonDAO;
import com.triador.yourwayserver.dao.model.Marathon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarathonServiceImpl implements MarathonService {

    private MarathonDAO marathonDAO;

    @Autowired
    public MarathonServiceImpl(MarathonDAO marathonDAO) {
        this.marathonDAO = marathonDAO;
    }

    @Override
    public Marathon saveMarathon(Marathon marathon) {
        return marathonDAO.saveMarathon(marathon);
    }

    @Override
    public Marathon getMarathon(int marathonId) {
        return marathonDAO.getMarathon(marathonId);
    }

    @Override
    public List<Marathon> getMarathons() {
        return marathonDAO.getMarathons();
    }
}
