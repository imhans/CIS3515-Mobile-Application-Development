package edu.temple.assignment07;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.Serializable;

public class PageViewerFragment extends Fragment implements Serializable, Parcelable {

    View l;
    WebView webView;
    ViewerInterface parentActivity;
    private String pageTitle;

    public PageViewerFragment() {
        // Required empty public constructor
    }

    protected PageViewerFragment(Parcel in) {
        pageTitle = in.readString();
    }

    public static final Creator<PageViewerFragment> CREATOR = new Creator<PageViewerFragment>() {
        @Override
        public PageViewerFragment createFromParcel(Parcel in) {
            return new PageViewerFragment(in);
        }

        @Override
        public PageViewerFragment[] newArray(int size) {
            return new PageViewerFragment[size];
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if ( context instanceof ViewerInterface ) {
            parentActivity = (ViewerInterface) context;
        }
        else {
            throw new RuntimeException("Please implement ViewerInterface to attach this fragment");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        l = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        webView = l.findViewById(R.id.webView);

        // Load a link comes from different app
        String url_string_id = getString(R.string.EXTERNAL_URL);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        if ( sharedPref.contains(url_string_id)) {
            String string_url = sharedPref.getString(url_string_id, null);
            webView.loadUrl(string_url);
            sharedPref.edit().remove(url_string_id).commit();
        }

        if (savedInstanceState != null)
            webView.restoreState(savedInstanceState);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //Fetch the url on current page and set the EditText with it
                super.onPageStarted(view, url, favicon);
                parentActivity.fetchURL(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //Fetch the title name of the current page and Set the action bar title
                pageTitle = webView.getTitle();
                parentActivity.fetchTitle(pageTitle);

            }
        });

        return l;
    }

    public String getTitle() {
        String title;
        if (webView != null) {
            title = webView.getTitle();
            return title == null || title.isEmpty() ? "Blank Page" : title;
        } else
        return "Blank Page";
    }

    interface ViewerInterface {
        void fetchURL(String url);
        void fetchTitle(String title);
    }

    /* Needed the methods to avoid Runtime exception: Parcelable encountered IOException writing serializable object */
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pageTitle);
    }

}