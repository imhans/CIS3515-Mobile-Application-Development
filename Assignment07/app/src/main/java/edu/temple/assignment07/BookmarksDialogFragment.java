package edu.temple.assignment07;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class BookmarksDialogFragment extends DialogFragment {

    BookmarkDeletion parentActivity;
    ArrayList bookmarkArrayList;
    private static final String ARRAYLIST_BMs_String = "ArrayList_bookmarks_String";

    public BookmarksDialogFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BookmarksDialogFragment.BookmarkDeletion) {
            parentActivity = (BookmarksDialogFragment.BookmarkDeletion) context;
        }
        else {
            throw new RuntimeException("Please implement BookmarkDeletion to attach this fragment");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.bookmark_delete_dialog)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int index = parentActivity.fetchIndex();
                        updateBookmarks(index); //Delete and Update the sharedPreferences
                        parentActivity.refreshListView(index); // Refresh(notify) the list for the bookmarks
                        Toast.makeText(getContext(), "The bookmark is successfully deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.cancel();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void updateBookmarks(int index) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ARRAYLIST_BMs_String, null);
        Type type = new TypeToken<ArrayList<Bookmark>>() {}.getType();
        bookmarkArrayList = gson.fromJson(json, type);

        // fetch the index for the position
        bookmarkArrayList.remove(index);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        json = gson.toJson(bookmarkArrayList);
        editor.putString(ARRAYLIST_BMs_String, json);
        editor.apply();
    }

    interface BookmarkDeletion {
        int fetchIndex();
        void refreshListView(int index);
    }
}