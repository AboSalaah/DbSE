package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ahmed Saleh on 4/19/2017.
 */
public class QueryUtils {

   public static Bitmap converttobitmap(String logo)
    {
        byte[] decodedString = Base64.decode(logo, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public static int getDppixels(Context context,int size)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (size*scale + 0.5f);
        return dpAsPixels;
    }
    public static String parser(String s)
    {
        Log.i("tag","el string hwa "+s);
        String parts[]=s.split("/");
        Log.i("tag","awl mkaan "+parts[0]);
        StringBuilder parse=new StringBuilder();
        parse.setLength(0);
        for(int i=0;i<parts.length;++i)
        {
            parse.append(parts[i]);
            parse.append("\n");
        }
        Log.i("tag","resultttt "+parse.toString()+"\n");
        return parse.toString();
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, null);
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
