package edu.temple.assignment04;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CanvasActivity extends AppCompatActivity {

    TextView textView;
    View bgView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String colorString = intent.getStringExtra("colorString");
        Integer colorInt = intent.getIntExtra("colorInt", 0);

        // Capture the layout's TextView and set the string as its text
        textView = findViewById(R.id.txtColor);
        textView.setText(colorString);
        textView.setTextSize(30);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setAllCaps(true);
        textView.setTextColor(Color.BLACK);

        // Capture the background color code and set the background color of the activity
        bgView = findViewById(R.id.bgColor);
        bgView.setBackgroundColor(colorInt);
    }
}
