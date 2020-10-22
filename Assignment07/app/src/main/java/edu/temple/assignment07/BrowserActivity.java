package edu.temple.assignment07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity {

    PageControlFragment pcf;
    PageViewerFragment pvf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pcf = new PageControlFragment();
        pvf = new PageViewerFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.page_control, pcf)
                .add(R.id.page_viewer, pvf)
                .commit();
    }
}