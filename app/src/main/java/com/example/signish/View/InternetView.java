package com.example.signish.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.signish.R;



public class InternetView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String parametro;

    WebView webView;



    public InternetView() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InternetView newInstance(String param1, String param2) {
        InternetView fragment = new InternetView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            parametro = bundle.getString("parametro");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ui_internet = inflater.inflate(R.layout.int_fragment, container, false);

        webView = ui_internet.findViewById(R.id.wid_webView);

        // webView.loadData(parametro,"text/html; charset=UTF-8", null);
        webView.loadData(parametro,"text/html","UTF-8");

        return ui_internet;

    }



}
