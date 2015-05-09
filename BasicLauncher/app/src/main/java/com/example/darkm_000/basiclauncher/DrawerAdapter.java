package com.example.darkm_000.basiclauncher;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by darkm_000 on 09/05/2015.
 */
public class DrawerAdapter extends BaseAdapter {

    Context context;
    MainActivity.Pack packs[];

    public DrawerAdapter(Context context, MainActivity.Pack[] packs) {
        this.context = context;
        this.packs = packs;
    }

    @Override
    public int getCount() {
        return packs.length;
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
        ImageView imageView=new ImageView(context);
        imageView.setImageDrawable(packs[position].icon);
        imageView.setLayoutParams(new GridView.LayoutParams(65, 65));
        imageView.setPadding(3,3,3,3);
        return imageView;
    }
}
