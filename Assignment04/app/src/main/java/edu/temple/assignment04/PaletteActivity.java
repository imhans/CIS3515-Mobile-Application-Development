package edu.temple.assignment04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PaletteActivity extends AppCompatActivity {

    GridView gv;
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
        colors.add("Green");
        colors.add("Yellow");
        colors.add("Red");

        gv = findViewById(R.id.gvColors);
        //Create and Set the custom adapter, ColorsAdapter, to the GridView
        ColorsAdapter<String> adapter = new ColorsAdapter<String>(this, colors);
        gv.setAdapter(adapter);

    }
}