package com.example.calculator;

import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {

    PanelFrg fragPnl = new PanelFrg();
    ContentFrg fragCnt = new ContentFrg();

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
