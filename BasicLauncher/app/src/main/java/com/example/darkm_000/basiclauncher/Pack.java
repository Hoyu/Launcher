package com.example.darkm_000.basiclauncher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by darkm_000 on 14/05/2015.
 */
public class Pack implements Serializable{

    //To serialize we can't use the Drawable. We create coordinates x, y and a String with the icon path directory
    transient Drawable icon;
    String name;
    String packageName;
    String label;
    int x, y;
    String iconPath;

    public String getPackageName() {
        return packageName;
    }
    public String getName() {
            return name;
        }

    //We use this methods to save the location of the icon in the app

    public void cache(){
        if (iconPath==null){
            //Create directory for saving cached icons of the apps
            new File(MainActivity.activity.getApplicationInfo().dataDir+"/CachedApps/").mkdirs();
        }
        if (icon != null){
            iconPath=MainActivity.activity.getApplicationInfo().dataDir+"/CachedApps/"+packageName+name;
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(iconPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (fileOutputStream!=null){
                //We compress the icon image (quality 0-100 but for png it's ignored)
                drawableToBitmap(icon).compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else
                iconPath=null; //Prevents repetition of failure
        }
    }
    public Bitmap getCached(){

        //We get a bitmap of the icon stored
        //We use BitmapFactory to get the bitmap from the file cached
        //BitmapFactory.Options will help us configure the decoded bitmap
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=false; //If set to true, the decoder will return null (no bitmap), but the out...
        options.inPreferredConfig= Bitmap.Config.ARGB_8888; //If this is non-null, the decoder will try to decode into this internal configuration.
        options.inDither=true; //If dither is true, the decoder will attempt to dither the decoded image.

        if (iconPath!=null){
            File iconCached=new File(iconPath);
            if (iconCached.exists())
                //If we really have an icon and a file: we do decodeFile with our options
                return BitmapFactory.decodeFile(iconCached.getAbsolutePath(), options);
        }else
            return null;

    }

    public static Bitmap drawableToBitmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable)
            return ((BitmapDrawable) drawable).getBitmap();
        //If it isn't drawable we have to create the bitmap (Config.ARGB_8888)
        Bitmap bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        //1.We use Canvas to draw the bitmap
        Canvas canvas=new Canvas(bitmap);
        //2.Set the bounds
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        //3.And simply draw it
        drawable.draw(canvas);

        return bitmap;
    }

}
