package com.example.mohandespejman.informationjack;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;



/**
 * Created by MohandesPejman on 11/17/2017.
 */

public class AdaptorMyList extends ArrayAdapter {




    DataBase dataBase;
    SQLiteDatabase sqLiteDatabase;

    boolean book;
    int resource;
    Activity activity;
    ArrayList<PartsList> arrayList;
    ArrayList<Integer> arraylikes;
    ArrayList<Integer> arrayid;


    public  static SharedPreferences myfont;
    public  static SharedPreferences mysizefont;

    public AdaptorMyList(Activity activity , int resource , ArrayList<PartsList> arrayList ,
                         boolean book , ArrayList<Integer> arraylikes , ArrayList<Integer> arrayid)
    {



        super(activity , resource , arrayList);
        this.resource = resource;
        this.activity = activity;
        this.arrayList = arrayList;
        this.book = book;
        this.arraylikes = arraylikes;
        this.arrayid = arrayid;
        myfont = PreferenceManager.getDefaultSharedPreferences(activity);
        mysizefont = PreferenceManager.getDefaultSharedPreferences(activity);

    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View v = this.activity.getLayoutInflater().inflate(this.resource , null);

        final JustifiedTextView txtpart = (JustifiedTextView) v.findViewById(R.id.txtpart);
        final JustifiedTextView txtlabel = (JustifiedTextView) v.findViewById(R.id.txtlabel);
         final ImageView imglike= (ImageView) v.findViewById(R.id.imglike);

        String font = myfont.getString("font" , "3");
        int sizefont = Integer.parseInt(myfont.getString("sizefont" , "16"));

        Typeface typeface = null;
        switch (font)
        {
            case "1" :
            {
                typeface =Typeface.createFromAsset(activity.getAssets(),"fonts/bnazanin.ttf");break;
            }
            case "2" :
            {
                typeface =Typeface.createFromAsset(activity.getAssets(),"fonts/irsans.ttf");break;
            }
            case "3" :
            {
                typeface =Typeface.createFromAsset(activity.getAssets(),"fonts/estedad.ttf");break;
            }
        }


        txtlabel.setTypeface(typeface);
        txtpart.setTypeface(typeface);
        txtlabel.setTextSize(sizefont);
        txtpart.setTextSize(sizefont + 4);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.layoutshowinf);

        PartsList partsList = arrayList.get(position);



        String label = partsList.txtlabel;
        
        txtpart.setText(partsList.txtLesson);
        txtlabel.setText(label);


        if(book == false)
        {


            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    enterlayoutbook(position);

                }
            });
            txtlabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    enterlayoutbook(position);

                }
            });
            txtpart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    enterlayoutbook(position);

                }
            });



        }
        else
        {



            try
            {


                ImageView imgshare =(ImageView) v.findViewById(R.id.imgshare);

                imglike.setVisibility(View.VISIBLE);
                imgshare.setVisibility(View.VISIBLE);


                final int select = arraylikes.get(position);
                if(select == 1)
                {

                    imglike.setImageResource(R.drawable.likeon);

                }





                imglike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        final int select = arraylikes.get(position);
                        if(select == 0)
                        {
                            imglike.setImageResource(R.drawable.likeon);
                            updatelikedatabase(1 , position);
                        }
                        else
                        {
                            imglike.setImageResource(R.drawable.likeoff);
                            updatelikedatabase(0 , position);
                        }



                    }
                });







                imgshare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        actionshare(txtpart.getText().toString() , txtlabel.getText().toString());

                    }
                });


            }
            catch (Exception e)
            {

            }


        }






        return v;



    }





    public void actionshare(String part , String label)
    {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT , part + ":" + label);
        activity.startActivity(Intent.createChooser(intent , "اشتراک گذاری"));


    }
    public void enterlayoutbook(int position)
    {

        Intent intent = new Intent(activity , ActivityBook.class);
        int Group = position+1;
        intent.putExtra("Group" , Group);
        activity.startActivity(intent);


    }


    public void updatelikedatabase(int select , int position)
    {


        try
        {


            dataBase = new DataBase(activity);
            sqLiteDatabase = dataBase.getReadableDatabase();



            int id = arrayid.get(position);

            sqLiteDatabase.execSQL("Update 'information' Set like = "+select+" Where id = "+id+"");

            if(select == 1)
                arraylikes.set(position , 1);
            else
                arraylikes.set(position , 0);

            sqLiteDatabase.close();


        }
        catch (Exception e)
        {

        }



    }
}
