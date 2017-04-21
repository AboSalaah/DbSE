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
    private String mType;
    public ListItem(String name, String  logo, int id,String type)
    {
        mItemName=name; mItemLogo=logo; mItemId=id; mHasImage=true; mType=type;
    }
    public ListItem(String name,int id,String type)
    {
        mItemName=name; mItemId=id; mHasImage=false; mType=type;
    }
    public String getmItemName(){return mItemName;}
    public String getmItemLogo(){return mItemLogo;}
    public int getmItemId(){return mItemId;}
    public boolean getmHasImage(){return mHasImage;}
    public String getmType(){return mType;}

}
