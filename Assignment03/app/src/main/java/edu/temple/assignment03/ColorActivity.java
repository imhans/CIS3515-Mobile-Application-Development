package edu.temple.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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

        final View bgView = findViewById(R.id.bgView);
        //bgView.setBackgroundColor(Color.BLUE);

        spinnerView = findViewById(R.id.spinner);
        ColorAdapter<String> adapter = new ColorAdapter<String>(this, colors);
        spinnerView.setAdapter(adapter);

        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object selectedObj = parent.getItemAtPosition(position);

                switch (selectedObj.toString()) {
                    case "Yellow" : bgView.setBackgroundColor(Color.YELLOW); break;
                    case "Red" : bgView.setBackgroundColor(Color.RED); break;
                    //    spinnerView.setBackgroundColor(Color.WHITE);
                }
                spinnerView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bgView.setBackgroundColor(Color.BLUE);
            }
        });

    }
}