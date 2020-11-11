package edu.temple.assignment07;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.net.URI;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class PageViewerFragment extends Fragment {

    View l;
    WebView webView;
    ViewerInterface parentActivity;
    private String pageTitle;

    public PageViewerFragment() {
        // Required empty public constructor
    }

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
        webView.loadUrl("https://google.com"); //To see if pvf is loaded into pf

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

    public void loadWeb(String url) {
        if ( !(url.startsWith("http://") || url.startsWith("https://")) )
            webView.loadUrl("https://" + url);
        else
            webView.loadUrl(url);

    }
    public String getTitle() {
        String title;
        if (webView != null) {
            title = webView.getTitle();
            return title == null || title.isEmpty() ? webView.getUrl() : title;
        } else
        return "Blank Page";
    }

    interface ViewerInterface {
        void fetchURL(String url);
        void fetchTitle(String title);
    }
}