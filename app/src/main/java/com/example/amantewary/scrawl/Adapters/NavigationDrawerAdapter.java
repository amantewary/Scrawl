package com.example.amantewary.scrawl.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amantewary.scrawl.Handlers.NavgitationModel;
import com.example.amantewary.scrawl.R;

import java.util.ArrayList;

public class NavigationDrawerAdapter extends ArrayAdapter<NavgitationModel> {

    String TAG = NavigationDrawerAdapter.class.getCanonicalName();
    private ArrayList<NavgitationModel> navigationList;
    private Context mContext;
    private boolean editToggle = true;


    public NavigationDrawerAdapter(ArrayList<NavgitationModel> list, Context context) {
        super(context, R.layout.nav_item, list);
        this.navigationList = list;
        this.mContext = context;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.nav_item, parent, false);
        LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.nav_linear_layout);
        final TextView labelNameTV = (TextView) rowView.findViewById(R.id.nav_text_view);
        ImageView labelImage = (ImageView) rowView.findViewById(R.id.nav_labels);
        final EditText labelEdittext = (EditText) rowView.findViewById(R.id.nav_edit_text);

        labelNameTV.setText(navigationList.get(position).getTitle());
        labelImage.setImageDrawable(navigationList.get(position).getLabelImageView());

        if (editToggle){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editToggle = false;
                Log.e(TAG, "Here " + position);
                String label = labelNameTV.getText().toString();
                labelNameTV.setVisibility(View.GONE);
                labelEdittext.setVisibility(View.VISIBLE);
                labelEdittext.setText(label);

                return false;
            }
        });


        return rowView;
    }



}
