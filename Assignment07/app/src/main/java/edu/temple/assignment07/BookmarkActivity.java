package edu.temple.assignment07;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {

    ListView listView;
    bookmarkAdapter mAdapter;
    Button closeButton;
    ArrayList<Bookmark> bms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        listView = findViewById(R.id.lvBookmark);

        bms = new ArrayList<Bookmark>();
        Bookmark a = new Bookmark("http://google.com", "Google");
        Bookmark b = new Bookmark("http://google.com", "Google2");
        bms.add(a); bms.add(b);
        String c = Integer.toString(bms.size());
        Log.d("BMs", c);

        mAdapter = new bookmarkAdapter(this, android.R.layout.simple_list_item_2, bms);

        listView.setAdapter(mAdapter);

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
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
                    Toast.makeText(context, "it works", Toast.LENGTH_LONG).show();
                }
            });

            return view;
        }
    }
}