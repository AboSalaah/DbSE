package com.example.ahmedsaleh.dbse;

/**
 * Created by Ahmed Saleh on 4/20/2017.
 */
public class List_view_faculty_item
{
    private String mNmae;
    private int mId;
    public List_view_faculty_item(String name,int id)
    {
        mNmae=name; mId=id;
    }
    public String getmName(){return mNmae;}
    public int getmId(){return mId;}
}


