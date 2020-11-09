package edu.temple.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageListFragment extends Fragment {

    View l;
    ListView listView;
    ListViewAdapter mAdapter;
    ArrayList<PageViewerFragment> pageList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PageListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageListFragment newInstance(String param1, String param2) {
        PageListFragment fragment = new PageListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_list, container, false);
        listView = l.findViewById(R.id.lvPages);
        pageList = new ArrayList<>();
        TextView textView;

        // Restore the pageList
        if ( savedInstanceState != null ) {
            //pageList.
        } else { // Nothing to restore
            pageList.add(new PageViewerFragment());
        }
        //ListViewAdapter<PageViewerFragment> adapter = new ListViewAdapter<PageViewerFragment>(getContext() ,arrayList);
        mAdapter = new ListViewAdapter(getContext(), android.R.layout.simple_list_item_1, pageList);
        listView.setAdapter(mAdapter);

        //if ( listView == null ) {
//            textView = new TextView(getContext());
//            textView.setText("Auto saved first item");
//            listView.addView(textView);
//            Log.d("testList", "done");
        //}
        return l;
    }

    public void updateList(PageViewerFragment pvf) {
        pageList.add(pvf);
        mAdapter.notifyDataSetChanged();
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
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            TextView textView;


            if ( listItem == null ) {
                textView = new TextView(context);
                textView.setText("Blank");
            }
            else {
                textView = (TextView) convertView;
            }

            PageViewerFragment currentPVF = pages.get(position);

//            if ( currentPVF != null )
//                textView.setText("Blank");
//            else
//                textView.setText("null");

            return textView;
            //return super.getView(position, convertView, parent);
        }
    }

}