package com.example.amantewary.scrawl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.SubtitleCollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.IShareAPI;
import com.example.amantewary.scrawl.API.Users.ICheckUser;
import com.example.amantewary.scrawl.Handlers.UserClass;
import com.example.amantewary.scrawl.API.Notes.INoteResponse;

import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.Handlers.ShareHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewNotesActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ViewNotesActivity";

    private Menu menu;
    private BottomSheetBehavior mBottomSheetBehavior1;
    private FloatingActionButton fab;
    private SubtitleCollapsingToolbarLayout collapsingToolbarLayout;
    private Integer noteId;
    private TextView tv_note_content, tv_note_link, tv_note_date, tv_note_status;
    private Button btn_edit, btn_share, btn_delete, btn_collaborate;
    private NoteHandler noteHandler;
    private NotesRequestHandler request;
    private SessionManager sessionManager;


    protected void viewBinder() {
        btn_edit = findViewById(R.id.btn_edit);
        btn_collaborate = findViewById(R.id.btn_collaborate);
        btn_share = findViewById(R.id.btn_share);
        btn_delete = findViewById(R.id.btn_delete);
        tv_note_content = findViewById(R.id.viewNotesBody);
        tv_note_link = findViewById(R.id.viewNotesLink);
        tv_note_date = findViewById(R.id.viewNoteDate);
        tv_note_status = findViewById(R.id.viewNoteStatus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewnotesscroll);
        viewBinder();
        collapsingToolbarLayout = findViewById(R.id.subtitlecollapsingtoolbarlayout);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            noteId = bundle.getInt("noteid");
        }
        collapsingToolbarLayout.setExpandedSubtitleTextAppearance(R.style.TextAppearance_AppCompat_Subhead);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
//        mToolbar.setTitle("My Title");
        View bottomSheet = findViewById(R.id.menu_sheet);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior1.setHideable(true);
        mBottomSheetBehavior1.setPeekHeight(0);
        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.cog);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });

        AppBarLayout mAppBarLayout = findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });

        btn_edit.setOnClickListener(this);
        btn_collaborate.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        sessionManager = new SessionManager(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        request = new NotesRequestHandler();
        try {
            request.getSingleNote(ViewNotesActivity.this, noteId, new INoteResponse() {
                @Override
                public void onSuccess(@NonNull List<NoteHandler> note) {
                    Log.d(TAG, "onResponse: Received Information: " + note.toString());
                    setView(note);
                }

                @Override
                public void onError(@NonNull Throwable throwable) {
                    Log.e(TAG, "onFailure: Something Went Wrong: " + throwable.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }

    public void setView(List<NoteHandler> note) {

        //TODO: REFACTORING
        try {
            for (NoteHandler n : note) {
                collapsingToolbarLayout.setTitle(n.getTitle());
                collapsingToolbarLayout.setSubtitle(n.getLabel_name());
                tv_note_content.setText(n.getBody());
                tv_note_link.setText(n.getUrl());
                tv_note_date.setText(n.getDate());
                tv_note_status.setText(n.getStatus());
            }
        } catch (Exception e) {
            Log.e(TAG, "Message: " + e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.activity_viewnotesmenu, menu);
        hideOption(R.id.action_info);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }


    @Override
    public void onClick(View view) {
        //Replace Condition Polymorphism
        switch (view.getId()) {
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

    private void sendRequest(ShareHandler shareHandler) {
        IShareAPI shareAPI = RetroFitInstance.getRetrofit().create(IShareAPI.class);
        Call<ShareHandler> call = shareAPI.createShare(shareHandler);
        call.enqueue(new Callback<ShareHandler>() {
            @Override
            public void onResponse(Call<ShareHandler> call, Response<ShareHandler> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: Received Information: " + response.body().toString());
                Toast.makeText(ViewNotesActivity.this, "Shared Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ShareHandler> call, Throwable t) {
                Log.e(TAG, "onFailure: Something Went Wrong: " + t.getMessage());
                Toast.makeText(ViewNotesActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
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
