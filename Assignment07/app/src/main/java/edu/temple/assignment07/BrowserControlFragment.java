package edu.temple.assignment07;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class BrowserControlFragment extends Fragment {

    View l;
    ImageButton btnNewPage;
    ImageButton btnAddBookmark;
    ImageButton btnBookmarks;
    ImageBtnClicked parentActivity;


    public BrowserControlFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BrowserControlFragment.ImageBtnClicked) {
            parentActivity = (BrowserControlFragment.ImageBtnClicked) context;
        }
        else {
            throw new RuntimeException("Please implement ImageBtnClicked to attach this fragment");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_browser_control, container, false);
        btnBookmarks = l.findViewById(R.id.btnBookmarks);
        btnAddBookmark = l.findViewById(R.id.btnAddBookmark);
        btnNewPage = l.findViewById(R.id.btnNewPage);

        btnNewPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                parentActivity.addPage();
                Log.d("test", "button is successfully clicked");
            }
        });

        btnBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(getActivity(), BookmarkActivity.class));
                getActivity().startActivity(intent);
            }
        });

        return l;
    }
    interface ImageBtnClicked {
        void addPage();
    }
}