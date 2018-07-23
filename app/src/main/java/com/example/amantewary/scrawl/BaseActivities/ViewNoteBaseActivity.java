package com.example.amantewary.scrawl.BaseActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.SubtitleCollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amantewary.scrawl.API.Notes.INoteResponse;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.example.amantewary.scrawl.NotesRequestHandler;
import com.example.amantewary.scrawl.R;
import com.example.amantewary.scrawl.SessionManager;
import com.example.amantewary.scrawl.ViewNotesActivity;

import java.util.List;

public abstract class ViewNoteBaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected static String TAG = "ViewNoteBaseActivity";

    protected Menu menu;
    protected BottomSheetBehavior mBottomSheetBehavior1;
    protected FloatingActionButton fab;
    protected SubtitleCollapsingToolbarLayout collapsingToolbarLayout;
    protected Integer noteId;
    protected TextView tv_note_content, tv_note_link, tv_note_date, tv_note_status;
    protected Button btn_edit, btn_share, btn_delete, btn_collaborate;
    protected NoteHandler noteHandler;
    protected NotesRequestHandler request;
    protected SessionManager sessionManager;


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
        btn_delete.setOnClickListener(this);

        sessionManager = new SessionManager(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        request = new NotesRequestHandler();
        try {
            request.getSingleNote(this, noteId, new INoteResponse() {
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
    public void onClick(View view) {}

}
