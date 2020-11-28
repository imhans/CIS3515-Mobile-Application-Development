package edu.temple.assignment07;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class BrowserControlFragment extends Fragment {

    View l;
    ImageButton btnNewPage;
    ImageButton btnAddBookmark;
    ImageButton btnBookmarks;
    ImageBtnClicked parentActivity;
    ArrayList<Bookmark> bookmarkArrayList;

    private static final String ARRAYLIST_BMs = "ArrayList_bookmarks";
    private static final String ARRAYLIST_BMs_String = "ArrayList_bookmarks_String";

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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(ARRAYLIST_BMs, bookmarkArrayList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loadBookmarks();

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
                getActivity().startActivityForResult(intent, 0);
            }
        });

        btnAddBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the current webView is null
                if (!parentActivity.isNull()) {
                    Bookmark bookmark = parentActivity.getBookmark();
                    bookmarkArrayList.add(bookmark);
                    saveAddedBookmarks(bookmarkArrayList);
                    Toast.makeText(getContext(), "A new bookmark is successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "cannot bookmark a blank page", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return l;
    }

    private void saveAddedBookmarks(ArrayList arrayList) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(ARRAYLIST_BMs_String, json);
        editor.apply();
    }
    private void loadBookmarks() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ARRAYLIST_BMs_String, null);
        Type type = new TypeToken<ArrayList<Bookmark>>() {}.getType();
        bookmarkArrayList = gson.fromJson(json, type);

        if ( bookmarkArrayList == null ) {
            bookmarkArrayList = new ArrayList<>();
        }
    }

    interface ImageBtnClicked {
        void addPage();
        boolean isNull();
        Bookmark getBookmark();
    }
}