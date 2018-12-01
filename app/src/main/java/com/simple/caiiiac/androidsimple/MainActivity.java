package com.simple.caiiiac.androidsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView listView =  (ExpandableListView) findViewById(R.id.listView);

        ExpandableListView.OnGroupExpandListener onGroupExpandListener = new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, groupPosition + "组", Toast.LENGTH_SHORT).show();
            }
        };
        listView.setOnGroupExpandListener(onGroupExpandListener);

        // 组数据
        ArrayList<String> groups = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            groups.add("第" + i + "组");
        }

        // 内容数据
        HashMap<String, ArrayList<String>> contents = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < groups.size(); i++) {
            ArrayList<String> content = new ArrayList<String>();
            for (int j = 0; j < i + 1; j++) {
                content.add("内容"+ j);
            }
            contents.put(groups.get(i), content);
        }

        adapter = new MyAdapter(this);
        adapter.setDatas(groups, contents);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}
