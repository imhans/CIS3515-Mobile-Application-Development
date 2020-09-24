package edu.temple.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    Spinner spinnerView;
    ArrayList<String> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create ArrayList to store colors
        colors = new ArrayList<String>();
        //Add colors
        colors.add("White");
        colors.add("Magenta");
        colors.add("Blue");
        colors.add("Cyan");
        colors.add("Dark gray");
        colors.add("Light gray");
        colors.add("Gray");
        colors.add("Green");
        colors.add("Yellow");
        colors.add("Red");

        spinnerView = findViewById(R.id.spinner);
        ColorAdapter<String> adapter = new ColorAdapter<String>(this, colors);
        spinnerView.setAdapter(adapter);
    }
}