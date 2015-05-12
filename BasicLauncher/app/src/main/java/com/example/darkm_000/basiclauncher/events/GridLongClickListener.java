package com.example.darkm_000.basiclauncher.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.example.darkm_000.basiclauncher.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by darkm_000 on 11/05/2015.
 */
public class GridLongClickListener implements AdapterView.OnItemLongClickListener {

    Context context;
    SlidingDrawer slidingDrawer;
    RelativeLayout home;

    public GridLongClickListener(Context context, SlidingDrawer slidingDrawer, RelativeLayout home) {
        this.context = context;
        this.slidingDrawer = slidingDrawer;
        this.home = home;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        //the coordinates are float
        layoutParams.leftMargin=(int)view.getX();
        layoutParams.topMargin=(int)view.getY();

        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        //With the inflater we get the linear layout
        LinearLayout linearLayout=(LinearLayout) inflater.inflate(R.layout.drawer_item, null);
        //We insert the view we recieve with the event click to the new layout
        ((ImageView)linearLayout.findViewById(R.id.icon_image)).setImageDrawable(((ImageView)view.findViewById(R.id.icon_image)).getDrawable());
        ((TextView)linearLayout.findViewById(R.id.icon_text)).setText(((TextView) view.findViewById(R.id.icon_text)).getText());
        //We put it into our home view in the same coordinates with the layourParams
        home.addView(linearLayout, layoutParams);
        //When we do this, we want our slide to close automatically
        slidingDrawer.animateClose();
        //Now we have to ensure that the new icon doesn't appear over the slidingDrawer
        slidingDrawer.bringToFront();
        //return true should stop onitemclicklistener
        return true;
    }
}
