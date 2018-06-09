package com.example.amantewary.scrawl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditNotesActivity extends AppCompatActivity {

    TextView tv_date;
    Button btn_add_label;
    EditText et_title, et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        Toolbar toolbar_edit_note = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar_edit_note);
        setTitle("Edit Note");

        tv_date = (TextView)findViewById(R.id.tv_date);
        btn_add_label = (Button)findViewById(R.id.btn_add_label);
        et_content = (EditText)findViewById(R.id.et_content);
        et_title = (EditText)findViewById(R.id.et_title);


        //make tv_date show current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        String current_date = df.format(c);
        tv_date.setText(current_date);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_edit_notes_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
