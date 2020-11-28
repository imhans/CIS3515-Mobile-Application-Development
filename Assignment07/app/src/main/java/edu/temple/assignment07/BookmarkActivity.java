package edu.temple.assignment07;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity implements BookmarksDialogFragment.BookmarkDeletion {

    ListView listView;
    bookmarkAdapter mAdapter;
    Button closeButton;
    ArrayList<Bookmark> bookmarkArrayList;
    int clickedIndex;

    private static final String ARRAYLIST_BMs_String = "ArrayList_bookmarks_String";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        closeButton = findViewById(R.id.btnClose);
        listView = findViewById(R.id.lvBookmark);

        // Load Bookmarks from the storage
        loadBookmarks();

        mAdapter = new bookmarkAdapter(this, android.R.layout.simple_list_item_2, bookmarkArrayList);

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String page = bookmarkArrayList.get(position).url;
                Intent returnIntent = new Intent();
                returnIntent.putExtra(getString(R.string.CLICKED_BOOKMARK_URL), page);
                setResult(Activity.RESULT_OK, returnIntent);

                finish();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadBookmarks() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ARRAYLIST_BMs_String, null);
        Type type = new TypeToken<ArrayList<Bookmark>>() {}.getType();
        bookmarkArrayList = gson.fromJson(json, type);

        if ( bookmarkArrayList == null ) {
            bookmarkArrayList = new ArrayList<>();
        }
    }

    private void displayAlertDialog() {
        DialogFragment newFragment = new BookmarksDialogFragment();
        newFragment.show(getSupportFragmentManager(), "delete");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public int fetchIndex() {
        return clickedIndex;
    }
    @Override
    public void refreshListView(int index) {
        mAdapter.bookmarks.remove(index);
        mAdapter.notifyDataSetChanged();
    }

    public class bookmarkAdapter extends ArrayAdapter<Bookmark> {

        Context context;
        ArrayList<Bookmark> bookmarks;


        public bookmarkAdapter(@NonNull Context context, int resource, ArrayList<Bookmark> bookmarks) {
            super(context, resource, bookmarks);
            this.context = context;
            this.bookmarks = bookmarks;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            TextView textView;
            ImageButton deleteButton;

            if ( view == null ) {
                view = View.inflate(getContext(), R.layout.bookmark_list_item, null);
            }

            textView = view.findViewById(R.id.tvTitle);
            deleteButton = view.findViewById(R.id.btnDelete);

            textView.setPadding(0,26,0,20);
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            textView.setText(bookmarks.get(position).getTitle());

            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    clickedIndex = position;
                    displayAlertDialog();
                }
            });

            return view;
        }
    }
}