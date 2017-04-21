package com.example.ahmedsaleh.dbse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class University_Profile extends AppCompatActivity {
    private StringBuilder parser=new StringBuilder();
    private String result;
    private String itemType;
    private StringBuilder url=new StringBuilder();
    private String itemId;
    private int nedeedpadding;
    private Faculties_Custom_Adapter adapter;
    private String tag="University_Profile";
    private ImageSliderAdapter adapterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university__profile);
        Intent intent=getIntent();
        itemType=intent.getStringExtra("type");
        itemId=intent.getStringExtra("id");
        url.append(intent.getStringExtra("url"));
        url.append(itemType+"/"+itemId+"?token="+getString(R.string.token));
        Log.i("University_Profile","el typeeeee "+itemType);
        Log.i("University_Profile","el id "+itemId);

        // this part will be deleted
        nedeedpadding=QueryUtils.getDppixels(this,5);
        int imagesid[]={R.drawable.pic4,R.drawable.pic5};
        ViewPager mViewPager = (ViewPager) findViewById(R.id.image_view_pager);
        adapterView = new ImageSliderAdapter(this,imagesid);
        mViewPager.setAdapter(adapterView);
        TextView textView=(TextView)findViewById(R.id.uni_profile_name_label);
        textView.setText("Name");
       // textView.setPadding(0,nedeedpadding,0,nedeedpadding);
        //textView.setBackgroundResource(R.drawable.labelrectangularshape);
        TextView textView7=(TextView)findViewById(R.id.uni_profile_name);
        textView7.setText("Cairo University");
        //textView7.setPadding(0,nedeedpadding,0,nedeedpadding);
        //textView7.setBackgroundResource(R.drawable.rectangularshape);
        ImageView imageView=(ImageView)findViewById(R.id.uni_profile_logo);
        imageView.setImageResource(R.drawable.ic_menu_camera);
        TextView textView711=(TextView)findViewById(R.id.uni_profile_discreption_label);
        textView711.setText("Description");
       // textView711.setPadding(0,nedeedpadding,0,nedeedpadding);
      //  textView711.setBackgroundResource(R.drawable.labelrectangularshape);
        TextView textView71=(TextView)findViewById(R.id.uni_profile_discreption);
        textView71.setText("gam3a m3fna fel giza");
       // textView71.setPadding(0,nedeedpadding,0,nedeedpadding);
        //textView71.setBackgroundResource(R.drawable.rectangularshape);
        TextView textView88=(TextView)findViewById(R.id.uni_profile_location_label);
        textView88.setText("Location");
        //textView88.setPadding(0,nedeedpadding,0,nedeedpadding);
        //textView88.setBackgroundResource(R.drawable.labelrectangularshape);
        TextView textView89=(TextView)findViewById(R.id.uni_profile_location);
        textView89.setText("Giza");
        //textView89.setPadding(0,nedeedpadding,0,nedeedpadding);
       // textView89.setBackgroundResource(R.drawable.rectangularshape);
        TextView textView1=(TextView)findViewById(R.id.uni_profile_image_label);
        textView1.setText("Photos");
        TextView textView2=(TextView)findViewById(R.id.uni_profile_city_label);
        textView2.setText("City");
        TextView textView3=(TextView)findViewById(R.id.uni_profile_city);
        textView3.setText("Giza");
        TextView textView4=(TextView)findViewById(R.id.uni_profile_contacts_label);
        textView4.setText("Contacts");
        TextView textView5=(TextView)findViewById(R.id.uni_profile_contacts);
        textView5.setText("13421525");
        TextView textView6=(TextView)findViewById(R.id.uni_profile_website_label);
        textView6.setText("Website");
        TextView textview122=(TextView)findViewById(R.id.uni_profile_website);
        textview122.setText("cufe.com");
        TextView textView8=(TextView)findViewById(R.id.uni_profile_facebookpage);
        textView8.setText("facebook.com/cufe");
        TextView textView9=(TextView)findViewById(R.id.uni_profile_facebookpage_label);
        textView9.setText("FacebookPage");
        TextView textView10=(TextView)findViewById(R.id.uni_profile_faculties_list_view_label);
        textView10.setText("Faculties");


























        //////////////////////////////////////////////////////////////////////////
        nedeedpadding=QueryUtils.getDppixels(getApplicationContext(),10);
        ArrayList<List_view_faculty_item>arr=new ArrayList<>();
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("doc",2));
        arr.add(new List_view_faculty_item("e3l",3));
        arr.add(new List_view_faculty_item("ada",4));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));
        arr.add(new List_view_faculty_item("eng",1));

        adapter=new Faculties_Custom_Adapter(getApplicationContext(),arr);
        ListView listView=(ListView)findViewById(R.id.uni_profile_faculties_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List_view_faculty_item list_view_faculty_item=(List_view_faculty_item)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), list_view_faculty_item.getmName(),
                        Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        Log.i("University_Profile","aheeeeeeh  "+url.toString());
       // connect();

    }


    void connect()
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url.toString())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException
            {
                result=response.body().string().toString();
                Log.i("University_Profile","result  "+result);
                  runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {


                        try {
                            JSONArray jsonArray=new JSONArray(result);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            JSONObject jsonObject1=jsonObject.getJSONObject(itemType);


                            TextView textView50=(TextView)findViewById(R.id.uni_profile_name_label);
                            textView50.setText("Name");
                            textView50.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String name=jsonObject1.getString("name");
                            TextView textView=(TextView)findViewById(R.id.uni_profile_name);
                            textView.setText(name);
                            textView.setPadding(0,nedeedpadding,0,nedeedpadding);




                            ImageView imageView2=(ImageView)findViewById(R.id.uni_profile_logo);
                            String logourl=jsonObject1.getString("logo");
                            if(!logourl.equals("null"))
                            {
                               // TextView textView51=(TextView)findViewById(R.id.uni_profile_logo_label);
                               // textView51.setText("Logo");
                                //textView51.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                      // getResources().getDimension(R.dimen.labelstextsizeforprofile));
                              //  Picasso.with(getApplicationContext()).load(logourl).into(imageView2);
                            }


                           /* TextView textView52=(TextView)findViewById(R.id.uni_profile_image_label);
                            textView52.setText("Image");
                            textView52.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String imageurl=jsonObject1.getString("pic1");
                            Log.i(tag,"el sora "+imageurl);
                            ImageView imageView=(ImageView)findViewById(R.id.uni_profile_imaage);
                            Picasso.with(getApplicationContext()).load(imageurl).into(imageView);
                            */



                            TextView textView53=(TextView)findViewById(R.id.uni_profile_discreption_label);
                            textView53.setText("Discreption");
                            textView53.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String descreption=jsonObject1.getString("description");
                            TextView textView1=(TextView)findViewById(R.id.uni_profile_discreption);
                            textView1.setText(descreption);
                            textView1.setPadding(0,nedeedpadding,0,nedeedpadding);



                            TextView textView54=(TextView)findViewById(R.id.uni_profile_city_label);
                            textView54.setText("City");
                            textView54.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String city=jsonObject1.getString("city");
                            TextView textView2=(TextView)findViewById(R.id.uni_profile_city);
                            textView2.setText(city);
                            textView2.setPadding(0,nedeedpadding,0,nedeedpadding);



                            TextView textView55=(TextView)findViewById(R.id.uni_profile_contacts_label);
                            textView55.setText("Contacts");
                            textView55.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String contacts=jsonObject1.getString("contacts");
                            TextView textView3=(TextView)findViewById(R.id.uni_profile_contacts);
                            textView3.setText(QueryUtils.parser(contacts));
                            textView3.setPadding(0,nedeedpadding,0,nedeedpadding);


                            TextView textView56=(TextView)findViewById(R.id.uni_profile_website_label);
                            textView56.setText("Website");
                            textView56.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String websiteurl=jsonObject1.getString("website_url");
                            TextView textView4=(TextView)findViewById(R.id.uni_profile_website);
                            textView4.setText(websiteurl);
                            textView4.setPadding(0,nedeedpadding,0,nedeedpadding);


                            TextView textView57=(TextView)findViewById(R.id.uni_profile_facebookpage_label);
                            textView57.setText("Facebook");
                            textView57.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String facebookpage=jsonObject1.getString("facebook_page");
                            TextView textView5=(TextView)findViewById(R.id.uni_profile_facebookpage);
                            textView5.setText(facebookpage);
                            textView5.setPadding(0,nedeedpadding,0,nedeedpadding);



                            TextView textView58=(TextView)findViewById(R.id.uni_profile_location_label);
                            textView58.setText("Location");
                            textView58.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                    getResources().getDimension(R.dimen.labelstextsizeforprofile));
                            String location=jsonObject1.getString("location");
                            TextView textView6=(TextView)findViewById(R.id.uni_profile_location);
                            textView6.setText(location);
                            textView6.setPadding(0,nedeedpadding,0,nedeedpadding);


                            if(itemType.equals("university"))
                            {

                                TextView textView59=(TextView)findViewById(R.id.uni_profile_president_label);
                                textView59.setText("President");
                                textView59.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                        getResources().getDimension(R.dimen.labelstextsizeforprofile));

                                String president=jsonObject1.getString("president_name");
                                Log.i(tag,"president "+president);
                                TextView textView7=(TextView)findViewById(R.id.uni_profile_presedent);
                                textView7.setText(president);
                                textView7.setPadding(0,nedeedpadding,0,nedeedpadding);



                                TextView textView60=(TextView)findViewById(R.id.uni_profile_tsnef_label);
                                textView60.setText("Rank");
                                textView60.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                        getResources().getDimension(R.dimen.labelstextsizeforprofile));
                                String rank=jsonObject1.getString("rank");
                                TextView textView8=(TextView)findViewById(R.id.uni_profile_tsnef);
                                textView8.setText(QueryUtils.parser(rank));
                                textView8.setPadding(0,nedeedpadding,0,nedeedpadding);

                            }
                            if(itemType.equals("university")||itemType.equals("faculty"))
                            {
                                Log.i(tag,"da5ll fel past_presidents");
                                TextView textView61=(TextView)findViewById(R.id.uni_profile_past_presedent_label);
                                textView61.setText("Past_President");
                                textView61.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                        getResources().getDimension(R.dimen.labelstextsizeforprofile));
                                String past_presidents=jsonObject1.getString("past_presidents");
                                 Log.i(tag,"el past president homa "+past_presidents);
                                TextView textView9=(TextView)findViewById(R.id.uni_profile_past_presedent);
                                textView9.setText(QueryUtils.parser(past_presidents));
                                textView9.setPadding(0,nedeedpadding,0,nedeedpadding);
                            }
                            if(itemType.equals("faculty")||itemType.equals("institute")) {
                                String fees = jsonObject1.getString("fees");
                                if (!fees.equals("null"))
                                {
                                    TextView textView62=(TextView)findViewById(R.id.uni_profile_fees_label);
                                    textView62.setText("Fees");
                                    textView62.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                            getResources().getDimension(R.dimen.labelstextsizeforprofile));


                                    TextView textView10=(TextView)findViewById(R.id.faculty_profile_fees);
                                    textView10.setText(fees);
                                    textView10.setPadding(0,nedeedpadding,0,nedeedpadding);
                                }


                                TextView textView63=(TextView)findViewById(R.id.uni_profile_department_label);
                                textView63.setText("Departments");
                                textView63.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                        getResources().getDimension(R.dimen.labelstextsizeforprofile));
                                String departments=jsonObject1.getString("departments");
                                TextView textView12=(TextView)findViewById(R.id.faculty_profile_department);
                                textView12.setText(QueryUtils.parser(departments));
                                textView12.setPadding(0,nedeedpadding,0,nedeedpadding);

                            }
                            if(!itemType.equals("academy"))
                            {
                                String others=jsonObject1.getString("others");
                                if(!others.equals("null"))
                                {
                                    TextView textView64=(TextView)findViewById(R.id.uni_profile_others_label);
                                    textView64.setText("Others");
                                    textView64.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                            getResources().getDimension(R.dimen.labelstextsizeforprofile));
                                    TextView textView11=(TextView)findViewById(R.id.uni_profile_others);
                                    textView11.setText(QueryUtils.parser(others));
                                    textView11.setPadding(0,nedeedpadding,0,nedeedpadding);

                                }

                            }
                            if(itemType.equals("university")||itemType.equals("academy"))
                            {
                                TextView textView65=(TextView)findViewById(R.id.uni_profile_faculties_list_view_label);
                                textView65.setText("Faculties");
                                textView65.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                        getResources().getDimension(R.dimen.labelstextsizeforprofile));
                                ArrayList<List_view_faculty_item>arr=new ArrayList<>();
                                JSONArray jsonArray1=jsonObject.getJSONArray("faculties");
                                for(int i=0;i<jsonArray1.length();++i)
                                {
                                    JSONObject jsonObject2=jsonArray1.getJSONObject(i);
                                    String namee=jsonObject2.getString("name");
                                    int id=jsonObject2.getInt("id");
                                    arr.add(new List_view_faculty_item(namee,id));
                                }
                                Log.i(tag,"7gm el arraay "+arr.size());
                                adapter.clear();
                                adapter.addAll(arr);
                                adapter.notifyDataSetChanged();

                            }





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });

    }
}
