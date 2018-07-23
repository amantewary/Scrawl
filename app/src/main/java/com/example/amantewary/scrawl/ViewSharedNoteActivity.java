package com.example.amantewary.scrawl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.amantewary.scrawl.BaseActivities.ViewNoteBaseActivity;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

public class ViewSharedNoteActivity extends ViewNoteBaseActivity {

    private ShareHandler shareHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = "ViewSharedNoteActivity";

        btn_collaborate.setVisibility(View.GONE);
        btn_share.setVisibility(View.GONE);
        btn_delete.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.btn_edit:
                Intent intent = new Intent(ViewSharedNoteActivity.this, EditNotesActivity.class);
                intent.putExtra("noteid", noteId);
                startActivity(intent);
                break;
        }
    }

}
