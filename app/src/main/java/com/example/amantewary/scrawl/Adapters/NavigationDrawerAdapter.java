package com.example.amantewary.scrawl.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.amantewary.scrawl.FilteredNotesActivity;
import com.example.amantewary.scrawl.Handlers.NavgitationModel;
import com.example.amantewary.scrawl.MainActivity;
import com.example.amantewary.scrawl.NavObserver;
import com.example.amantewary.scrawl.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class NavigationDrawerAdapter extends ArrayAdapter<NavgitationModel> implements Observer {

    private String TAG = NavigationDrawerAdapter.class.getCanonicalName();
    private ArrayList<NavgitationModel> navigationList;
    private Context mContext;
    private boolean editToggle = true;
    private boolean mode = false;
    private int currentPosition;


    public NavigationDrawerAdapter(ArrayList<NavgitationModel> list, Context context, NavObserver navObserver) {
        super(context, R.layout.nav_item, list);
        Log.e(TAG, "Here created Observer");
        this.navigationList = list;
        this.mContext = context;
        navObserver.addObserver(this);


    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.nav_item, parent, false);
        final ViewSwitcher viewSwitcher = rowView.findViewById(R.id.viewSwitcher);
        LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.nav_linear_layout);
        final TextView labelNameTV = (TextView) rowView.findViewById(R.id.nav_text_view);
        final ImageView labelImage = (ImageView) rowView.findViewById(R.id.nav_labels);
        final EditText labelEdittext = (EditText) rowView.findViewById(R.id.nav_edit_text);
        labelNameTV.setText(navigationList.get(position).getTitle());
        labelImage.setImageDrawable(navigationList.get(position).getLabelImageView());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mode) {
                    Log.e(TAG, "Here " + labelNameTV.getText());
                    Intent intent = new Intent(mContext, FilteredNotesActivity.class);
                    intent.putExtra("label_name", labelNameTV.getText());
                    mContext.startActivity(intent);
                }
            }
        });


        // This code needs refactoring
        labelImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (editToggle) {

                    String label = navigationList.get(position).getTitle();
                    mode = false;
                    editToggle = false;
                    currentPosition = position;
                    Log.e(TAG, "position" + position);
                    labelEdittext.setBackgroundResource( R.drawable.border);
                    toggleVisibility(currentPosition, parent);
                    labelImage.setImageResource(R.drawable.ic_bookmark_grey_24dp);
                    labelEdittext.setText(label);
                    viewSwitcher.showNext();

                } else {
                    mode = true;
                    editToggle = true;
                    toggleVisibility(currentPosition, parent);
                    navigationList.add(position,new NavgitationModel(navigationList.get(position).getLabelImageView(),labelEdittext.getText().toString()));
                    navigationList.remove(position+1);
                    Log.e(TAG, "HEre");
                    labelImage.setImageDrawable(navigationList.get(position).getLabelImageView());
                    labelNameTV.setText(navigationList.get(position).getTitle());
                    viewSwitcher.showPrevious();
                }


                return false;
            }
        });


        return rowView;
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof NavObserver) {
            if (o.equals(MainActivity.class.getCanonicalName())) {

            }
        }
    }

    void toggleVisibility(int currentPosition, ViewGroup parent){
        if(!editToggle){
            for ( int i = 0; i < parent.getChildCount();  i++ ){
                if (i != currentPosition){

                    Log.e(TAG, ""+parent.getChildCount());
                    View view = parent.getChildAt(i);
                    view.animate().alpha(0.0f).setDuration(500);
                    view.setVisibility(View.GONE);
                }
            }
        }else{
            for ( int i = 0; i < parent.getChildCount();  i++ ){
                    Log.e(TAG, ""+parent.getChildCount());
                    View view = parent.getChildAt(i);
                    view.animate().alpha(1.0f).setDuration(500);
                    view.setVisibility(View.VISIBLE);

            }
        }

    }



}
