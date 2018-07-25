package com.example.amantewary.scrawl.NoteState;

import android.content.Context;
import android.content.Intent;

import com.example.amantewary.scrawl.ViewSharedNoteActivity;

public class SharedNote implements NoteState {

    @Override
    public void runViewNoteActivity(NoteContext noteContext, Context context, Integer noteid) {
        Intent intent = new Intent(context, ViewSharedNoteActivity.class);
        intent.putExtra("noteid", noteid);
        context.startActivity(intent);
    }

}
