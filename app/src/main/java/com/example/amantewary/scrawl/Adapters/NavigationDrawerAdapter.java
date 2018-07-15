package com.example.amantewary.scrawl.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amantewary.scrawl.Handlers.NavgitationModel;
import com.example.amantewary.scrawl.R;

import java.util.ArrayList;

public class NavigationDrawerAdapter extends ArrayAdapter<NavgitationModel> implements View.OnClickListener {

    private ArrayList<NavgitationModel> navigationList;
    Context mContext;


    @Override
    public void onClick(View view) {

    }

    public NavigationDrawerAdapter(ArrayList<NavgitationModel> list, Context context) {
        super(context, R.layout.nav_item, list);
        this.navigationList = list;
        this.mContext=context;

    }

    private static class ViewHolder {
        EditText labelEditText;
        TextView labelText;
        ImageView labelImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.nav_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.nav_text_view);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.nav_labels);
        textView.setText(navigationList.get(position).getTitle());
        imageView.setImageDrawable(navigationList.get(position).getLabelImageView());

        return rowView;
    }

}
