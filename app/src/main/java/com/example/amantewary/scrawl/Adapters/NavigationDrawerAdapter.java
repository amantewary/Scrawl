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


    public NavigationDrawerAdapter(ArrayList<NavgitationModel> list, Context context, NavObserver navObserver) {
        super(context, R.layout.nav_item, list);
        Log.e(TAG, "Here created Observer");
        this.navigationList = list;
        this.mContext = context;
        navObserver.addObserver(this);


    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.nav_item, parent, false);
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
                if (editToggle) {
                    Log.e(TAG, "Here " + labelNameTV.getText());
                    Intent intent = new Intent(mContext, FilteredNotesActivity.class);
                    intent.putExtra("label_name", labelNameTV.getText());
                    mContext.startActivity(intent);
                }

            }
        });


        labelImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (editToggle) {
                    editToggle = false;
                    mode = false;

                    Log.e(TAG, "Here true" + position);
                    labelImage.setImageResource(R.drawable.ic_bookmark_grey_24dp);
                    String label = labelNameTV.getText().toString();
                    viewSwitcher.showNext();
                    labelEdittext.setText(label);
                } else {
                    editToggle = true;
                    mode = true;
                    viewSwitcher.showPrevious();
                    labelImage.setImageDrawable(navigationList.get(position).getLabelImageView());
                    Log.e(TAG, "Here " + position);
                }


                return true;
            }
        });

        return rowView;
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof NavObserver) {
            if (o.equals(MainActivity.class.getCanonicalName())) {
                if (mode) {
                }
            }
        }
    }



}
