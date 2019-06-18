package com.example.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class frg_panel extends Fragment {

    TextView tvTime;
    String timer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_panel, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.tvTime);
        SimpleDateFormat time = null;
        time = new SimpleDateFormat();
        Bundle args =new Bundle();
        args.putSerializable("time", timer);
        //textView.setText(String.format("%s",time.format(timer)));
        return rootView;
    }

}