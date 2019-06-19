package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class PanelFragment extends Fragment implements View.OnClickListener{

    FragmentCallToActivity closing;
    private long timer;

    public interface FragmentCallToActivity{

        void onCloseClick(int a);
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentCallToActivity){
            closing = (FragmentCallToActivity) context;
        }
    }

    @SuppressLint("SimpleDateFormat")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_panel, container, false);
        TextView tvTime = rootView.findViewById(R.id.tvTime);
        SimpleDateFormat time = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss");
        if(getArguments() != null){
            timer = getArguments().getLong("time");
        }
        tvTime.setText(String.format("%s %s", tvTime.getText().toString(), time.format(timer)));
        rootView.findViewById(R.id.btnClose).setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnClose) {
            closing.onCloseClick(1);
        }
    }
}