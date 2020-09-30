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
        int colors[] = new int[9];
        colors[0] = Color.WHITE;
        colors[1] = Color.MAGENTA;
        colors[2] = Color.BLUE;
        colors[3] = Color.CYAN;
        colors[4] = Color.DKGRAY;
        colors[5] = Color.LTGRAY;
        colors[6] = Color.GREEN;
        colors[7] = Color.YELLOW;
        colors[8] = Color.RED;

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
            textView.setBackgroundColor(colors[position]);
            textView.setTextColor(Color.BLACK);
        } else {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
