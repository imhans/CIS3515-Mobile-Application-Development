package edu.temple.assignment07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.SearchClickedInterface {

    PageControlFragment pcf;
    PageViewerFragment pvf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.title));


        pcf = new PageControlFragment();
        pvf = new PageViewerFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.page_control, pcf)
                .add(R.id.page_viewer, pvf)
                .commit();
    }

    @Override
    public void searchClicked(String url) {
        pvf.changeText(url);
    }
}