package com.example.mohandespejman.informationjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by MohandesPejman on 12/12/2017.
 */

public class ActivityLargeText extends AppCompatActivity {

    DataBase dataBase;
    SQLiteDatabase sqLiteDatabase;


    TextView txtlargetext;
    TextView txtfirsttext;
    ImageView imgsharlarge;

    public  static SharedPreferences myfont;
    public  static SharedPreferences mysizefont;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.layout_large_book);



        try
        {

            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        }
        catch (Exception e)
        {

        }


        txtlargetext = (TextView) findViewById(R.id.txtlargebook);
        txtfirsttext = (TextView) findViewById(R.id.txtfirst);
        imgsharlarge = (ImageView) findViewById(R.id.imgsharelarge);




        myfont = PreferenceManager.getDefaultSharedPreferences(this);
        mysizefont = PreferenceManager.getDefaultSharedPreferences(this);

        String font = myfont.getString("font" , "3");
        int sizefont = Integer.parseInt(myfont.getString("sizefont" , "16"));

        Typeface typeface = null;
        switch (font)
        {
            case "1" :
            {
                typeface =Typeface.createFromAsset(this.getAssets(),"fonts/bnazanin.ttf");break;
            }
            case "2" :
            {
                typeface =Typeface.createFromAsset(this.getAssets(),"fonts/irsans.ttf");break;
            }
            case "3" :
            {
                typeface =Typeface.createFromAsset(this.getAssets(),"fonts/estedad.ttf");break;
            }
        }

        txtlargetext.setTypeface(typeface);
        txtlargetext.setTextSize(sizefont);

        Bundle bundle = getIntent().getExtras();
        int Group = bundle.getInt("Group");
        String First = bundle.getString("First");

        txtfirsttext.setText(First);


        dataBase = new DataBase(this);
        sqLiteDatabase = dataBase.getReadableDatabase();



        Cursor cursor =sqLiteDatabase.rawQuery("Select * From information where  type="+Group+"", null);
        while (cursor.moveToNext())
        {
            String text = cursor.getString(cursor.getColumnIndex("description"));
            txtlargetext.setText(text);
       }



    }


    public void imgsharelarge(View view)
    {


        actionshare(txtfirsttext.getText().toString() , txtlargetext.getText().toString());


    }

    public void actionshare(String first , String label)
    {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT , first + ":" + label);
        ActivityLargeText.this.startActivity(Intent.createChooser(intent , "اشتراک گذاری"));


    }
}
