package com.example.amantewary.scrawl;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.Users.IUserExistResponse;
import com.example.amantewary.scrawl.BaseActivities.ViewNoteBaseActivity;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.Calendar;

public class ViewNotesActivity extends ViewNoteBaseActivity implements TimePickerDialog.OnTimeSetListener{

    private static final String TAG = "ViewNotesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        btn_collaborate.setOnClickListener(this);
        btn_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.btn_timer:
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getFragmentManager(), "time picker");
                break;
            case R.id.btn_edit:
                Intent intent = new Intent(ViewNotesActivity.this, EditNotesActivity.class);
                intent.putExtra("noteid", noteId);
                startActivity(intent);
                break;
            case R.id.btn_collaborate:
                showDialog();
                break;
            case R.id.btn_share:
                setShareIntent();
                break;
            case R.id.btn_delete:
                deleteNote();
                break;
        }
    }

    private void setShareIntent() {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, tv_note_content.getText().toString());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "share to"));
        } catch (Exception e) {
            Log.e(TAG, "Failed to set Share Intent: " + e.getMessage());
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
        Intent intent = new Intent(ViewNotesActivity.this, NotificationReceiver.class);
        intent.putExtra("notetitle", collapsingToolbarLayout.getTitle());
        PendingIntent reminder = PendingIntent.getBroadcast(this, requestId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        noteAlarm.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, reminder);
        Toast.makeText(this, "Reminder Set", Toast.LENGTH_LONG).show();
    }

    private void showDialog() {

        final EditText et_collaborate_with = new EditText(this);
        et_collaborate_with.setHint("Please input email address");
        try {
            new AlertDialog.Builder(this)
                    .setTitle("Collaborate with:")
                    .setView(et_collaborate_with)
                    .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setCollaborateInfo(et_collaborate_with.getText().toString());
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } catch (Exception e) {
            Log.e(TAG, "Failed to show Dialog " + e.getMessage());
        }

    }

    public void setCollaborateInfo(final String collaborate_with) {
        if (!emailPasswordValidation.isEmailValid(collaborate_with)){
            Toast.makeText(this, "Please input a valid email address.", Toast.LENGTH_LONG).show();
        }else {
            try {
                if (sessionManager.checkLogin()) {
                    final String share_from = sessionManager.getUserEmail();

                    UserRequestHandler userRequestHandler = new UserRequestHandler();
                    try{
                        userRequestHandler.checkIfUserExists(this, collaborate_with, new IUserExistResponse() {
                            @Override
                            public void onSuccess(@NonNull Boolean ifExist) {
                                if (ifExist){
                                    Integer note_id = noteId;
                                    ShareHandler shareHandler = new ShareHandler(share_from, collaborate_with, note_id);
                                    sendRequest(shareHandler);
                                }else {
                                    Toast.makeText(getApplicationContext(), "Sorry. This user does not exist.", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                Log.e(TAG, "onFailure: Something Went Wrong: " + throwable.getMessage());
                            }
                        });
                    }catch (Exception e) {
                        Log.e(TAG, "Message: " + e.toString());
                    }

                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("You have not logged in")
                            .setMessage("You have not logged in")
                            .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ViewNotesActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }

            } catch (Exception e) {
                Log.e(TAG, "Message: " + e.toString());
            }
        }
    }

    private void sendRequest(ShareHandler shareHandler) {
        SharesRequestHandler sharesRequestHandler = new SharesRequestHandler();
        sharesRequestHandler.createShare(shareHandler,ViewNotesActivity.this);
    }

    public void deleteNote() {

        //Builder
        try {
            AlertDialog.Builder deleteAlert = new AlertDialog.Builder(ViewNotesActivity.this);
            deleteAlert.setTitle("Delete Note");
            deleteAlert.setMessage("Are you sure?");
            deleteAlert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    noteHandler = new NoteHandler(noteId);
                    request = new NotesRequestHandler();
                    request.deleteNote(noteHandler, ViewNotesActivity.this);

                }
            });
            deleteAlert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            deleteAlert.show();
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }

    }

}
