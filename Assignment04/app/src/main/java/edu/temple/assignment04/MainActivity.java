package edu.temple.assignment04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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

public class MainActivity extends AppCompatActivity implements PaletteFragment.ColorSelectedInterface {

//    GridView gv;
//    ArrayList<String> colors;
    CanvasFragment canvasFragment;
    PaletteFragment paletteFragment;
    int[] colors_int = {Color.WHITE, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.LTGRAY, Color.GREEN, Color.YELLOW, Color.RED};
    String[] colors_str = {"White", "Magenta", "Blue", "Cyan", "Dark Gray", "Light Gray", "Green", "Yellow", "Red"};
            //getResources().getStringArray(R.array.colors);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.app_title1));

//        //Create ArrayList to store colors
//        colors = new ArrayList<String>();
//        Resources res = getResources();
//        String[] colors_array = res.getStringArray(R.array.colors);
//
//        //Add colors
//        colors.add(colors_array[0]);
//        colors.add(colors_array[1]);
//        colors.add(colors_array[2]);
//        colors.add(colors_array[3]);
//        colors.add(colors_array[4]);
//        colors.add(colors_array[5]);
//        colors.add(colors_array[6]);
//        colors.add(colors_array[7]);
//        colors.add(colors_array[8]);
//
//        gv = findViewById(R.id.gvColors);
//        //Create and Set the custom adapter, ColorsAdapter, to the GridView
//        ColorsAdapter<String> adapter = new ColorsAdapter<String>(this, colors);
//        gv.setAdapter(adapter);
//
//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Toast.makeText(getApplicationContext(),"dd", Toast.LENGTH_LONG);
//                Intent intent = new Intent(getApplicationContext(), CanvasActivity.class);
//                intent.putExtra("colorString", colors.get(position).toString());
//                View selectedView = view;
//                ColorDrawable viewColor = (ColorDrawable) view.getBackground();
//                intent.putExtra("colorInt",viewColor.getColor());
//                startActivity(intent);
//            }
//        });
        paletteFragment = new PaletteFragment();
        canvasFragment = new CanvasFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1, paletteFragment.newInstance(getResources().getStringArray(R.array.colors)))
                .add(R.id.container_2, canvasFragment)
                .commit();

    }

    @Override
    public void colorSelected(int index) {
        canvasFragment.changeColor(colors_int[index]);
        canvasFragment.changeText(colors_str[index]);
    }
}