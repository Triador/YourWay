package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.repo.MarathonRepository;
import com.triador.yourwayserver.dao.model.Marathon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarathonServiceImpl implements MarathonService {

    private MarathonRepository marathonDAO;

    @Autowired
    public MarathonServiceImpl(MarathonRepository marathonDAO) {
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
