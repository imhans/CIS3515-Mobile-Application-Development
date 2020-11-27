package edu.temple.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PageListFragment extends Fragment {

    View l;
    ListView listView;
    ListViewAdapter mAdapter;
    ArrayList<PageViewerFragment> pvfs;
    PageListInterface parentActivity;

    private static final String ARRAYLIST_PVF = "ArrayList_pvf";

    public PageListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PageListFragment.PageListInterface) {
            parentActivity = (PageListFragment.PageListInterface) context;
        }
        else {
            throw new RuntimeException("Please implement PageListInterface to attach this fragment");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( savedInstanceState != null ) {
            pvfs = (ArrayList<PageViewerFragment>) savedInstanceState.getSerializable(ARRAYLIST_PVF);
            //Toast.makeText(getContext(), "ListView is NOT NULL: ", Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(getContext(), "ListView is NULL: ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        l = inflater.inflate(R.layout.fragment_page_list, container, false);
        listView = l.findViewById(R.id.lvPages);

        mAdapter = new ListViewAdapter(getContext(), android.R.layout.simple_list_item_1, pvfs);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //display the pvf(selected index of listView) into PVF
                //Modify the actionbar Title to the linked page
                //Modify the url
                parentActivity.displayPVF(position);
            }
        });
        return l;
    }

    public void updateList() {
        mAdapter.notifyDataSetChanged();
    }

    interface PageListInterface {
        /*
        The item of the listView is clicked,
        -display the certain pvf
        -set the title
        -set the url
        * */
        void displayPVF(int position);
    }

    public class ListViewAdapter extends ArrayAdapter<PageViewerFragment> {

        Context context;
        ArrayList<PageViewerFragment> pages;

        public ListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PageViewerFragment> pages) {
            super(context, resource, pages);
            this.context = context;
            this.pages = pages;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            TextView textView;
            if (convertView instanceof TextView) {
                textView = (TextView) convertView;

            } else {
                textView = new TextView(context);
                textView.setPadding(5,8,8,5);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(14);
                textView.setText("Blank");
            }
            textView.setText(pages.get(position).getTitle());

            return textView;
        }

    }

}