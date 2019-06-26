package com.example.mohandespejman.informationjack;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MohandesPejman on 11/26/2017.
 */

public class ActivitySendEmail extends AppCompatActivity {


    EditText edtage;
    EditText edthome;
    EditText edtmessage;


    FloatingActionButton floatsend;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_email);

        edtage = (EditText) findViewById(R.id.edtage);
        edthome = (EditText) findViewById(R.id.edthome);
        edtmessage = (EditText) findViewById(R.id.edtmessage);

        floatsend = (FloatingActionButton) findViewById(R.id.floatsend);

        try
        {

            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        }
        catch (Exception e)
        {
            setdialog(R.drawable.recordtwo , getResources().getString(R.string.dialog_error_sdk));
        }



    }

    public void floatsend(View view)
    {


        String age ="";
        String home = "";
        String message = "";

        try
        {


            if(!(edtmessage.getText().toString().equals("")))
            {
                message = edtmessage.getText().toString();
            }

            if(!(edtage.getText().toString().equals("")))
            {
                age = edtage.getText().toString();
            }
            if(!(edthome.getText().toString().equals("")))
            {
                home = edthome.getText().toString();
            }



            if(!(edtmessage.getText().toString().equals("")))
            {


                if(checknetwork())
                {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL , new String[]{"Moafisarbazi@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT , "درخواست معافیت");


                    if( age != "" && home != "")
                    {


                        intent.putExtra(Intent.EXTRA_TEXT ,  " سن:" + age + "\n" + " متصدی محل سکونت:" + home + "\n" + "درخواست:" + message);

                    }
                    else if(home != "" )
                    {

                        intent.putExtra(Intent.EXTRA_TEXT ,  " متصدی محل سکونت:" + home + "\n" +  "درخواست:" + message);


                    }
                    else if(age != "")
                    {

                        intent.putExtra(Intent.EXTRA_TEXT ,  " سن:" + age + "\n" +  "درخواست:" + message);

                    }
                    else
                    {

                        intent.putExtra(Intent.EXTRA_TEXT ,  "درخواست:" + message);

                    }

                    try
                    {
                        startActivity(Intent.createChooser(intent , "ارسال ایمیل..."));
                    }
                    catch (android.content.ActivityNotFoundException ex)
                    {

                        setdialog(R.drawable.recordtwo , "برنامه ای برای ارسال ایمیل پیدا نشد لطفا تنظیمات ایمیل خود را وارد کنید");

                    }


                }
                else
                {
                    setdialog(R.drawable.recordtwo , getResources().getString(R.string.dialog_error_network));
                }




            }
            else
            {

                setdialog(R.drawable.recordtwo , getResources().getString(R.string.dialog_error_message_email));

            }


        }
        catch (Exception e)
        {
            setdialog(R.drawable.recordtwo , getResources().getString(R.string.dialog_error));
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


    public void setdialog(Integer drawle , String message )
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

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });




    }


}
