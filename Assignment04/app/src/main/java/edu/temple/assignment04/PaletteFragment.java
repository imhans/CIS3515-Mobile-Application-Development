package edu.temple.assignment04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaletteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaletteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String COLOR_KEYS = "Colors";

    private String[] colors;
    ColorSelectedInterface parentActivity;


    public PaletteFragment() { }

    // TODO: Rename and change types and number of parameters
    public static PaletteFragment newInstance(String[] colors) {
        PaletteFragment fragment = new PaletteFragment();
        Bundle args = new Bundle();
        args.putStringArray(COLOR_KEYS, colors);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( getArguments() != null ) {
            colors = getArguments().getStringArray(COLOR_KEYS);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if ( context instanceof ColorSelectedInterface )
            parentActivity = (ColorSelectedInterface) context;
        else
            throw new RuntimeException("Please implement ColorSelectedInterface to attach this fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View l = inflater.inflate(R.layout.fragment_palette, container, false);
        GridView gv = l.findViewById(R.id.gvColors);
        gv.setAdapter(new ArrayAdapter<>((Context) parentActivity, android.R.layout.simple_list_item_1, colors));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentActivity.colorSelected(position);
            }
        });
        return l;
    }

    interface ColorSelectedInterface {
        void colorSelected(int index);
    }
}