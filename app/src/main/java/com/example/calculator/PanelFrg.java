package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class PanelFrg extends Fragment {

    long timer;

    @SuppressLint("SimpleDateFormat")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pnl_fragment, container, false);
        TextView tvTime = (TextView) rootView.findViewById(R.id.tvTime);
        SimpleDateFormat time = null;
        time = new SimpleDateFormat();
        assert getArguments() != null;
        timer = getArguments().getLong("time");
        tvTime.setText(String.format("%s","Время открытия приложения: " + time.format(timer)));
        Button button = (Button) rootView.findViewById(R.id.btnClose);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.exit(0);
            }
        });
        return rootView;
    }
}