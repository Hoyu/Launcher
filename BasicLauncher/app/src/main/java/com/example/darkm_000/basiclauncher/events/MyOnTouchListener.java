package com.example.darkm_000.basiclauncher.events;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by darkm_000 on 13/05/2015.
 */
public class MyOnTouchListener implements View.OnTouchListener {

    //Width and Height are equal
    int iconWidth;

    public MyOnTouchListener(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                //Layout is the square icon: iconWidth x iconWidth
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(iconWidth, iconWidth);
                //Here we get the event RAWx and y and adjust the result to put the icon in the center
                layoutParams.leftMargin=(int)event.getRawX() - iconWidth/2;
                layoutParams.topMargin=(int)event.getRawY() - iconWidth/2;
                //Set the view with the new layout parameters
                v.setLayoutParams(layoutParams);
        }
        //to continue handling the event after this event
        return false;
    }
}
