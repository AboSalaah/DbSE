package com.example.ahmedsaleh.dbse;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     private TabLayout tabs;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (TabLayout) findViewById(R.id.tabLayout);
        // Create an adapter that knows which fragment should be shown on each page

        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
      // setdatafornaivgationheader(); should be called when user sign in to update the circle in the navigation drawer


















    }

    /*void setdatafornaivgationheader()
    {
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigationview);
       View view= navigationView.getHeaderView(0);
         RelativeLayout relativeLayout=(RelativeLayout)view;
        TextView textView=(TextView)relativeLayout.findViewById(R.id.textincircle);
        if(textView!=null)
        {
        GradientDrawable backgroundGradient = (GradientDrawable)textView.getBackground();
        backgroundGradient.setColor(Color.BLUE);}
    }
    */


}
