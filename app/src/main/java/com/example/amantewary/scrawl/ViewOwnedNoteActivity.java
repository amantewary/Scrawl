package com.example.amantewary.scrawl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.Users.ICheckUser;
import com.example.amantewary.scrawl.BaseActivities.ViewNoteBaseActivity;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.Handlers.ShareHandler;
import com.example.amantewary.scrawl.Handlers.UserClass;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class ViewOwnedNoteActivity extends ViewNoteBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = "ViewOwnedNoteActivity";

        btn_collaborate.setOnClickListener(this);
        btn_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.btn_edit:
                Intent intent = new Intent(ViewOwnedNoteActivity.this, EditNotesActivity.class);
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


    /////
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

    /////
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

        try {
            if (sessionManager.checkLogin()) {
                final String share_from = sessionManager.getUserEmail();

                final Boolean[] result = new Boolean[1];
                ICheckUser service = RetroFitInstance.getRetrofit().create(ICheckUser.class);
                RequestBody body = RequestBody.create(MediaType.parse("text/plain"), collaborate_with);
                Map<String, RequestBody> requestBodyMap = new HashMap<>();
                requestBodyMap.put("email", body);
                Call<UserClass> call = service.checkIfUserExists(requestBodyMap);
                call.enqueue(new Callback<UserClass>() {
                    @Override
                    public void onResponse(Call<UserClass> call, retrofit2.Response<UserClass> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getError().equals("false")) {
                                result[0] = false;
                                String share_to = collaborate_with;
                                Integer note_id = noteId;
                                ShareHandler shareHandler = new ShareHandler(share_from, share_to, note_id);
                                sendRequest(shareHandler);
                            } else {
                                result[0] = true;
                                Toast.makeText(getApplicationContext(), "Sorry. This user does not exist.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.e(TAG, "" + response.raw());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserClass> call, Throwable t) {
                        t.printStackTrace();
                        Log.e(TAG, "ifUserExists.onFailure" + t.getMessage());
                    }
                });

            } else {
                new AlertDialog.Builder(this)
                        .setTitle("You have not logged in")
                        .setMessage("You have not logged in")
                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ViewOwnedNoteActivity.this, LoginActivity.class);
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

    private void sendRequest(ShareHandler shareHandler) {
        SharesRequestHandler sharesRequestHandler = new SharesRequestHandler();
        sharesRequestHandler.createShare(shareHandler,ViewOwnedNoteActivity.this);
    }

    public void deleteNote() {

        //Builder
        try {
            AlertDialog.Builder deleteAlert = new AlertDialog.Builder(ViewOwnedNoteActivity.this);
            deleteAlert.setTitle("Delete Note");
            deleteAlert.setMessage("Are you sure?");
            deleteAlert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    noteHandler = new NoteHandler(noteId);
                    request = new NotesRequestHandler();
                    request.deleteNote(noteHandler, ViewOwnedNoteActivity.this);
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
