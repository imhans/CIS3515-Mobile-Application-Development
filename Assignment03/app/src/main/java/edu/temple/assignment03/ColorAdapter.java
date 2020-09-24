package edu.temple.assignment03;

import android.content.Context;
import android.graphics.Color;
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
        int colors[] = new int[10];
        colors[0] = Color.WHITE;
        colors[1] = Color.MAGENTA;
        colors[2] = Color.BLUE;
        colors[3] = Color.CYAN;
        colors[4] = Color.DKGRAY;
        colors[5] = Color.LTGRAY;
        colors[6] = Color.GRAY;
        colors[7] = Color.GREEN;
        colors[8] = Color.YELLOW;
        colors[9] = Color.RED;

        TextView textView;
        if (convertView == null) {
            //Create a View
            textView = new TextView(context);
            //Set things for the View
            textView.setPadding(20, 10, 0, 10);
            textView.setTextSize(20);
            textView.setText(getItem(position).toString());
            textView.setBackgroundColor(colors[position]); //Setting BG colors
            textView.setTextColor(Color.BLACK);
        } else {
            textView = (TextView) convertView;
        }

        //Return the View
        return textView;
    }
}
