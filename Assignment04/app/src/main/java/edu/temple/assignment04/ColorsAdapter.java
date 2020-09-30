package edu.temple.assignment04;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorsAdapter<String> extends BaseAdapter {

    Context context;
    ArrayList<String> colors;

    public ColorsAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.colors = arrayList;
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
        return colors.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            //Create a View
            textView = new TextView(context);
            //Set things for the View
            textView.setPadding(10, 50, 10, 50);
            textView.setTextSize(20);
            textView.setAllCaps(true);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setText(getItem(position).toString());
        } else {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
