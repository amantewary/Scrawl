package com.example.amantewary.scrawl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.Notes.INoteResponse;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class EditNotesActivity extends AppCompatActivity implements Observer {

    private static final String TAG = "EditNotesActivity";

    private EditText et_title, et_content, et_link;
    private TextView tv_date;
    private Spinner labelSpinner;
    private ArrayList<String> labels;
    private Integer noteId;
    private InputHandler inputHandler;
    private NotesRequestHandler request;

    protected void viewBinder(){
        et_title = findViewById(R.id.et_title_edit);
        et_content = findViewById(R.id.et_content_edit);
        et_link = findViewById(R.id.et_link_edit);
        labelSpinner = findViewById(R.id.sp_edit_label);
        tv_date = findViewById(R.id.tv_date_edit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        viewBinder();
        Toolbar toolbar_edit_note = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar_edit_note);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Edit Note");

        inputHandler = new InputHandler(getApplicationContext());
        request = new NotesRequestHandler();
        inputHandler.addObserver(this);
        inputHandler.doRealTimeLanguageCheck(et_content);
        inputHandler.doRealTimeLanguageCheck(et_title);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            noteId = bundle.getInt("noteid");
        }

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
        String current_date = df.format(c);
        tv_date.setText(current_date);

        labels = LabelLoader.getInstance().loadLabel(EditNotesActivity.this);
        final ArrayAdapter labelAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                labels
        );
        labelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        labelSpinner.setAdapter(labelAdapter);

        //Abstract Factory
        getNoteByNoteID();

    }

    public void getNoteByNoteID(){
        try{
            request.getSingleNote(EditNotesActivity.this,noteId, new INoteResponse(){
                @Override
                public void onSuccess(@NonNull List<NoteHandler> note) {
                    Log.d(TAG, "onResponse: Received Information: " + note.toString());
                    for(NoteHandler n : note){
                        et_title.setText(n.getTitle());
                        et_content.setText(n.getBody());
                        et_link.setText(n.getUrl());
                    }
                }

                @Override
                public void onError(@NonNull Throwable throwable) {
                    Log.e(TAG, "onFailure: Something Went Wrong: " + throwable.getMessage());
                }
            });
        }catch (Exception e){
            Log.e(TAG,"Message: " + e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_edit_notes_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            editNote();
        }
        return super.onOptionsItemSelected(item);
    }

    public void editNote(){

        String label = inputHandler.inputCensor(labelSpinner.getSelectedItem().toString());
        String title = inputHandler.inputCensor(et_title.getText().toString().trim());
        String body = inputHandler.inputCensor(et_content.getText().toString().trim());
        String link = et_link.getText().toString().trim();
        try {
            NoteHandler noteHandler = new NoteHandler(noteId, label, title, body, link, 1);
            if (inputHandler.inputValidator(title, body, link)) {
                request.editNote(noteHandler, EditNotesActivity.this);
            } else {
                inputHandler.inputErrorHandling(et_title, et_content, et_link);
            }
        }catch (Exception e){
            Log.e(TAG,"Message: " + e.toString());
        }

    }
    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof InputHandler){
            Log.e(TAG, "Here");
            Toast.makeText(getApplicationContext(),"All bad words will be censored", Toast.LENGTH_SHORT).show();
        }
    }

}
