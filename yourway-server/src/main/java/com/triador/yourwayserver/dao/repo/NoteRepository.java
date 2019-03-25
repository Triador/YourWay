package com.triador.yourwayserver.dao.repo;

import com.triador.yourwayserver.dao.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Integer> {

}
