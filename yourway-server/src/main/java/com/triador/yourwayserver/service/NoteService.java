package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.model.Note;

public interface NoteService {

    Note save(Note note);

    int delete(int noteId, int bookId);
}
