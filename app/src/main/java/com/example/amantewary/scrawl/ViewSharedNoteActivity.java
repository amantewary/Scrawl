package com.example.amantewary.scrawl;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.amantewary.scrawl.BaseActivities.ViewNoteBaseActivity;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.Calendar;

public class ViewSharedNoteActivity extends ViewNoteBaseActivity implements TimePickerDialog.OnTimeSetListener{

    private ShareHandler shareHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            case R.id.btn_timer:
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getFragmentManager(), "time picker");
                break;
        }
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {

        /*
            Setting the Calendar instance to the time picked
            using TimePicker Dialog
         */
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calender.set(Calendar.MINUTE, minute);
        calender.set(Calendar.SECOND, 0);
        calender.set(Calendar.MILLISECOND, 0);
        /*
            Converting Time to millisecond for setting it in AlarmManager &
            Creating a dynamic requestId using current system time.
         */
        long timeInMillis = calender.getTimeInMillis();
        final int requestId = (int) System.currentTimeMillis();
        /*
            Setting an AlarmManager and passing the intent to NotificationReceiver.java
         */
        AlarmManager noteAlarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ViewSharedNoteActivity.this, NotificationReceiver.class);
        intent.putExtra("notetitle", collapsingToolbarLayout.getTitle());
        PendingIntent reminder = PendingIntent.getBroadcast(this, requestId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        noteAlarm.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, reminder);
        Toast.makeText(this, "Reminder Set", Toast.LENGTH_LONG).show();
    }



    public void cancelShare() {
    }

}
