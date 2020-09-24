package edu.temple.assignment03;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorAdapter<String> extends BaseAdapter {
    Context context;
    ArrayList<String> colors;

    public ColorAdapter(Context context, ArrayList<String> colors) {
        this.context = context;
        this.colors = colors;
    }
    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int position) {
        return colors.get(position);
    }

    @Override //not using
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create a View
        TextView textView = new TextView(context);
        //Set things for the View
        textView.setPadding(8, 8, 8, 8);
        textView.setText(getItem(position).toString());

        //Return the View
        return textView;
    }
}
