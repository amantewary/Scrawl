package com.example.amantewary.scrawl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.Notes.INoteResponse;
import com.example.amantewary.scrawl.Adapters.FilteredNotesListAdapter;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.l4digital.fastscroll.FastScrollRecyclerView;

import java.util.List;

public class FilteredNotesActivity extends AppCompatActivity {

    private static final String TAG = "FilteredNotesActivity";
    private FastScrollRecyclerView notesListView;
    private FilteredNotesListAdapter filteredNotesListAdapter;
    private Toolbar toolbar;
    private String label_name;

    protected void viewBinder() {
        toolbar = findViewById(R.id.toolbar);
        notesListView = findViewById(R.id.viewFilteredNoteList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_notes);
        viewBinder();
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            label_name = bundle.getString("label_name");
        }
        Log.e(TAG, "LABEL_NAME" + label_name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFilteredNotesList(label_name);
    }

    public void populateFilteredNotesList(String label_name) {
        NotesRequestHandler request = new NotesRequestHandler();

        try {
            request.getNotesListByLabel(FilteredNotesActivity.this, label_name, new INoteResponse() {
                @Override
                public void onSuccess(@NonNull List<NoteHandler> note) {
                    Log.d(TAG, "onResponse: Received Information: " + note.toString());
                    filteredNotesListAdapter = new FilteredNotesListAdapter(FilteredNotesActivity.this, note);
                    notesListView.setAdapter(filteredNotesListAdapter);
                    notesListView.setLayoutManager(new LinearLayoutManager(FilteredNotesActivity.this));
                }

                @Override
                public void onError(@NonNull Throwable throwable) {
                    Log.e(TAG, "onFailure: Something Went Wrong: " + throwable.getMessage());
                    Toast.makeText(FilteredNotesActivity.this, "Notes Not Available For This Label", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }
}
