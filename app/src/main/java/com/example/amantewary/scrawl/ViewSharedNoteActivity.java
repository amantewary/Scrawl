package com.example.amantewary.scrawl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.amantewary.scrawl.BaseActivities.ViewNoteBaseActivity;

public class ViewSharedNoteActivity extends ViewNoteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = "ViewSharedNoteActivity";

        btn_collaborate.setVisibility(View.GONE);
        btn_share.setVisibility(View.GONE);
        btn_delete.setText("Cancel collaboration");
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
            case R.id.btn_delete:
                cancelShare();
                break;
        }
    }

    public void cancelShare() {

    }

}
