package edu.temple.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagerFragment extends Fragment {

    ArrayList<PageViewerFragment> pvfs;
    PagerInterface parentActivity;
    ViewPager mPager;
    PVFsStateAdapter mPagerAdapter;
    int currentItem;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARRAYLIST_PVF = "ArrayList_pvf";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PagerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PagerFragment newInstance(ArrayList<PageViewerFragment> pageList) {
        PagerFragment fragment = new PagerFragment();
        if (pageList != null) {
            Bundle args = new Bundle();
            args.putSerializable(ARRAYLIST_PVF, pageList);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( getArguments() != null ) {

            //pvfs = ( ArrayList<PageViewerFragment> )  getArguments().getSerializable("ARRAYLIST_PVF");
        }

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

        if (savedInstanceState != null) {
            //
        } else {
            pvfs = new ArrayList<>();
            pvfs.add(new PageViewerFragment());
            parentActivity.passPVFs(pvfs);
        }


        View l = inflater.inflate(R.layout.fragment_pager, container, false);
        mPager = l.findViewById(R.id.viewpager);
        mPagerAdapter = new PVFsStateAdapter(this.getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(0);

        mPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Check the index of the current page
                currentItem = pvfs.indexOf(position);
                Log.d("position", "changed "+ position);
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
        pvfs.get(mPager.getCurrentItem()).webView.goBack();
    }

    public void goForwardPF() {
        pvfs.get(mPager.getCurrentItem()).webView.goForward();
    }

    interface PagerInterface {
        void passPVFs(ArrayList<PageViewerFragment> pvfs);
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

    }



}