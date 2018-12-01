package com.simple.caiiiac.androidsimple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<String> mDatas;
    private LayoutInflater mInflater;

    public MyAdapter(Context c) {
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return mDatas!= null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.spinner_layout, null);
            textView = convertView.findViewById(R.id.textTitle);
            convertView.setTag(textView);
        } else {
            textView = (TextView) convertView.getTag();
        }
        textView.setText(mDatas.get(position));
        return convertView;
    }

    public void setDatas(ArrayList<String> datas) {
        mDatas = datas;
    }
}
