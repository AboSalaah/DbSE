package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Ahmed Saleh on 4/20/2017.
 */
public class Faculties_Custom_Adapter extends ArrayAdapter<List_view_faculty_item> {
    private Context context;
    public Faculties_Custom_Adapter(Context context, ArrayList<List_view_faculty_item> w)
    {
        super(context,0,w);
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List_view_faculty_item w=getItem(position);
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.faculties_list_view_item,parent,false);
        }
        TextView textView=(TextView)listItemView.findViewById(R.id.faculty_list_view_text_view);
        textView.setText(w.getmName());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                context.getResources().getDimension(R.dimen.itemattributetextsize));
        textView.setTextColor(Color.parseColor("#000000"));
        return listItemView;
    }
}
