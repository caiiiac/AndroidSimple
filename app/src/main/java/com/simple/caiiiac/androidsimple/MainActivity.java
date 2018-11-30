package com.simple.caiiiac.androidsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sex_rg);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_btn_boy) {
                    Toast.makeText(MainActivity.this,"男",Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radio_btn_gril) {
                    Toast.makeText(MainActivity.this,"女",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
