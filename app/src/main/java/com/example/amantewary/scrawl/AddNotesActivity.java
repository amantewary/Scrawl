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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNotesActivity extends AppCompatActivity {

    TextView tv_date;
    EditText et_title, et_content, et_link;
    Spinner sp_add_labels;
    String title, date, content, link;
    Integer label_id;

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
        //Get Label


        setTitle("Edit Note");

        tv_date = (TextView) findViewById(R.id.tv_date);
        et_content = (EditText) findViewById(R.id.et_content);
        et_title = (EditText) findViewById(R.id.et_title);
        et_link = (EditText) findViewById(R.id.et_link);
        sp_add_labels = (Spinner) findViewById(R.id.sp_add_label);


        //TODO Retrieve date the note was last modified from db...
        //Fake date here
        date = "Jun 23 2018";
        tv_date.setText(date);

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

//            String txt_title = et_title.getText().toString();
//            String txt_link = et_link.getText().toString();
//
//            //validate EditTexts first
//            if (txt_title.isEmpty()) {
//                et_title.setError("Please input the title.");
//
//            } else if (!txt_link.isEmpty() && !isLink(txt_link)) {
//                et_link.setError("Please input valid link");
//
//            } else {
//                //collect note info.
//                title = et_title.getText().toString();
//                Log.d("Note Info", "title: " + title);
//
//                //label is already got in OnItemClickListener of sp_add_labels
//                Log.d("Note Info", "label: " + label);
//
//                Date c = Calendar.getInstance().getTime();
//                SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
//                date = df.format(c);
//                Log.d("Note Info", "date: " + date);
//
//                content = et_content.getText().toString();
//                Log.d("Note Info", "content: " + content);
//
//                link = et_link.getText().toString();
//                Log.d("Note Info", "link: " + link);
//
//                //TODO save note into db...
//
//            }
//

        }
        return super.onOptionsItemSelected(item);
    }

    public void addNote(){
        
    }



}
