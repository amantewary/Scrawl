package com.example.amantewary.scrawl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.amantewary.scrawl.API.ILabelAPI;
import com.example.amantewary.scrawl.API.INoteAPI;
import com.example.amantewary.scrawl.Adapters.NotesList;
import com.example.amantewary.scrawl.Handlers.LabelHandler;
import com.example.amantewary.scrawl.Handlers.NoteHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    RecyclerView notesListView;
    NotesList notesAdapter;
    ArrayList<String> labelOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        notesListView = findViewById(R.id.viewNoteList);

        //Loading Labels from database
        ILabelAPI labelAPI = RetroFitInstance.getRetrofit().create(ILabelAPI.class);

        Call<List<LabelHandler>> call = labelAPI.getLabels();
        call.enqueue(new Callback<List<LabelHandler>>() {
            @Override
            public void onResponse(Call<List<LabelHandler>> call, Response<List<LabelHandler>> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: Received Information: " + response.body().toString());
                List<LabelHandler> labels = response.body();
                labelOptions = new ArrayList<String>();
                for (LabelHandler label : labels) {
                    Log.e("label", label.getName());
                    labelOptions.add(label.getName());
                }
            }

            @Override
            public void onFailure(Call<List<LabelHandler>> call, Throwable t) {
                Log.e(TAG, "onFailure: Something Went Wrong: " + t.getMessage());
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab_add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Factory Design Pattern
                Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
                intent.putExtra("labels", labelOptions);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        INoteAPI noteAPI = RetroFitInstance.getRetrofit().create(INoteAPI.class);
        Call<List<NoteHandler>> call = noteAPI.getNotes();

        call.enqueue(new Callback<List<NoteHandler>>() {
            @Override
            public void onResponse(Call<List<NoteHandler>> call, Response<List<NoteHandler>> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: Received Information: " + response.body().toString());
                List<NoteHandler> notes = response.body();
                notesAdapter = new NotesList(MainActivity.this, notes);
                notesListView.setAdapter(notesAdapter);
                notesListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<List<NoteHandler>> call, Throwable t) {
                Log.e(TAG, "onFailure: Something Went Wrong: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
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

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
