package com.example.darkm_000.basiclauncher.events;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.darkm_000.basiclauncher.MainActivity;

/**
 * Created by darkm_000 on 13/05/2015.
 */
public class AppClickListener implements View.OnClickListener {

    Context context;
    MainActivity.Pack[] packs;

    public AppClickListener(Context context, MainActivity.Pack[] packs) {
        this.context = context;
        this.packs = packs;
    }

    @Override
    public void onClick(View v) {
        //We use .Tag() to obtain the info for the position in the array
        String[] data= (String[]) v.getTag();

        Intent launchIntent=new Intent(Intent.ACTION_MAIN);
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName componentName=new ComponentName(data[0], data[1]);
        launchIntent.setComponent(componentName);
        context.startActivity(launchIntent);

    }
}
