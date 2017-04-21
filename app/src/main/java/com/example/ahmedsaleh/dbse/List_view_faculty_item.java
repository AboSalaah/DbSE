package com.example.ahmedsaleh.dbse;

/**
 * Created by Ahmed Saleh on 4/20/2017.
 */
public class List_view_faculty_item
{
    private String mNmae;
    private String mId;
    private String mType;
    public List_view_faculty_item(String name,String id,String type)
    {
        mNmae=name; mId=id; mType=type;
    }
    public String getmName(){return mNmae;}
    public String getmId(){return mId;}
    public String getmType(){return mType;}
}


