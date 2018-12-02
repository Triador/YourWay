package com.triador.yourwayserver.controllers;

import com.triador.yourwayserver.dao.model.Note;
import com.triador.yourwayserver.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"books/{bookId}/notes"})
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public Note saveNote(@RequestBody Note note) {
        return noteService.save(note);
    }

    @DeleteMapping(path = "/{noteId}")
    public void deleteNote(@PathVariable int bookId, @PathVariable int noteId) {
        noteService.delete(noteId, bookId);
    }

}
