package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahmed Saleh on 4/17/2017.
 */
public class Exp_list_Adapter extends BaseExpandableListAdapter{
    private List<ListItem> header_titles;
    private HashMap<ListItem,ArrayList<ListItem>> child_titles;
    private Context ctx;
    public Exp_list_Adapter(Context ctx, List<ListItem>header_titles, HashMap<ListItem, ArrayList<ListItem>> child_titles)
    {
        this.ctx=ctx; this.header_titles=header_titles; this.child_titles=child_titles;
    }
    @Override
    public int getGroupCount() {
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (child_titles.get(header_titles.get(groupPosition))).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

            return header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles.get((header_titles.get(groupPosition))).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ListItem header_title=(ListItem)getGroup(groupPosition);
        String name=header_title.getmItemName();
        Bitmap logo=header_title.getmItemLogo();
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.parent_exp_list_item,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.parent_exp_list_item_text_view);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(name);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.parent_exp_list_item_image_view);
        imageView.setImageBitmap(logo);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ctx,details.class);
                intent.putExtra("Uni_name", header_title);
                ctx.startActivity(intent);
                */
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ListItem child_title=(ListItem) child_titles.get(header_titles.get(groupPosition)).get(childPosition);
        String title=child_title.getmItemName();
        Bitmap logo=child_title.getmItemLogo();
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.child_exp_list_item,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.child_exp_list_item_text_view);
        textView.setText(title);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.child_exp_list_item_image_view);
        imageView.setImageBitmap(logo);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(ctx,details.class);
                intent.putExtra("Uni_name", child_title);
                ctx.startActivity(intent);
                */
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    void setAdapter(Context ctx, List<ListItem>header_titles, HashMap<ListItem, ArrayList<ListItem>> child_titles)
    {
        this.ctx=ctx; this.header_titles=header_titles; this.child_titles=child_titles;
        notifyDataSetChanged();

    }
}
