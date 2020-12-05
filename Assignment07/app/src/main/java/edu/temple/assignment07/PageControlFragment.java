package edu.temple.assignment07;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class PageControlFragment extends Fragment {
    View l;
    EditText urlText;
    ImageButton search;
    ImageButton next;
    ImageButton previous;
    SearchClickedInterface parentActivity;

    private static final String URL_STRING = "url";

    public PageControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SearchClickedInterface) {
            parentActivity = (SearchClickedInterface) context;
        }
        else {
            throw new RuntimeException("Please implement SearchClickedInterface to attach this fragment");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(URL_STRING, urlText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_control, container, false);
        urlText = l.findViewById(R.id.tvURL);
        search = l.findViewById(R.id.btnSearch);
        previous = l.findViewById(R.id.btnPrevious);
        next = l.findViewById(R.id.btnNext);

        //Restore the EditText
        if ( savedInstanceState != null )
            urlText.setText(savedInstanceState.getString(URL_STRING));

        //Search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //URL Validation
                if (!android.webkit.URLUtil.isValidUrl(urlText.getText().toString())
                || android.webkit.URLUtil.isHttpUrl(urlText.getText().toString())) {
                    String urlString = urlText.getText().toString();
                    urlString = urlString.substring(urlString.lastIndexOf('/')+1);
                    urlText.setText(urlString);
                }
                if (!urlText.getText().toString().matches("")) {
                    parentActivity.searchClicked(urlText.getText().toString());
                }
                else {
                    Toast.makeText(getContext(), "You must type in a url", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Previous button
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goPrevious();
            }
        });

        //Next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goNext();
            }
        });

        return l;
    }
    interface SearchClickedInterface {
        void searchClicked(String url);
        void goPrevious();
        void goNext();
    }

}