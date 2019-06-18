package com.example.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class frg_panel extends Fragment {

    long timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timer = getArguments().getLong("time");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_panel, container, false);
        TextView tvTime = (TextView) rootView.findViewById(R.id.tvTime);
//        SimpleDateFormat time = null;
//        time = new SimpleDateFormat();
        tvTime.setText(String.format("%s",timer));
        return rootView;
    }
}