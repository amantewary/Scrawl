package com.example.amantewary.scrawl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.amantewary.scrawl.API.Labels.ILabelResponse;
import com.example.amantewary.scrawl.API.Notes.INoteResponse;
import com.example.amantewary.scrawl.Adapters.NavigationDrawerAdapter;
import com.example.amantewary.scrawl.Adapters.NotesListAdapter;
import com.example.amantewary.scrawl.Handlers.LabelHandler;
import com.example.amantewary.scrawl.Handlers.NavgitationModel;
import com.example.amantewary.scrawl.Handlers.NoteHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.l4digital.fastscroll.FastScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    FirebaseAnalytics mFirebaseAnalytics;
    NavigationDrawerAdapter navigationDrawerAdapter;
    private FastScrollRecyclerView notesListView;
    private NotesListAdapter notesAdapter;
    private ArrayList<String> labelList;
    private Toolbar toolbar;
    private ListView listView;
    private DrawerLayout drawer;
    private LinearLayout logout;
    private SwipeRefreshLayout swiperefresh;
    private SessionManager sessionManager;

    protected void viewBinder() {
        toolbar = findViewById(R.id.toolbar);
        notesListView = findViewById(R.id.viewNoteList);
        listView = findViewById(R.id.lstDrawerItems);
        drawer = findViewById(R.id.drawer_layout);
        logout = findViewById(R.id.nav_lout);
        swiperefresh =findViewById(R.id.swiperefresh);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinder();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setCurrentScreen(this, getClass().getCanonicalName(), null);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ArrayList<NavgitationModel> navgitationModels = new ArrayList<>();
        loadLabelsForList(navgitationModels);
        navigationDrawerAdapter = new NavigationDrawerAdapter(navgitationModels, this);
        listView.setAdapter(navigationDrawerAdapter);


        //Loading Labels from database
        //TODO: Need to move this in splash screen
        initialLabelListLoading();

        FloatingActionButton fab = findViewById(R.id.fab_add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Factory Design Pattern
                Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        sessionManager = new SessionManager(getApplicationContext());

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh called from SwipeRefreshLayout");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        populateNotesList();
                        swiperefresh.setRefreshing(false);
                    }
                },0);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateNotesList();
    }

    protected void populateNotesList() {
        String cur_usr_email = sessionManager.getUserEmail();
        Integer cur_usr_id = sessionManager.getUserId();

        NotesRequestHandler request = new NotesRequestHandler();
        try {
            request.getAllNotesByUserId(MainActivity.this, cur_usr_email, cur_usr_id, new INoteResponse() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }

                @Override
                public void onSuccess(@NonNull List<NoteHandler> notes) {
                    Log.d(TAG, "getID:" + String.valueOf(notes.get(0).getId()));
                    if (notes.get(0).getId() != null) {
                        Log.d(TAG, "populateNotesList().onResponse: Received Information: " + notes.toString());
                        notesAdapter = new NotesListAdapter(MainActivity.this, notes);
                        notesListView.setAdapter(notesAdapter);
                        notesListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                }

                @Override
                public void onError(@NonNull Throwable throwable) {
                    Log.e(TAG, "populateNotesList().onError: Something Went Wrong: " + throwable.getMessage());
                }
            });
        }catch (Exception e){
            Log.e(TAG,"Message:" + e.toString());
        }
    }

    public void initialLabelListLoading(){
        try {
            LabelRequestHandler request = new LabelRequestHandler();
            request.getLabel(MainActivity.this, 44, new ILabelResponse() {
                @Override
                public void onSuccess(@NonNull List<LabelHandler> labels) {
                    Log.d(TAG, "onResponse: Received Information: " + labels.toString());
                    labelList = new ArrayList<>();
                    for (LabelHandler label : labels) {
                        Log.e("label", label.getName());
                        labelList.add(label.getName());
                    }
                    LabelLoader.getInstance().saveLabel(MainActivity.this, labelList);
                }
                @Override
                public void onError(@NonNull Throwable throwable) {
                    Log.e(TAG, "onFailure: Something Went Wrong: " + throwable.getMessage());
                }
            });
        }catch (Exception e){
            Log.e(TAG,"Message" + e.toString());
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_add) {


        } else if (id == R.id.nav_logout) {
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");

        String positiveText = getString(android.R.string.yes);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        signOut();
                    }
                });

        String negativeText = getString(android.R.string.no);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing here
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void signOut() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.logoutUser();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    void loadLabelsForList(ArrayList<NavgitationModel> navList) {
        ArrayList<String> labelString = LabelLoader.getInstance().loadLabel(this);

        for (String labelName : labelString) {
            navList.add(new NavgitationModel(getResources().getDrawable(R.drawable.ic_bookmark_black_24dp), labelName));
        }
    }


}
