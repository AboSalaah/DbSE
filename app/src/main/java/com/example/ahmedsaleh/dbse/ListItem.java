package com.example.ahmedsaleh.dbse;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


/**
 * Created by Ahmed Saleh on 4/18/2017.
 */
public class ListItem {

    private String mItemName;
    private String mItemLogo;
    private int mItemId;
    boolean mHasImage;
    public ListItem(String name, String  logo, int id)
    {
        mItemName=name; mItemLogo=logo; mItemId=id; mHasImage=true;
    }
    public ListItem(String name,int id)
    {
        mItemName=name; mItemId=id; mHasImage=false;
    }
    public String getmItemName(){return mItemName;}
    public String getmItemLogo(){return mItemLogo;}
    public int getmItemId(){return mItemId;}
    public boolean getmHasImage(){return mHasImage;}

}
