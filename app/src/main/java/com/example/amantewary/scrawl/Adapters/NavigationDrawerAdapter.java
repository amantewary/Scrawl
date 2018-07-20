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
import com.example.amantewary.scrawl.R;

import java.util.ArrayList;

public class NavigationDrawerAdapter extends ArrayAdapter<NavgitationModel> {

    private String TAG = NavigationDrawerAdapter.class.getCanonicalName();
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
                if(editToggle){
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
                    Log.e(TAG, "Here true" + position);
                    labelImage.setImageResource(R.drawable.ic_bookmark_grey_24dp);
                    String label = labelNameTV.getText().toString();
                    viewSwitcher.showNext();
                    labelEdittext.setText(label);

                } else {
                    editToggle = true;
                    viewSwitcher.showPrevious();
                    labelImage.setImageDrawable(navigationList.get(position).getLabelImageView());
                    Log.e(TAG, "Here " + position);

                }


                return true;
            }
        });

        return rowView;
    }


}
