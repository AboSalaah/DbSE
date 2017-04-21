package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        String logo=header_title.getmItemLogo();
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.parent_exp_list_item,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.parent_exp_list_item_text_view);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(name);

        if(header_title.getmHasImage()) {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.parent_exp_list_item_image_view);
            Picasso.with(ctx).load(logo).into(imageView);
        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ctx.toString(),"ana d5ltt hnaaaaaaaa");
                Intent intent = new Intent(ctx,University_Profile.class);
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
       if(child_title.getmHasImage()) {
           ImageView imageView = (ImageView) convertView.findViewById(R.id.child_exp_list_item_image_view);
           Picasso.with(ctx).load(logo).into(imageView);
       }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,University_Profile.class);

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

    void setAdapter(Context ctx, List<ListItem>header_titles, HashMap<ListItem, ArrayList<ListItem>> child_titles)
    {
        this.ctx=ctx; this.header_titles=header_titles; this.child_titles=child_titles;
        notifyDataSetChanged();

    }
}
