package com.example.calculator;

import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements PanelFragment.FragmentCallToActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PanelFragment fragPnl = new PanelFragment();
        ContentFragment fragCnt = new ContentFragment();
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

        getSupportFragmentManager().beginTransaction().replace(R.id.panelFragment, fragPnl).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragCnt).commit();
    }

    @Override
    public void onCloseClick(int a) {
        if(a == 1){
            finish();
        }
    }
}
