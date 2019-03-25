package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.repo.NoteRepository;
import com.triador.yourwayserver.dao.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void delete(Integer id) {
        noteRepository.deleteById(id);
    }
}
