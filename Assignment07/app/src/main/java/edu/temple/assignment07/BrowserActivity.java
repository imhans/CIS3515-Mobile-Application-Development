package edu.temple.assignment07;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        //getSupportFragmentManager().putFragment(outState, "pvf", pvf);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            if ( resultCode == Activity.RESULT_OK) {
                int index = pf.mPager.getCurrentItem();
                pf.pvfs.get(index).webView.loadUrl(data.getStringExtra(getString(R.string.CLICKED_BOOKMARK_URL)));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.title));

        flag = findViewById(R.id.page_list) != null;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //No transaction
        if (getSupportFragmentManager().findFragmentById(R.id.page_control) == null
        && getSupportFragmentManager().findFragmentById(R.id.page_viewer ) == null) {
            pcf = new PageControlFragment();
            pf = new PagerFragment();
            //pvf = new PageViewerFragment();
            bcf = new BrowserControlFragment();
            plf = new PageListFragment();


            if (flag) {
                transaction
                        .add(R.id.page_control, pcf)
                        .add(R.id.page_viewer, pf)
                        .add(R.id.page_list, plf)
                        .add(R.id.browser_control, bcf)
                        .commit();
            }
            else {
                transaction
                        .add(R.id.page_control, pcf)
                        .add(R.id.page_viewer, pf)
                        .add(R.id.browser_control, bcf)
                        .add(plf, "plf")
                        //.addToBackStack(null)
                        .commit();
            }

        } else {
            pcf = (PageControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pcf");
            pf = (PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pf");
            plf = (PageListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "plf");
            bcf = (BrowserControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "bcf");

            if (getSupportFragmentManager().findFragmentById(R.id.page_list) == null) {
                transaction.replace(R.id.page_list, plf).commit();
            }
        }

        // Manage intents sent from external application
        Intent intent = getIntent();
        handleView(intent);
    }


    void handleView(Intent intent) {
        String uri = intent.getStringExtra(Intent.EXTRA_TEXT);
        if ( uri != null ) {
            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.EXTERNAL_URL), uri);
            editor.apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_share, menu);
        //return true;
        return super.onCreateOptionsMenu(menu);
    }

    public void setIntent(String string) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, string);
        startActivity(Intent.createChooser(sendIntent, null));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                setIntent(pf.getURL());
                break;
            default:
                break;
        }
        return true;
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
    public boolean isNull() {
        return pf.viewIsNull();
    }

    @Override
    public Bookmark getBookmark() {
        return pf.createBookmark();
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