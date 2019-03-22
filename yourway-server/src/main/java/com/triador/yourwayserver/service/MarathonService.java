package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.repo.MarathonRepository;
import com.triador.yourwayserver.dao.model.Marathon;
import com.triador.yourwayserver.enumeration.ErrorMessage;
import com.triador.yourwayserver.exception.CommonException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarathonService {

    private MarathonRepository marathonRepository;

    public MarathonService(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    public Marathon saveMarathon(Marathon marathon) {
        return marathonRepository.save(marathon);
    }

    public Marathon getMarathon(int id) {
        return marathonRepository.findById(id).orElseThrow(() -> new CommonException(ErrorMessage.ERROR_NOT_FOUND.getMessage()));
    }

    public List<Marathon> getMarathons() {
        return marathonRepository.findAll();
    }
}
