package com.example.note.service;

import com.example.note.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NoteService {
    private List<Note> notes = new ArrayList<>();

    List<Note> listAll() {
        return this.notes;
    }

    Note add(Note note) {
        note.setId(ThreadLocalRandom.current().nextLong());
        notes.add(note);
        return note;
    }

    void deleteById(long id) {
        try {
            notes.removeIf(note -> note.getId() == id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    void update(Note note) {
        Note updateNote = notes.stream().filter(noteFilter -> noteFilter.getId().equals(note.getId())).findFirst().orElseThrow();
        updateNote.setContent(note.getContent());
        updateNote.setTitle(note.getTitle());
    }

    Note getById(long id) {
        return notes.stream().filter(noteFilter -> noteFilter.getId() == id).findFirst().orElseThrow();
    }
}
