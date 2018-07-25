package com.example.amantewary.scrawl.NoteState;

public class NoteContext {
    private NoteState noteState;

    public NoteContext() {
        this.noteState = null;
    }

    public NoteState getNoteState() {
        return noteState;
    }

    public void setNoteState(NoteState noteState) {
        this.noteState = noteState;
    }
}
