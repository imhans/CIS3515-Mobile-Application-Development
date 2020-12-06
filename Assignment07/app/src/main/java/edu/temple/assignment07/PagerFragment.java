package edu.temple.assignment07;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class PagerFragment extends Fragment {

    ArrayList<PageViewerFragment> pvfs;
    PagerInterface parentActivity;
    ViewPager mPager;
    PVFsStateAdapter mPagerAdapter;
    int currentPosition;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARRAYLIST_PVF = "ArrayList_pvf";
    private static final String CURRENT_POSITION = "Current_position";

    public PagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(ARRAYLIST_PVF, pvfs);
        outState.putInt(CURRENT_POSITION, currentPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            pvfs = (ArrayList<PageViewerFragment>) savedInstanceState.getSerializable(ARRAYLIST_PVF);
            currentPosition = savedInstanceState.getInt(CURRENT_POSITION);
        }

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PagerFragment.PagerInterface) {
            parentActivity = (PagerFragment.PagerInterface) context;
        }
        else {
            throw new RuntimeException("Please implement PagerInterface to attach this fragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            pvfs = new ArrayList<>();
            pvfs.add(new PageViewerFragment());
            parentActivity.passPVFs(pvfs);
            currentPosition = 0;
        }
        else {
            parentActivity.passPVFs(pvfs);
        }

        View l = inflater.inflate(R.layout.fragment_pager, container, false);
        mPager = l.findViewById(R.id.viewpager);
        mPagerAdapter = new PVFsStateAdapter(this.getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(currentPosition);

        mPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Refresh the browser when the viewPager swiped
                parentActivity.browserSwiped(position);
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return l;
    }

    //Add a new PVF to the ArrayList and refresh once the imageButton clicked
    public void updatePVFs() {
        pvfs.add(new PageViewerFragment());

        mPager.getAdapter().notifyDataSetChanged();
        mPager.setCurrentItem(pvfs.size()-1);
    }


    //Load the page using url into PagerFragment
    public void searchPF(String url) {
        if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            pvfs.get(mPager.getCurrentItem()).webView.loadUrl("https://" + url);

        } else{
                pvfs.get(mPager.getCurrentItem()).webView.loadUrl(url);
            }
    }

    public void goBackPF() {
        if (pvfs.get(currentPosition).webView.canGoBack())
            Toast.makeText(getContext(), "can go back", Toast.LENGTH_LONG).show();

        pvfs.get(currentPosition).webView.goBack();
    }

    public void goForwardPF() {

        pvfs.get(currentPosition).webView.goForward();
    }

    public boolean viewIsNull() {
        return pvfs.get(currentPosition).webView.getUrl() == null;
    }

    public Bookmark createBookmark() {
        String url = pvfs.get(currentPosition).webView.getUrl();
        String title = pvfs.get(currentPosition).webView.getTitle();
        Bookmark bookmark = new Bookmark(url, title);
        return bookmark;
    }
    public String getURL() {
        String url = pvfs.get(currentPosition).webView.getUrl();
        return url;
    }

    interface PagerInterface {
        void passPVFs(ArrayList<PageViewerFragment> pvfs);
        void browserSwiped(int position);
    }

    //Custom FragmentStatePagerAdapter
    public class PVFsStateAdapter extends FragmentStatePagerAdapter {

        public PVFsStateAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return pvfs.get(position);
        }

        @Override
        public int getCount() {
            return pvfs.size();
        }

        public String getURL(int position) {
            return pvfs.get(position).webView.getUrl();
        }
        public String getTitle(int position) {
            return pvfs.get(position).webView.getTitle();
        }
    }



}