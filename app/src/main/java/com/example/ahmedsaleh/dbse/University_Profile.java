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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextClock;
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
    private double lat;//for last latitude for location on map
    private double lon; //for last longatiude for laction on map
    private String location; //for last location
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university__profile);
        final Intent intent=getIntent();
        itemType=intent.getStringExtra("type");
        itemId=intent.getStringExtra("id");
        url.setLength(0);
        url.append(getString(R.string.url));
        url.append(itemType+"/"+itemId+"?token="+getString(R.string.token));
        Log.i("University_Profile","el typeeeee "+itemType);
        Log.i("University_Profile","el id "+itemId);

        // this part will be deleted
        nedeedpadding=QueryUtils.getDppixels(this,5);
      /*  int imagesid[]={R.drawable.pic4,R.drawable.pic5};
        ViewPager mViewPager = (ViewPager) findViewById(R.id.image_view_pager);
        adapterView = new ImageSliderAdapter(this,imagesid);

        ViewPager
        mViewPager.setAdapter(new ImageSliderAdapter(this,new ArrayList<String>()));
        */
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

        TextView textView11=(TextView)findViewById(R.id.uni_profile_location_on_map_label);
        textView11.setText("Location On Map");
        TextView textView12=(TextView)findViewById(R.id.uni_profile_location_on_map);
        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryUtils.showLocationOnMap(getApplicationContext(),lat,lon,location);
            }
        });
























        //////////////////////////////////////////////////////////////////////////
        nedeedpadding=QueryUtils.getDppixels(getApplicationContext(),10);
        ArrayList<List_view_faculty_item>arr=new ArrayList<>();
        arr.add(new List_view_faculty_item("eng","1", "uni"));
        arr.add(new List_view_faculty_item("doc","2", "uni"));
        arr.add(new List_view_faculty_item("e3l","3", "uni"));
        arr.add(new List_view_faculty_item("ada","4", "uni"));
        arr.add(new List_view_faculty_item("eng","1", "uni"));
        arr.add(new List_view_faculty_item("eng","1", "uni"));
        arr.add(new List_view_faculty_item("eng","1", "uni"));
        arr.add(new List_view_faculty_item("eng","1", "uni"));
        arr.add(new List_view_faculty_item("eng","1", "uni"));

        adapter=new Faculties_Custom_Adapter(getApplicationContext(),arr);
        listView=(ListView)findViewById(R.id.uni_profile_faculties_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List_view_faculty_item list_view_faculty_item=(List_view_faculty_item)parent.getItemAtPosition(position);
                Intent intent1=new Intent(University_Profile.this,University_Profile.class);
                intent1.putExtra("type",list_view_faculty_item.getmType());
                intent1.putExtra("id",list_view_faculty_item.getmId());
                startActivity(intent1);

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
        connect();

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
                         Log.i("tag","result   "+result);

                        try {
                            JSONArray jsonArray=new JSONArray(result);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            JSONObject jsonObject1=jsonObject.getJSONObject(itemType);



                          /*  if(itemType.equals("university"))
                            {
                                TextView textView=(TextView)findViewById(R.id.faculty_profile_department_label);
                                textView.setVisibility(View.GONE);
                                TextView textView2=(TextView)findViewById(R.id.faculty_profile_department);
                                textView2.setVisibility(View.GONE);

                            }
                            else if(itemType.equals("faculty")||itemType.equals("institute")||itemType.equals("academy"))
                            {
                                TextView textView=(TextView)findViewById(R.id.uni_profile_tsnef_label);
                                textView.setVisibility(View.GONE);
                                TextView textView2=(TextView)findViewById(R.id.uni_profile_tsnef);
                                textView2.setVisibility(View.GONE);
                                TextView textView3=(TextView)findViewById(R.id.uni_profile_president_label);
                                textView3.setVisibility(View.GONE);
                                TextView textView4=(TextView)findViewById(R.id.uni_profile_presedent);
                                textView4.setVisibility(View.GONE);
                                if(itemType.equals("institute"))
                                {
                                    ListView listView=(ListView)findViewById(R.id.uni_profile_faculties_list_view);
                                    listView.setVisibility(View.GONE);
                                    TextView facultieslabel=(TextView)findViewById(R.id.uni_profile_faculties_list_view_label);
                                    facultieslabel.setVisibility(View.GONE);
                                }
                                if(itemType.equals("academy")||itemType.equals("university"))
                                {
                                    TextView feeslable=(TextView)findViewById(R.id.uni_profile_fees_label);
                                    feeslable.setVisibility(View.GONE);
                                    TextView fees=(TextView)findViewById(R.id.faculty_profile_fees);
                                    fees.setVisibility(View.GONE);
                                }
                                if(itemType.equals("institute")||itemType.equals("academy"))
                                {
                                    TextView pastpresidentlabel=(TextView)findViewById(R.id.uni_profile_past_presedent_label);
                                    pastpresidentlabel.setVisibility(View.GONE);
                                    TextView pastpresident=(TextView)findViewById(R.id.uni_profile_past_presedent);
                                    pastpresident.setVisibility(View.GONE);
                                }
                            }
*/


                            TextView itemnamelabel = (TextView) findViewById(R.id.uni_profile_name_label);
                            TextView itemname = (TextView) findViewById(R.id.uni_profile_name);
                            if(jsonObject1.has("name")) {
                                itemnamelabel.setText("Name");

                                String name = jsonObject1.getString("name");
                                itemname.setText(name);
                            }
                            else
                            {
                                itemnamelabel.setVisibility(View.GONE);
                                itemname.setVisibility(View.GONE);
                            }





                            ImageView imageView2=(ImageView)findViewById(R.id.uni_profile_logo);
                            if(jsonObject1.has("logo")) {
                                String logourl = jsonObject1.getString("logo");
                                if (!logourl.equals("null")) {

                                    Picasso.with(getApplicationContext()).load(logourl).into(imageView2);
                                } else {
                                    imageView2.setVisibility(View.GONE);
                                }
                            }
                            else
                            {
                                imageView2.setVisibility(View.GONE);
                            }
                            ViewPager viewPager=(ViewPager)findViewById(R.id.image_view_pager);
                            if(jsonObject1.has("pic1")&&!jsonObject1.getString("pic1").equals("null"))
                            {
                                ArrayList<String>images=new ArrayList<String>();
                                if(!jsonObject1.getString("pic1").equals("null"))
                                {String pic1=getString(R.string.imageurl)+jsonObject1.getString("pic1");
                                images.add(pic1);}
                                if(jsonObject1.has("pic2"))
                                {
                                    if(!jsonObject1.getString("pic2").equals("null"))
                                    {String pic2=getString(R.string.imageurl)+jsonObject1.getString("pic2");
                                    images.add(pic2);}
                                    if(jsonObject1.has("pic3"))
                                    {
                                        if(!jsonObject1.getString("pic3").equals("null"))
                                        {String pic3=getString(R.string.imageurl)+jsonObject1.getString("pic3");
                                        images.add(pic3);}
                                    }
                                    if(jsonObject1.has("pic4"))
                                    {
                                        if(!jsonObject1.getString("pic4").equals("null"))
                                        {String pic4=getString(R.string.imageurl)+jsonObject1.getString("pic4");
                                        images.add(pic4);}
                                        if(jsonObject1.has("pic5"))
                                        {
                                            if(!jsonObject1.getString("pic5").equals("null"))
                                            {String pic5=getString(R.string.imageurl)+jsonObject1.getString("pic5");
                                            images.add(pic5);}
                                        }
                                    }
                                }
                                Log.i("tag","size el images "+images.size());
                                ImageSliderAdapter imageSliderAdapter=new ImageSliderAdapter(getApplicationContext(),images);
                               viewPager.setAdapter(imageSliderAdapter);
                            }
                            else
                            {

                                viewPager.setVisibility(View.GONE);
                                TextView imageView=(TextView)findViewById(R.id.uni_profile_image_label);
                                imageView.setVisibility(View.GONE);

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

                            TextView discriptionlable = (TextView) findViewById(R.id.uni_profile_discreption_label);
                            TextView discription = (TextView) findViewById(R.id.uni_profile_discreption);
                            if(jsonObject1.has("description")) {

                               discriptionlable.setText("Discreption");
                                String descreption = jsonObject1.getString("description");

                               discription.setText(descreption);
                            }
                            else
                            {
                                discription.setVisibility(View.GONE);
                                discriptionlable.setVisibility(View.GONE);
                            }

                            TextView citylable=(TextView)findViewById(R.id.uni_profile_city_label);
                            TextView cityy=(TextView)findViewById(R.id.uni_profile_city);
                            if(jsonObject1.has("city")) {
                                citylable.setText("City");
                                String city = jsonObject1.getString("city");
                               cityy.setText(city);
                                location=city;
                            }
                            else
                            {
                                citylable.setVisibility(View.GONE);
                                cityy.setVisibility(View.GONE);
                            }



                            TextView contactslable=(TextView)findViewById(R.id.uni_profile_contacts_label);
                            TextView contactss=(TextView)findViewById(R.id.uni_profile_contacts);
                            if(jsonObject1.has("contacts")) {
                                contactslable.setText("Contacts");

                                String contacts = jsonObject1.getString("contacts");
                                contactss.setText(QueryUtils.parser(contacts));
                            }
                            else
                            {
                                contactslable.setVisibility(View.GONE);
                                contactss.setVisibility(View.GONE);
                            }



                            TextView websiteurllabel=(TextView)findViewById(R.id.uni_profile_website_label);
                            TextView  websiteurll=(TextView)findViewById(R.id.uni_profile_website);
                            if(jsonObject1.has("website_url")) {
                                websiteurllabel.setText("Website");

                                String websiteurl = jsonObject1.getString("website_url");
                                websiteurll.setText(websiteurl);
                            }
                            else {
                                websiteurll.setVisibility(View.GONE);
                                websiteurllabel.setVisibility(View.GONE);
                            }


                            TextView facebookpagelable=(TextView)findViewById(R.id.uni_profile_facebookpage_label);
                            TextView facebookpagee=(TextView)findViewById(R.id.uni_profile_facebookpage);
                            if(jsonObject1.has("facebook_page")) {
                                facebookpagelable.setText("Facebook");

                                String facebookpage = jsonObject1.getString("facebook_page");

                                facebookpagee.setText(facebookpage);
                            }



                            TextView locationlabel=(TextView)findViewById(R.id.uni_profile_location_label);
                            TextView locationn=(TextView)findViewById(R.id.uni_profile_location);
                            if(jsonObject1.has("location")) {
                                locationlabel.setText("Location");

                                String location = jsonObject1.getString("location");
                                locationn.setText(location);
                            }
                            else
                            {
                                locationlabel.setVisibility(View.GONE);
                                locationn.setVisibility(View.GONE);
                            }



                            TextView presidentlable=(TextView)findViewById(R.id.uni_profile_president_label);
                            TextView presidentt=(TextView)findViewById(R.id.uni_profile_presedent);
                                 if(jsonObject1.has("president_name")) {
                                    presidentlable.setText("President");
                                     String president = jsonObject1.getString("president_name");

                                     presidentt.setText(president);
                                 }
                            else
                                 {
                                     presidentlable.setVisibility(View.GONE);
                                     presidentt.setVisibility(View.GONE);
                                 }



                            TextView ranklabel=(TextView)findViewById(R.id.uni_profile_tsnef_label);
                            TextView rankk=(TextView)findViewById(R.id.uni_profile_tsnef);
                            if(jsonObject1.has("rank")) {
                                ranklabel.setText("Rank");

                                String rank = jsonObject1.getString("rank");

                                rankk.setText(QueryUtils.parser(rank));
                            }
                            else
                            {
                                rankk.setVisibility(View.GONE);
                                ranklabel.setVisibility(View.GONE);
                            }




                                TextView pastpresidentlable=(TextView)findViewById(R.id.uni_profile_past_presedent_label);
                            TextView pastpresidnet=(TextView)findViewById(R.id.uni_profile_past_presedent);
                            if(jsonObject1.has("past_presidents")) {
                               pastpresidentlable.setText("Past_President");

                                String past_presidents = jsonObject1.getString("past_presidents");

                                pastpresidnet.setText(QueryUtils.parser(past_presidents));
                            }
                            else
                            {
                                pastpresidentlable.setVisibility(View.GONE);
                                pastpresidnet.setVisibility(View.GONE);
                            }

                            TextView feeslabel=(TextView)findViewById(R.id.uni_profile_fees_label);
                            TextView feess=(TextView)findViewById(R.id.faculty_profile_fees);
                            if(jsonObject1.has("fees")) {
                                String fees = jsonObject1.getString("fees");

                                if (!fees.equals("null")) {
                                    feeslabel.setText("Fees");

                                    feess.setText(fees);

                                } else {
                                    feeslabel.setVisibility(View.GONE);
                                    feess.setVisibility(View.GONE);
                                }
                            }
                            else
                            {
                                feeslabel.setVisibility(View.GONE);
                                feess.setVisibility(View.GONE);
                            }


                                TextView deplable=(TextView)findViewById(R.id.faculty_profile_department_label);
                            TextView deb=(TextView)findViewById(R.id.faculty_profile_department);
                            if(jsonObject1.has("departments")) {
                                deplable.setText("Departments");

                                String departments = jsonObject1.getString("departments");

                                deb.setText(QueryUtils.parser(departments));
                            }
                            else
                            {
                                deb.setVisibility(View.GONE);
                                deplable.setVisibility(View.GONE);
                            }
                            if(jsonObject1.has("x"))
                            {
                                lat=jsonObject1.getDouble("x");
                            }
                            if(jsonObject1.has("y"))
                            {
                                lon=jsonObject1.getDouble("y");
                            }


                            TextView otherslabel=(TextView)findViewById(R.id.uni_profile_others_label);
                            TextView otherss=(TextView)findViewById(R.id.uni_profile_others);
                            if(jsonObject1.has("others")) {
                                String others = jsonObject1.getString("others");

                                if (!others.equals("null")) {

                                   otherslabel.setText("Others");


                                    otherss.setText(QueryUtils.parser(others));


                                } else {
                                    otherss.setVisibility(View.GONE);
                                    otherslabel.setVisibility(View.GONE);
                                }
                            }
                            else
                            {
                                otherss.setVisibility(View.GONE);
                                otherslabel.setVisibility(View.GONE);
                            }

                             TextView facultieslabel=(TextView)findViewById(R.id.uni_profile_faculties_list_view_label);
                            if(jsonObject.has("faculties")) {
                                String type;
                                if (itemType.equals("university")) type = "faculty";
                                else type = "acadfaculty";

                                facultieslabel.setText("Faculties");

                                ArrayList<List_view_faculty_item> arr = new ArrayList<>();
                                JSONArray jsonArray1 = jsonObject.getJSONArray("faculties");
                                for (int i = 0; i < jsonArray1.length(); ++i) {
                                    JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                                    String namee = jsonObject2.getString("name");
                                    int id = jsonObject2.getInt("id");
                                    String idd=String.valueOf(id);
                                    arr.add(new List_view_faculty_item(namee, idd, type));
                                }
                                Log.i(tag, "7gm el arraay " + arr.size());
                                adapter.clear();
                                adapter.addAll(arr);
                                adapter.notifyDataSetChanged();
                            }
                            else
                            {
                                facultieslabel.setVisibility(View.GONE);
                                listView.setVisibility(View.GONE);
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
