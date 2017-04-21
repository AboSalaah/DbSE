package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Ahmed Saleh on 4/21/2017.
 */
public class ImageSliderAdapter extends PagerAdapter {
    private Context ctx;
    private int[]sliderimagesId;
    public ImageSliderAdapter(Context ctx,int []imagesid)
    {
        this.ctx=ctx;
        sliderimagesId=imagesid;
    }
    @Override
    public int getCount() {
        return sliderimagesId.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView mImageView = new ImageView(ctx);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderimagesId[position]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    public void setImages(int images[])
    {
        sliderimagesId=images;
    }
}
