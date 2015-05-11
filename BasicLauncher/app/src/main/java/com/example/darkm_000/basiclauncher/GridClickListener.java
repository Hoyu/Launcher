package com.example.darkm_000.basiclauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by darkm_000 on 11/05/2015.
 */
public class GridClickListener implements AdapterView.OnItemClickListener {

    Context context;
    PackageManager packageManager;
    MainActivity.Pack[] packs;

    public GridClickListener(Context context, PackageManager packageManager, MainActivity.Pack[] packs) {
        this.context = context;
        this.packageManager = packageManager;
        this.packs = packs;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent launchIntent=packageManager.getLaunchIntentForPackage(packs[position].name);
        context.startActivity(launchIntent);
    }
}
