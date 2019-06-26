package com.example.mohandespejman.informationjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {


    Button btnpartone;
    Button btnparttwo;
    Button btnpartthere;
    Button btnpartfour;
    Button btnpartfive;
    Button btnpartsix;
    Button btnpartseven;




    DrawerLayout drawerLayout;
    NavigationView navigation;

    ImageView imgmenu;
    ImageView imgsendemail;


    public static SharedPreferences shdescription;
    public static SharedPreferences shmessage;




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.navigation);
        }
        catch (Exception e)
        {

        }







        try
        {

            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        }
        catch (Exception e)
        {

        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawel);
        navigation = (NavigationView) findViewById(R.id.navigation);




        imgmenu = (ImageView) findViewById(R.id.imgmenu);
        imgsendemail = (ImageView) findViewById(R.id.imgsendemail);


        btnpartone = (Button) findViewById(R.id.btnpartone);
        btnparttwo = (Button) findViewById(R.id.btnparttwo);
        btnpartthere = (Button) findViewById(R.id.btnpartthere);
        btnpartfour = (Button) findViewById(R.id.btnpartfour);
        btnpartfive = (Button) findViewById(R.id.btnpartfive);
        btnpartsix = (Button) findViewById(R.id.btnpartsix);
        btnpartseven = (Button) findViewById(R.id.btnpartseven);

        shdescription = PreferenceManager.getDefaultSharedPreferences(this);
        shmessage = PreferenceManager.getDefaultSharedPreferences(this);

        boolean dialogdes = shdescription.getBoolean("description_app" , true);
        if(dialogdes)
        {
            setdialog(R.drawable.recordtwo , getResources().getString(R.string.dialog_des_top) + "\n" + "\n"
            +  getResources().getString(R.string.dialog_des) , false);

            SharedPreferences.Editor editor = shdescription.edit();
            editor.putBoolean("description_app" , false);
            editor.commit();

        }

        setanimbuttons();





        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                switch (item.getItemId())
                {

                    case R.id.menu_message :
                    {

                        Intent intent = new Intent(ActivityMain.this , ActivitySendEmail.class);
                        startActivity(intent);
                        break;

                    }
                    case R.id.menu_mylike :
                    {

                        Intent intent = new Intent(ActivityMain.this , ActivityBook.class);
                        intent.putExtra("Group" , 0);
                        startActivity(intent);
                        break;

                    }

                    case R.id.menu_option :
                    {

                        Intent intent = new Intent(ActivityMain.this , ActivityOption.class);
                        startActivity(intent);
                        break;

                    }

                    case R.id.menu_source :
                    {

                        setdialog(R.drawable.recordtwo , "منابع:" + "\n" + "www.yjc.ir" + "\n" + "\n" + "www.simiaservice.com" +"\n" + "\n" + "www.parsine.com", false);
                        break;

                    }
                    case  R.id.menu_about :
                    {

                        setdialog(R.drawable.recordtwo , "طراح و برنامه نویس" + "\n" + "مهندس پژمان حاجی حیدری"  + "\n" + "\n" + "گرد اوری اطلاعات برنامه" + "\n"
                        + "مهندس مجتبی بیگی" + "\n" +  "\n" + "با تشکر از"  + "\n" + "مهدی حاجی حیدری"  , false);
                        break;

                    }
                    case R.id.menu_mind :
                    {


                        sendmessage();

                    }

                }
                return true;
            }
        });




    }





    public  void imgsendemail(View view)
    {


        Intent intent = new Intent(ActivityMain.this , ActivitySendEmail.class);
        startActivity(intent);


    }


    public void menu(View view)
    {


        drawerLayout.openDrawer(Gravity.RIGHT);

    }




    public void btnpartone(View view)
    {


        Intent intent = new Intent(this , ActivityMyList.class);
        startActivity(intent);


    }


    public void btnparttwo(View view)
    {


        startbookintent(20 , getResources().getString(R.string.type_two));


    }

    public void btnpartthere(View view)
    {

        startbookintent(21 , getResources().getString(R.string.type_there));

    }

    public void btnpartfour(View view)
    {

        startbookintent(22 , getResources().getString(R.string.type_four));

    }

    public void btnpartfive(View view)
    {

        startbookintent(23 , getResources().getString(R.string.type_five));

    }

    public void btnpartsix(View view)
    {

        startbookintent(24 , getResources().getString(R.string.type_six));

    }

    public void btnpartseven(View view)
    {

        startbookintent(25 , getResources().getString(R.string.type_seven));

    }



    public void setanimbuttons()
    {



        Animation animsendemail = AnimationUtils.loadAnimation(this , R.anim.animsendemail);
        Animation animation = AnimationUtils.loadAnimation(this ,R.anim.animbuttonone);
        Animation animation2 = AnimationUtils.loadAnimation(this ,R.anim.animbuttontwo);
        Animation animation3 = AnimationUtils.loadAnimation(this ,R.anim.animbuttonthere);
        Animation animation4 = AnimationUtils.loadAnimation(this ,R.anim.animbuttonfour);
        Animation animation5 = AnimationUtils.loadAnimation(this ,R.anim.animbuttonfive);
        Animation animation6 = AnimationUtils.loadAnimation(this ,R.anim.animbuttonsix);
        Animation animation7 = AnimationUtils.loadAnimation(this ,R.anim.animbuttonseven);


        btnpartone.setAnimation(animation);
        btnparttwo.setAnimation(animation2);
        btnpartthere.setAnimation(animation3);
        btnpartfour.setAnimation(animation4);
        btnpartfive.setAnimation(animation5);
        btnpartsix.setAnimation(animation6);
        btnpartseven.setAnimation(animation7);
        imgsendemail.setAnimation(animsendemail);



    }


    @Override
    public void onBackPressed() {

        boolean message = shmessage.getBoolean("message_app" , true);

        if(message)
        {


            setdialog(R.drawable.menustar , "ایا میخواهید به برنامه نظر بدهید؟" , true);


        }
        else
        {
            finish();
        }



    }

    public void setdialog(Integer drawle , String message , final boolean mes)
    {


        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_confirmation);
        dialog = builder.create();

        dialog.show();

        TextView txtmessage = (TextView) dialog.findViewById(R.id.txtdialog);
        ImageView imgdialog = (ImageView) dialog.findViewById(R.id.imgdialog);
        Button btnyes = (Button) dialog.findViewById(R.id.btnyes);
        Button btnno = (Button) dialog.findViewById(R.id.btnno);

        txtmessage.setText(message);
        imgdialog.setImageResource(drawle);

        if(mes == true)
        {
            btnno.setVisibility(View.VISIBLE);

            btnno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mes == true)
                {


                    if(checknetwork())
                    {
                        sendmessage();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(ActivityMain.this , "عدم دسترس به اینترنت"   , Toast.LENGTH_SHORT).show();
                    }


                }

                dialog.dismiss();
            }
        });




    }

    public void sendmessage()
    {

        try
       {


               SharedPreferences.Editor editor = shmessage.edit();
               editor.putBoolean("message_app" , false);
               editor.commit();

               Intent intent = new Intent(Intent.ACTION_EDIT);
               intent.setData(Uri.parse("bazaar://details?id=" + "com.example.mohandespejman.informationjack"));
               intent.setPackage("com.farsitel.bazaar");
               startActivity(intent);



       }
       catch (Exception e)
       {

       }



    }


    public boolean checknetwork()
    {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if(info != null && info.isConnected())
        {
            return  true;
        }
        else
        {
            return false;
        }

    }



    public void startbookintent(int Group , String myfirst)
    {




        Intent intent = new Intent(ActivityMain.this , ActivityLargeText.class);
        intent.putExtra("Group" , Group);
        intent.putExtra("First" , myfirst);
        startActivity(intent);


    }


}
