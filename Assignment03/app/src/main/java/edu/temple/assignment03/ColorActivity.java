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
import android.widget.TextView;

import org.w3c.dom.Text;

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
        spinnerView = findViewById(R.id.spinner);

        //Create and Set the custom adapter, ColorAdapter, to the spinner
        ColorAdapter<String> adapter = new ColorAdapter<String>(this, colors);
        spinnerView.setAdapter(adapter);

        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView selectedView = (TextView) parent.getSelectedView();
                Object selectedObj = parent.getItemAtPosition(position);

                switch (selectedObj.toString()) {
                    case "White" : bgView.setBackgroundColor(Color.WHITE); break;
                    case "Magenta" : bgView.setBackgroundColor(Color.MAGENTA); break;
                    case "Blue" : bgView.setBackgroundColor(Color.BLUE); break;
                    case "Cyan" : bgView.setBackgroundColor(Color.CYAN); break;
                    case "Dark gray" : bgView.setBackgroundColor(Color.DKGRAY); break;
                    case "Light gray" : bgView.setBackgroundColor(Color.LTGRAY); break;
                    case "Gray" : bgView.setBackgroundColor(Color.GRAY); break;
                    case "Green" : bgView.setBackgroundColor(Color.GREEN); break;
                    case "Yellow" : bgView.setBackgroundColor(Color.YELLOW); break;
                    case "Red" : bgView.setBackgroundColor(Color.RED); break;
                }
                selectedView.setBackgroundColor(Color.WHITE);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}