package com.example.ahmedsaleh.dbse.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmedsaleh.dbse.Activities.Item_Profile;
import com.example.ahmedsaleh.dbse.Helpers.ListItem;
import com.example.ahmedsaleh.dbse.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahmed Saleh on 4/17/2017.
 * Custom Adapter Class that extends from BaseExpandableListAdapter Class to hold the custom list items data for ExpandableListView to show
 * @extend BaseExpandableListAdapter
 */
public class Exp_list_Adapter extends BaseExpandableListAdapter{
    private List<ListItem> header_titles;
    private HashMap<ListItem,ArrayList<ListItem>> child_titles;
    private Context ctx;

    /**
     * Initialize the Parent and Child data to use it with the ExpandableListView
     * @param ctx Application Context
     * @param header_titles  List that represent Parent data
     * @param child_titles HashMap that represent each Parent with it's Childs
     */
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
        String logo=header_title.getmItemLogo();
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.parent_exp_list_item,null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.parent_exp_list_item_image_view);

        }
        TextView textView=(TextView)convertView.findViewById(R.id.parent_exp_list_item_text_view);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setText(name);
        ImageView imageView2=(ImageView)convertView.findViewById(R.id.parent_exp_list_item_image_view);
        if(header_title.getmHasImage())
        {
            Picasso.with(ctx).load(logo).into(imageView2);
        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ctx.toString(),"ana d5ltt hnaaaaaaaa");
                Intent intent = new Intent(ctx,Item_Profile.class);
                intent.putExtra("type",header_title.getmType());
                intent.putExtra("id",String.valueOf(header_title.getmItemId()));
                intent.putExtra("url",ctx.getString(R.string.url));
                ctx.startActivity(intent);

            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ListItem child_title=(ListItem) child_titles.get(header_titles.get(groupPosition)).get(childPosition);
        String title=child_title.getmItemName();
       String  logo=child_title.getmItemLogo();
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.child_exp_list_item,null);


        }
        TextView textView=(TextView)convertView.findViewById(R.id.child_exp_list_item_text_view);
        textView.setText(title);
        textView.setTextColor(Color.parseColor("#000000"));
        ImageView imageView=(ImageView)convertView.findViewById(R.id.child_exp_list_item_image_view);
        if(child_title.getmHasImage())
        {
            Picasso.with(ctx).load(logo).into(imageView);
        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,Item_Profile.class);

                intent.putExtra("type",child_title.getmType());

                intent.putExtra("id",String.valueOf(child_title.getmItemId()));
                intent.putExtra("url",ctx.getString(R.string.url));
                ctx.startActivity(intent);

            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * Function to set Adapter data if the data to be shown in the UI changed
     * @param ctx Application Context
     * @param header_titles List that represent Parent data
     * @param child_titles HashMap that represent each Parent with it's Child's
     * @return void
     */

    public void setAdapter(Context ctx, List<ListItem> header_titles, HashMap<ListItem, ArrayList<ListItem>> child_titles)
    {
        this.ctx=ctx; this.header_titles=header_titles; this.child_titles=child_titles;
        notifyDataSetChanged();

    }
}
