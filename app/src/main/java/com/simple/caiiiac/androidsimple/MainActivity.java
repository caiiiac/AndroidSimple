package com.simple.caiiiac.androidsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter(this);

        final Spinner spinner =  (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);


        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("caiiiac","" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(onItemSelectedListener);


        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add("第" + i + "行");
        }

        adapter.setDatas(datas);
        adapter.notifyDataSetChanged();
    }
}
