package edu.temple.assignment07;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class BookmarkActivity extends AppCompatActivity {


    ListView listView;
    bookmarkAdapter mAdapter;
    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        //listView = findViewById(R.id.lvBookmark);
        //ArrayAdapter mAdapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_2);
        //mAdapter = new bookmarkAdapter(this.getApplicationContext(), android.R.layout.simple_list_item_2);
        //listView.setAdapter(mAdapter);

    }


    public class bookmarkAdapter extends ArrayAdapter<String> {

        Context context;
        ImageButton deleteButton;

        public bookmarkAdapter(@NonNull Context context, int resource) {
            super(context, resource);
            this.context = context;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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
            textView.setText("Testing");

            return textView;
        }
    }
}