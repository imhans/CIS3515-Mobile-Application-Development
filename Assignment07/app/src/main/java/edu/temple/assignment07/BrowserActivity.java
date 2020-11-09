package edu.temple.assignment07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.MalformedURLException;

public class BrowserActivity extends AppCompatActivity
        implements PageControlFragment.SearchClickedInterface,
        PageViewerFragment.ViewerInterface,
        BrowserControlFragment.ImageBtnClicked {

    PageControlFragment pcf;
    PageViewerFragment pvf;
    PageListFragment plf;
    BrowserControlFragment bcf;
    boolean flag;

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

        flag = findViewById(R.id.page_list) != null;

        //No transaction
        if (getSupportFragmentManager().findFragmentById(R.id.page_control) == null
        && getSupportFragmentManager().findFragmentById(R.id.page_viewer ) == null) {
            pcf = new PageControlFragment();
            pvf = new PageViewerFragment();
            bcf = new BrowserControlFragment();
            plf = new PageListFragment();

            if (flag) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.page_control, pcf)
                        .add(R.id.page_viewer, pvf)
                        .add(R.id.page_list, plf)
                        .add(R.id.browser_control, bcf)
                        .addToBackStack(null)
                        .commit();
            }
            else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.page_control, pcf)
                        .add(R.id.page_viewer, pvf)
                        .add(R.id.browser_control, bcf)
                        .addToBackStack(null)
                        .commit();
            }

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

    @Override
    public void fetchTitle(String title) {
        this.setTitle(title);
    }

    @Override
    public void addPage() {
        //Add a new instance of PVF into the arrayList
        plf.updateList(new PageViewerFragment());
    }
}