package com.example.ahmedsaleh.dbse.Helpers;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


/**
 * Created by Ahmed Saleh on 4/18/2017.
 * Custom Class that represent University,Institute,Academy or Faculty data in Exp_list_Adapter
 */
public class ListItem {

    private String mItemName;
    private String mItemLogourl=null;
    private int mItemId;
    boolean mHasImage;
    private String mType;

    /**
     * Initialize item data
     * @param name represent item Name
     * @param logourl represent item Logourl
     * @param id   represent item Id
     * @param type represent item Type i.e University or Faculty etc.....
     */
    public ListItem(String name, String  logourl, int id,String type)
    {
        mItemName=name; mItemLogourl=logourl; mItemId=id; mHasImage=true; mType=type;
    }

    /**
     * second constructor for items that hasn't logo
     * @param name represent item Name
     * @param id represent item Id
     * @param type represent item Type
     */
    public ListItem(String name,int id,String type)
    {
        mItemName=name; mItemId=id; mHasImage=false; mType=type;
    }

    /**
     * Function returns item Name
     * @return String
     */
    public String getmItemName(){return mItemName;}

    /**
     * Function returns item Logourl
     * @return String
     */
    public String getmItemLogo(){return mItemLogourl;}

    /**
     * Function returns item Id
     * @return
     */
    public int getmItemId(){return mItemId;}

    /**
     * Function returns that if the item has logo or not
     * @return boolean
     */
    public boolean getmHasImage(){return mHasImage;}

    /**
     * Function returns item Type
     * @return String
     */
    public String getmType(){return mType;}


}
