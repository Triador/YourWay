package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.Marathon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarathonRepository extends CrudRepository<Marathon, Integer> {

    @Override
    List<Marathon> findAll();
}
