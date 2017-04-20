package com.example.ahmedsaleh.dbse;

import android.graphics.Bitmap;


/**
 * Created by Ahmed Saleh on 4/18/2017.
 */
public class ListItem {

    private String mItemName;
    private Bitmap mItemLogo;
    private int mItemId;
    public ListItem(String name,Bitmap logo,int id)
    {
        mItemName=name; mItemLogo=logo; mItemId=id;
    }
    public String getmItemName(){return mItemName;}
    public Bitmap getmItemLogo(){return mItemLogo;}
    public int getmItemId(){return mItemId;}

}
