package com.example.amantewary.scrawl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.INoteAPI;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNotesActivity extends AppCompatActivity {

    TextView tv_date;
    EditText et_title, et_content, et_link;
    Spinner sp_add_labels;
    String title, date, content, link;

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
        //Get Label


        setTitle("Edit Note");

        tv_date = (TextView) findViewById(R.id.tv_date);
        et_content = (EditText) findViewById(R.id.et_content);
        et_title = (EditText) findViewById(R.id.et_title);
        et_link = (EditText) findViewById(R.id.et_link);
        sp_add_labels = (Spinner) findViewById(R.id.sp_add_label);

        //make tv_date show current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
        String current_date = df.format(c);
        tv_date.setText(current_date);

        ArrayList<String> labels = (ArrayList<String>) getIntent().getSerializableExtra("labels");
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

        getMenuInflater().inflate(R.menu.activity_edit_notes_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            addNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNote(){
        String label = sp_add_labels.getSelectedItem().toString();
        String title = et_title.getText().toString().trim();
        String body = et_content.getText().toString().trim();
        String link = et_link.getText().toString().trim();
        NoteHandler noteHandler = new NoteHandler(label, title, body, link, 1);
        sendRequest(noteHandler);
    }

    private void sendRequest(NoteHandler noteHandler){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppURLs.noteApiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INoteAPI noteAPI = retrofit.create(INoteAPI.class);
        Call<NoteHandler> call = noteAPI.createNote(noteHandler);
        call.enqueue(new Callback<NoteHandler>() {
            @Override
            public void onResponse(Call<NoteHandler> call, Response<NoteHandler> response) {
                Toast.makeText(AddNotesActivity.this,"Note Created", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<NoteHandler> call, Throwable t) {
                Toast.makeText(AddNotesActivity.this,"Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
