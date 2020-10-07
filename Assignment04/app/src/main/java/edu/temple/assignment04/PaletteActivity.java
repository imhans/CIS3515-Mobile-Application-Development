package edu.temple.assignment04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
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
        this.setTitle(getResources().getString(R.string.app_title1));

        //Create ArrayList to store colors
        colors = new ArrayList<String>();
        Resources res = getResources();
        String[] colors_array = res.getStringArray(R.array.colors);

        //Add colors
        colors.add(colors_array[0]);
        colors.add(colors_array[1]);
        colors.add(colors_array[2]);
        colors.add(colors_array[3]);
        colors.add(colors_array[4]);
        colors.add(colors_array[5]);
        colors.add(colors_array[6]);
        colors.add(colors_array[7]);
        colors.add(colors_array[8]);

        gv = findViewById(R.id.gvColors);
        //Create and Set the custom adapter, ColorsAdapter, to the GridView
        ColorsAdapter<String> adapter = new ColorsAdapter<String>(this, colors);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),"dd", Toast.LENGTH_LONG);
                Intent intent = new Intent(getApplicationContext(), CanvasActivity.class);
                intent.putExtra("colorString", colors.get(position).toString());
                View selectedView = view;
                ColorDrawable viewColor = (ColorDrawable) view.getBackground();
                intent.putExtra("colorInt",viewColor.getColor());
                startActivity(intent);
            }
        });
    }
    //Called when the user clicks a color
    public void displayCanvas(View v) {
        Intent intent = new Intent(this, CanvasActivity.class);
        //EditText editText = (EditText) findViewById(R.id.gvColors);
    }
}