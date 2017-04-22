package com.example.ahmedsaleh.dbse;

/**
 * Created by Ahmed Saleh on 4/20/2017.
 * Custom Class that represent data for every faculty item that Faculties_Custom_Adapter holds
 */
public class List_view_faculty_item
{
    private String mNmae;
    private String mId;
    private String mType;

    /**
     * Initialize faculty data
     * @param name represent faculty Name
     * @param id   represent faculty Id
     * @param type represent faculty Type i.e faculty in university or in academy
     */
    public List_view_faculty_item(String name,String id,String type)
    {
        mNmae=name; mId=id; mType=type;
    }

    /**
     * Fuctnion returns faculty Name
     * @return String
     */
    public String getmName(){return mNmae;}

    /**
     * Function returns faculty Id
     * @return String
     */
    public String getmId(){return mId;}

    /**
     * Function returns faculty Type
     * @return String
     */
    public String getmType(){return mType;}
}


