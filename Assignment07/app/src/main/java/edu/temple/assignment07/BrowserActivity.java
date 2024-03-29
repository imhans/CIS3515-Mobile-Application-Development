package edu.temple.assignment07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.MalformedURLException;

public class BrowserActivity extends AppCompatActivity
        implements PageControlFragment.SearchClickedInterface,
        PageViewerFragment.ViewerInterface {

    PageControlFragment pcf;
    PageViewerFragment pvf;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "pcf", pcf);
        getSupportFragmentManager().putFragment(outState, "pvf", pvf);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.title));


        if (getSupportFragmentManager().findFragmentById(R.id.page_control) == null
        && getSupportFragmentManager().findFragmentById(R.id.page_viewer ) == null) {
            pcf = new PageControlFragment();
            pvf = new PageViewerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.page_control, pcf)
                    .add(R.id.page_viewer, pvf)
                    .addToBackStack(null)
                    .commit();
        } else {
            pcf = (PageControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pcf");
            pvf = (PageViewerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pvf");
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