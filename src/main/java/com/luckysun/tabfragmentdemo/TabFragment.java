package com.luckysun.tabfragmentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
    private TextView mLoadingView;

    private boolean isLoaded;


    private Handler mHandler = new Handler() {
    };

    public static TabFragment newInstance(TabInfo info) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, info.name);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            onCreate++;
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.d(MainActivity.TAG, mParam1 + " fragment onCreate ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        textView = (TextView) rootView.findViewById(R.id.testview);
        mLoadingView = (TextView) rootView.findViewById(R.id.loading_view);

        showView(false);

        onCreateView++;
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


    private void showView(boolean isShow) {
        if (isShow) {
            textView.setVisibility(View.VISIBLE);
            mLoadingView.setVisibility(View.GONE);
            isLoaded = true;
        } else {
            textView.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.VISIBLE);
            isLoaded = false;
        }
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void loadDatas() {
        Log.d(MainActivity.TAG, mParam1 + " fragment loadDatas ");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showView(true);
                textView.setText(mParam1 +  "\n onCreate : " + onCreate + " \n onCreateView: " + onCreateView);
            }
        }, 2 * 1000);
    }
}
