package com.example.amantewary.scrawl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNotesActivity extends AppCompatActivity implements Observer {

    private static final String TAG = "AddNotesActivity";

    private TextView tv_date;
    private EditText et_title, et_content, et_link;
    private Spinner sp_add_labels;
    private ArrayList<String> labels;
    InputHandler inputHandler;
    /**
     * A method to check if a string is a link
     *
     * @param str string
     * @return true: it is link; false: it is not a link
     */
    private static boolean isLink(String str) {
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                + "(([0-9]{1,3}.){3}[0-9]{1,3}"
                + "|"
                + "([0-9a-z_!~*'()-]+.)*"
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]."
                + "[a-z]{2,6})"
                + "(:[0-9]{1,4})?"
                + "((/?)|"
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";

        return match(regex, str);
    }

    /**
     * A method to determine if a string match a regex
     *
     * @param regex regex
     * @param str   string
     * @return true: match; false: not match
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        Toolbar toolbar_edit_note = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar_edit_note);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        inputHandler = new InputHandler(getApplicationContext());
        inputHandler.addObserver(this);

        setTitle("Add Note");

        tv_date = findViewById(R.id.tv_date);
        et_content = findViewById(R.id.et_content);
        et_title = findViewById(R.id.et_title);
        et_link = findViewById(R.id.et_link);
        sp_add_labels = findViewById(R.id.sp_add_label);

        /**
         * Swag code!
         * */
        doRealTimeCheck();

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

            String label = inputHandler.inputCensor(sp_add_labels.getSelectedItem().toString());
            String title = inputHandler.inputCensor(et_title.getText().toString().trim());
            String body = inputHandler.inputCensor(et_content.getText().toString().trim());
            String link = et_link.getText().toString().trim();
            //TODO: Need to change user_id once login and registration is done.
            NoteHandler noteHandler = new NoteHandler(label, title, body, link, 1);
            if (inputHandler.inputValidator(title, body, link)) {
                NotesRequestHandler request = new NotesRequestHandler();
                request.createNote(noteHandler, AddNotesActivity.this);
            }else{
                inputHandler.inputErrorHandling(et_title, et_content, et_link);
            }
        } catch (Exception e) {
            Log.e("Message", ""+e);
        }
    }


    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof InputHandler){
            Log.e(TAG, "Here");
            Toast.makeText(getApplicationContext(),"I know you are adding bad words.. naughty bow", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO: Need to move this in InputHandler Class
    private void doRealTimeCheck(){
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() != 0){
                    inputHandler.inputCensor(et_content.getText().toString().trim());
                }
            }
        });

        et_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() != 0){
                    inputHandler.inputCensor(et_content.getText().toString().trim());
                }
            }
        });
    }
}
