package com.luckysun.tabfragmentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TabFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int onCreateView = 0;
    private int onCreate = 0;

    private String mParam1;
    private TextView textView;

    public static TabFragment newInstance(TabInfo info) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, info.name);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            onCreate ++ ;
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam1 += "\n onCreate : " + onCreate;
            Log.d(MainActivity.TAG, mParam1 + " fragment onCreate ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        textView = (TextView) rootView.findViewById(R.id.testview);
        onCreateView++;
        textView.setText(mParam1 + " \n onCreateView: " + onCreateView);
        Log.d(MainActivity.TAG, mParam1 + " fragment onCreateView ");
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
