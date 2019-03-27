package com.simple.caiiiac.androidsimple;

import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        if (savedInstanceState == null) {
            Fragment fragment = new SettingFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fm, fragment)
                    .commit();
        }

    }
}
