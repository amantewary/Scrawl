package com.example.amantewary.scrawl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.INoteAPI;
import com.example.amantewary.scrawl.Handlers.LabelHandler;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNotesActivity extends AppCompatActivity {

    private static final String TAG = "AddNotesActivity";

<<<<<<< HEAD
    TextView tv_date;
    EditText et_title, et_content, et_link;
    Spinner sp_add_labels;
    String title, date, content, link;
    SessionManager sessionManager;

=======
    private TextView tv_date;
    private EditText et_title, et_content, et_link;
    private Spinner sp_add_labels;
    private ArrayList<String> labels;
>>>>>>> devint

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

    public boolean handleShareEvent(){
        if(sessionManager.checkLogin()){
            Intent intent = getIntent();
            String action = intent.getAction();
            String type = intent.getType();

            if (Intent.ACTION_SEND.equals(action) && type != null) {
                if ("text/plain".equals(type)) {
                    String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                    if (sharedText != null) {
                        et_content.setText(sharedText);
                        return true;
                    }
                }
            }
        }else{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD

        setContentView(R.layout.activity_add_notes);
        sessionManager = new SessionManager(getApplicationContext());
=======
        setContentView(R.layout.activity_add_notes);
>>>>>>> devint
        Toolbar toolbar_edit_note = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar_edit_note);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Get Label

        setTitle("Add Note");

        tv_date = findViewById(R.id.tv_date);
        et_content = findViewById(R.id.et_content);
        et_title = findViewById(R.id.et_title);
        et_link = findViewById(R.id.et_link);
        sp_add_labels = findViewById(R.id.sp_add_label);

        //make tv_date show current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
        String current_date = df.format(c);
        tv_date.setText(current_date);
        LabelHandler labelHandler = new LabelHandler();
        Log.e(TAG,labelHandler.getName()+ "");
//        ArrayList<String> labels = (ArrayList<String>) getIntent().getSerializableExtra("labels");
//        ArrayList<String> labels = new LabelHandler().getLabelHandlers();
//
//        try{
//            Context ctx = getApplicationContext();
//
//            FileInputStream fileInputStream = ctx.openFileInput("labels.txt");
//
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//            String lineData = bufferedReader.readLine();
//            Log.e(TAG,lineData);
//
//        }catch (Exception e){
//            Log.e(TAG,""+e);
//        }



        handleShareEvent();

<<<<<<< HEAD
=======
        labels = LabelLoader.getInstance().loadLabel(AddNotesActivity.this);
        final ArrayAdapter labelAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                labels
        );
        labelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_add_labels.setAdapter(labelAdapter);
>>>>>>> devint
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
            String label = sp_add_labels.getSelectedItem().toString();
            String title = et_title.getText().toString().trim();
            String body = et_content.getText().toString().trim();
            String link = et_link.getText().toString().trim();
            //TODO: Need to change user_id once login and registration is done.
            NoteHandler noteHandler = new NoteHandler(label, title, body, link, 1);
            /*
            Validation for Recipe's Name, Ingredients, Steps and Cuisine.
            User can leave the link blank.
         */
            if (!TextUtils.isEmpty(title)
                    && !TextUtils.isEmpty(body)
                    && (Patterns.WEB_URL.matcher(link).matches()
                    || TextUtils.isEmpty(link))) {
                sendRequest(noteHandler);
            }else{

                if (title.matches("")) {
                    et_title.setBackgroundResource(R.drawable.border_error);
                    et_title.setError("Enter Note Title");
                    return;
                } else {
                    et_title.setBackgroundResource(R.drawable.border);
                }
                if (body.matches("")) {
                    et_content.setBackgroundResource(R.drawable.border_error);
                    et_content.setError("Enter Note Body");
                    return;
                } else {
                    et_content.setBackgroundResource(R.drawable.border);
                }
                if (!Patterns.WEB_URL.matcher(link).matches()) {
                    et_link.setError("Please Enter Valid URL");
                    return;
                }

            }
        } catch (Exception e) {
            Log.e("Message", e.toString());
        }
    }

    private void sendRequest(NoteHandler noteHandler) {
        INoteAPI noteAPI = RetroFitInstance.getRetrofit().create(INoteAPI.class);
        Call<NoteHandler> call = noteAPI.createNote(noteHandler);
        call.enqueue(new Callback<NoteHandler>() {
            @Override
            public void onResponse(Call<NoteHandler> call, Response<NoteHandler> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: Received Information: " + response.body().toString());
                Toast.makeText(AddNotesActivity.this, "Note Created", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<NoteHandler> call, Throwable t) {
                Log.e(TAG, "onFailure: Something Went Wrong: " + t.getMessage());
                Toast.makeText(AddNotesActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
