package com.example.darkm_000.basiclauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

import com.example.darkm_000.basiclauncher.events.GridLongClickListener;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    class Pack {
        Drawable icon;
        String name;
        String label;
    }
    //Grid with all the apps
    GridView grid;
    //Main screen/ user desktop
    RelativeLayout home;
    //the slide screen with the grid
    SlidingDrawer drawer;
    //We need an adapter to draw our screens
    DrawerAdapter drawerAdapter;

    //With the packageManager we'll get the info of the system to our array of apps
    Pack [] packs;
    PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        packageManager=getPackageManager();
        //We get the GridView "content" that we created in the xml
        grid=(GridView) findViewById(R.id.content);
        home=(RelativeLayout) findViewById(R.id.home);
        drawer=(SlidingDrawer) findViewById(R.id.drawer);
        //For drawing the grid layout with the applications of the system
        //Get the applications info in our array
        setPacks();


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
        //We can reorder the apps if we want :D

        //initialize the DrawerAdapter with the info
        drawerAdapter= new DrawerAdapter(this,packs);
        //Then just let the adapter do his job on the GridView
        grid.setAdapter(drawerAdapter);
        //We need to make the icons launch the apps with an event. See GridClickListener
        grid.setOnItemClickListener(new GridClickListener(this,packageManager,packs));
        //Long click for putting the icons on home screen
        grid.setOnItemLongClickListener(new GridLongClickListener(this, drawer, home));
    }

    public class AppListener extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent arg1) {
            // TODO Auto-generated method stub
            setPacks();
        }
    }


}
