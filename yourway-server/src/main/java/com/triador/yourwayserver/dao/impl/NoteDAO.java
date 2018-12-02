package com.triador.yourwayserver.dao.impl;

import com.triador.yourwayserver.dao.model.Note;

public interface NoteDAO {

    Note save(Note note);

    int delete(int noteId, int bookId);
}
