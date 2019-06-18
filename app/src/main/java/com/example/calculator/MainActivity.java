package com.example.calculator;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PanelFrg fragPnl = new PanelFrg();
    ContentFrg fragCnt = new ContentFrg();
    FragmentTransaction fragTrans;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle btnBundle = new Bundle();
        btnBundle.putInt("size", width);
        fragCnt.setArguments(btnBundle);

        Bundle bundle = new Bundle();
        bundle.putLong("time", System.currentTimeMillis());
        fragPnl.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.PanelFragment, fragPnl).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.ContentFragment, fragCnt).commit();
    }
}
