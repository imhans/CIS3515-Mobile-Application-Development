package edu.temple.assignment07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity
        implements PageControlFragment.SearchClickedInterface,
        PageViewerFragment.ViewerInterface,
        BrowserControlFragment.ImageBtnClicked,
        PagerFragment.PagerInterface,
        PageListFragment.PageListInterface {

    PageControlFragment pcf;
    //PageViewerFragment pvf;
    PageListFragment plf;
    BrowserControlFragment bcf;
    PagerFragment pf;
    boolean flag;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        getSupportFragmentManager().putFragment(outState, "pcf", pcf);
        getSupportFragmentManager().putFragment(outState, "pf", pf);
        getSupportFragmentManager().putFragment(outState, "plf", plf);
        getSupportFragmentManager().putFragment(outState, "bcf", bcf);
        super.onSaveInstanceState(outState);
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
            //pvf = new PageViewerFragment();
            bcf = new BrowserControlFragment();
            plf = new PageListFragment();
            pf = new PagerFragment();

            if (flag) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.page_control, pcf)
                        .add(R.id.page_viewer, pf)
                        .add(R.id.page_list, plf)
                        .add(R.id.browser_control, bcf)
                        .addToBackStack(null)
                        .commit();
            }
            else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.page_control, pcf)
                        .add(R.id.page_viewer, pf)
                        .add(R.id.browser_control, bcf)
                        .add(plf, "plf")
                        .addToBackStack(null)
                        .commit();
            }

        } else {
            if (flag) {
                pcf = (PageControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pcf");
                pf = (PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pf");
                plf = (PageListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "plf");
                bcf = (BrowserControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "bcf");
            }
            else {
                pcf = (PageControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pcf");
                pf = (PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pf");
                bcf = (BrowserControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "bcf");
                plf = (PageListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "plf");
            }

        }

    }

    @Override
    public void searchClicked(String url) {
        //pvf.loadWeb(url); this is used for PVF to display the webView directly
        pf.searchPF(url);
    }

    @Override
    public void goPrevious() {
        //pvf.webView.goBack();
        pf.goBackPF();
        Toast.makeText(getApplicationContext(), "aaa", Toast.LENGTH_LONG).show();
    }

    @Override
    public void goNext() {
        //pvf.webView.goForward();
        pf.goForwardPF();
    }

    @Override
    public void fetchURL(String url) {
        pcf.urlText.setText(url);
    }

    @Override
    public void fetchTitle(String title) {
        this.setTitle(title);
        plf.mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPage() {
        //Add a new instance of PVF into the arrayList
        pf.updatePVFs();
        plf.updateList();
    }

    @Override
    public void passPVFs(ArrayList<PageViewerFragment> pvfs) {
        plf.pvfs = pvfs;
        //plf.updateList(); Compared to line 110 in addPage() method, this one only gets null pointer.
    }

    @Override
    public void browserSwiped(int position) { //Orientation - vertical
        //set url
        pcf.urlText.setText(pf.mPagerAdapter.getURL(position));
        //set title
        this.setTitle(pf.mPagerAdapter.getTitle(position));
    }

    @Override
    public void displayPVF(int position) { //Orientation - horizontal
        //display pvf
        pf.mPager.setCurrentItem(position);
        //set url
        pcf.urlText.setText(pf.mPagerAdapter.getURL(position));
        //set title
        this.setTitle(pf.mPagerAdapter.getTitle(position));
    }

}