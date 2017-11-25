package me.varunon9.fakelock.help;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import me.varunon9.fakelock.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {


    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        WebView helpWebView = (WebView) rootView.findViewById(R.id.help_webView);
        helpWebView.loadUrl("file:///android_asset/help.html");
        return rootView;
    }

}
