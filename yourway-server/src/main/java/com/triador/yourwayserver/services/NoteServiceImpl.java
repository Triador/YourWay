package com.triador.yourwayserver.services;

import com.triador.yourwayserver.dao.impl.NoteDAO;
import com.triador.yourwayserver.dao.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteDAO noteDAO;

    @Autowired
    public NoteServiceImpl(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    public Note save(Note note) {
        return noteDAO.save(note);
    }

    @Override
    public int delete(int noteId, int bookId) {
        return noteDAO.delete(noteId, bookId);
    }
}
