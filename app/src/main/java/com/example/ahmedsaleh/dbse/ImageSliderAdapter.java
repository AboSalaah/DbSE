package com.example.ahmedsaleh.dbse;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Saleh on 4/21/2017.
 * Custom Adapter Class that extend from PagerAdapter Class to hold Images Urls needed to be shown by ViewPager on University,Institute,Academy or Faculty Profile
 * @extend PagerAdapter
 */
public class ImageSliderAdapter extends PagerAdapter {
    private Context ctx;
    private ArrayList<String>sliderimagesUrls;

    /**
     * Initialize the Adapter with the ImagesUrls
     * @param ctx Application Context
     * @param imagesurls ArrayList that represent ImagesUrls
     */
    public ImageSliderAdapter(Context ctx,ArrayList<String>imagesurls)
    {
        this.ctx=ctx;
        sliderimagesUrls=imagesurls;
    }
    @Override
    public int getCount() {
        return sliderimagesUrls.size();
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
        Picasso.with(ctx).load(sliderimagesUrls.get(position)).into(mImageView);
      //  mImageView.setImageResource(sliderimagesId[position]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    /**
     * Function to set ImagesUrls if the images on the UI have been changed
     * @param imagesurls ArrayList that represent ImagesUrls for the Adapter
     * @return void
     */
    public void setImages(ArrayList<String> imagesurls)
    {
        sliderimagesUrls=imagesurls;
    }
}
