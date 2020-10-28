package edu.temple.assignment07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.MalformedURLException;

public class BrowserActivity extends AppCompatActivity
        implements PageControlFragment.SearchClickedInterface,
        PageViewerFragment.ViewerInterface {

    PageControlFragment pcf;
    PageViewerFragment pvf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.title));

        pcf = new PageControlFragment();
        pvf = new PageViewerFragment();

        if (getSupportFragmentManager().findFragmentById(R.id.page_control) == null
        && getSupportFragmentManager().findFragmentById(R.id.page_viewer ) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.page_control, pcf)
                    .add(R.id.page_viewer, pvf)
                    .addToBackStack(null)
                    .commit();
        } else {

        }

    }

    @Override
    public void searchClicked(String url) {
        pvf.loadWeb(url);
    }

    @Override
    public void goPrevious() {
        pvf.webView.goBack();
    }

    @Override
    public void goNext() {
        pvf.webView.goForward();
    }

    @Override
    public void fetchURL(String url) {
        pcf.urlText.setText(url);
    }
}