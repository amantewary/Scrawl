package com.example.amantewary.scrawl.NoteState;

import android.content.Context;
import android.content.Intent;

import com.example.amantewary.scrawl.ViewNotesActivity;

public class ViewNote implements NoteState {
    @Override
    public void runViewNoteActivity(NoteContext noteContext, Context context, Integer noteid) {
        Intent intent = new Intent(context, ViewNotesActivity.class);
        intent.putExtra("noteid", noteid);
        context.startActivity(intent);
    }
}
