package com.turner.bookmark;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;

public class BookListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 0;
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
//        if (convertView == null) {
//            convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
//        }
//
//        ((TextView) convertView.findViewById(android.R.id.text1))
//                .setText(getItem(position));
//        return convertView;
        return null;
    }
}
