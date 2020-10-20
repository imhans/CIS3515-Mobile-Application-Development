package edu.temple.assignment04;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CanvasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CanvasFragment extends Fragment {
    View bg_color;
    TextView txt_color;
//    // TODO: Rename and change types and number of parameters
//    public static CanvasFragment newInstance(String param1, String param2) {
//        CanvasFragment fragment = new CanvasFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View l = inflater.inflate(R.layout.fragment_canvas, container, false);
        bg_color = l.findViewById(R.id.bgColor);
        txt_color = l.findViewById(R.id.txtColor);
        return l;
    }

    public void changeColor(int color) {
        bg_color.setBackgroundColor(color);
    }
    public void changeText(String color) {
        txt_color.setText(color);
    }
}