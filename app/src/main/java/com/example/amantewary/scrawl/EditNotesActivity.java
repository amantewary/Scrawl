package com.example.amantewary.scrawl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditNotesActivity extends AppCompatActivity {

    TextView tv_date;
    EditText et_title, et_content, et_link;
    Spinner sp_add_labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        Toolbar toolbar_edit_note = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar_edit_note);
        setTitle("Edit Note");

        tv_date = (TextView)findViewById(R.id.tv_date);
        et_content = (EditText)findViewById(R.id.et_content);
        et_title = (EditText)findViewById(R.id.et_title);
        et_link = (EditText)findViewById(R.id.et_link);
        sp_add_labels = (Spinner)findViewById(R.id.sp_add_label);

        //make tv_date show current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
        String current_date = df.format(c);
        tv_date.setText(current_date);

        //TODO Retrieve labels from db
        //Fake labels here
        String[] labels = new String[]{
                "Personal",
                "Work",
                "Life",
                "Travel"
        };

        ArrayAdapter labelAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                labels
                );

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

        //validate EditTexts
        if (id == R.id.action_save) {

            String txt_title = et_title.getText().toString();
            if(txt_title.isEmpty()) {
                et_title.setError("Please input the title.");
            }

            String txt_link = et_link.getText().toString();
            if (!txt_link.isEmpty() && !isLink(txt_link)){
                et_link.setError("Please input valid link");
            }

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A method to check if a string is a link
     * @param str string
     * @return true: it is link; false: it is not a link
     */
    private static boolean isLink(String str){
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
     * @param regex regex
     * @param str string
     * @return true: match; false: not match
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


}
