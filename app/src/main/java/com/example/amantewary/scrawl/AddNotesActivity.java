package com.example.amantewary.scrawl;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class AddNotesActivity extends AppCompatActivity implements Observer {

    private static final String TAG = "AddNotesActivity";

    private TextView tv_date;
    private EditText et_title, et_content, et_link;
    private Spinner sp_add_labels;
    private ArrayList<String> labels;
    private InputHandler inputHandler;
    private String title, label, body, link;

    protected void viewBinder(){
        tv_date = findViewById(R.id.tv_date);
        et_content = findViewById(R.id.et_content);
        et_title = findViewById(R.id.et_title);
        et_link = findViewById(R.id.et_link);
        sp_add_labels = findViewById(R.id.sp_add_label);
    }

    private void handleSendNotes() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        try {
            if (sessionManager.checkLogin()) {
                Intent intent = getIntent();
                String action = intent.getAction();
                String type = intent.getType();

                if (Intent.ACTION_SEND.equals(action) && type != null) {
                    if ("text/plain".equals(type)) {
                        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                        if (sharedText != null) {
                            et_content.setText(sharedText);
                        }
                    }
                }
            }
        }catch (Exception e){
            Log.e(TAG,"Message: " + e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        viewBinder();
        Toolbar toolbar_edit_note = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar_edit_note);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        inputHandler = new InputHandler(getApplicationContext());
        inputHandler.addObserver(this);

        setTitle("Add Note");
        inputHandler.doRealTimeLanguageCheck(et_content);
        inputHandler.doRealTimeLanguageCheck(et_title);

        //make tv_date show current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
        String current_date = df.format(c);
        tv_date.setText(current_date);

        labels = LabelLoader.getInstance().loadLabel(AddNotesActivity.this);
        final ArrayAdapter labelAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                labels
        );
        labelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_add_labels.setAdapter(labelAdapter);

        handleSendNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_add_notes_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            addNote();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNote() {

        try {
            label = inputHandler.inputCensor(sp_add_labels.getSelectedItem().toString());
            title = inputHandler.inputCensor(et_title.getText().toString().trim());
            body = inputHandler.inputCensor(et_content.getText().toString().trim());
            link = et_link.getText().toString().trim();
            //TODO: Need to change user_id once login and registration is done.
            NoteHandler noteHandler = new NoteHandler(label, title, body, link, 1);
            if (inputHandler.inputValidator(title, body, link)) {
                NotesRequestHandler request = new NotesRequestHandler();
                request.createNote(noteHandler, AddNotesActivity.this);
            } else {
                inputHandler.inputErrorHandling(et_title, et_content, et_link);
            }
        } catch (Exception e) {
            Log.e(TAG,"Message: " + e.toString());
        }
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof InputHandler) {
            Log.e(TAG, "Here");
            Toast.makeText(getApplicationContext(), "All bad words will be censored", Toast.LENGTH_SHORT).show();
        }
    }
}
