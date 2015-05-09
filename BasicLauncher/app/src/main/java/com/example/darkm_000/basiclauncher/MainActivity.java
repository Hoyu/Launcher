package com.example.darkm_000.basiclauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    class Pack {
        Drawable icon;
        String name;
        String label;
    }

    GridView grid;
    DrawerAdapter drawerAdapter;

    Pack [] packs;
    PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        packageManager=getPackageManager();
        //We get the GridView "content" that we created in the xml
        grid=(GridView) findViewById(R.id.content);
        //For drawing the grid layout with the applications of the system
        //Get the applications info in our array
        setPacks();
        //initialize the DrawerAdapter with the info
        drawerAdapter= new DrawerAdapter(this,packs);
        //Then just let the adapter do his job on the GridView
        grid.setAdapter(drawerAdapter);

    }

    public void setPacks(){
        final Intent mainIntent=new Intent(Intent.ACTION_MAIN, null);
        //We want to get the apps that could be launch
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //We make a query with the PackageManager for activities launchables using our intent and no flags
        List<ResolveInfo> listPacks=packageManager.queryIntentActivities(mainIntent, 0);
        packs=new Pack[listPacks.size()];
        //We create the packs with the info needed: icons, names, labels
        for (int i=0;i<listPacks.size();i++){
            packs[i]=new Pack();
            packs[i].icon=listPacks.get(i).loadIcon(packageManager);
            packs[i].name=listPacks.get(i).resolvePackageName;
            packs[i].label=listPacks.get(i).loadLabel(packageManager).toString();
        }
    }


}
