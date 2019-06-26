package com.example.mohandespejman.informationjack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by MohandesPejman on 12/4/2017.
 */

public class ActivityOption extends AppCompatActivity {


    public  static  SharedPreferences myfont;
    public  static  SharedPreferences sizefont;
    public  static  SharedPreferences dialogdes;
    public  static  SharedPreferences dialogmes;

    RadioGroup rgfonts;

    RadioButton rdfontone;
    RadioButton rdfonttwo;
    RadioButton rdfontthere;

    CheckBox ckdialogdes;
    CheckBox ckdialogmes;

    SeekBar seekbar;

    String font="3";
    int sizemyfont = 18;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        myfont = PreferenceManager.getDefaultSharedPreferences(ActivityOption.this);
        sizefont = PreferenceManager.getDefaultSharedPreferences(ActivityOption.this);
        dialogdes = PreferenceManager.getDefaultSharedPreferences(ActivityOption.this);
        dialogmes = PreferenceManager.getDefaultSharedPreferences(ActivityOption.this);

        rgfonts = (RadioGroup) findViewById(R.id.rgfonts);

        rdfontone  = (RadioButton) findViewById(R.id.rbfontnazanin);
        rdfonttwo  = (RadioButton) findViewById(R.id.rbfontiransans);
        rdfontthere  = (RadioButton) findViewById(R.id.rbfontestedad);

        ckdialogdes = (CheckBox) findViewById(R.id.ck_dialog_des);
        ckdialogmes = (CheckBox) findViewById(R.id.ck_dialog_mes);


        seekbar = (SeekBar) findViewById(R.id.seekbarsizefont);



        setcheckbox();
        setradiogroup();

        rgfonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {



                switch (rgfonts.getCheckedRadioButtonId())
                {
                    case R.id.rbfontnazanin :
                    {
                        font = "1";break;
                    }
                    case R.id.rbfontiransans :
                    {
                        font = "2";break;
                    }
                    case R.id.rbfontestedad :
                    {
                        font = "3";break;
                    }

                }


            }
        });


        String size = sizefont.getString("sizefont" , "18");

        sizemyfont = Integer.parseInt(size);
        seekbar.setProgress(sizemyfont);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sizemyfont = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }






    @Override
    public void onBackPressed() {


        String message = "ایا میخواهید تغییرات ذخیره شوند؟";
        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_confirmation);
        dialog = builder.create();

        dialog.show();

        TextView txtmessage = (TextView) dialog.findViewById(R.id.txtdialog);
        ImageView imgdialog = (ImageView) dialog.findViewById(R.id.imgdialog);
        Button btnyes = (Button) dialog.findViewById(R.id.btnyes);
        Button btnno = (Button) dialog.findViewById(R.id.btnno);

        btnno.setVisibility(View.VISIBLE);

        txtmessage.setText(message);


        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }
        });
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                    SharedPreferences.Editor editfont = myfont.edit();
                    editfont.putString("font" , font);
                    editfont.commit();



                    SharedPreferences.Editor editsizefont = sizefont.edit();
                    editsizefont.putString("sizefont" , sizemyfont+"");
                    editsizefont.commit();



                    SharedPreferences.Editor editdiades = dialogdes.edit();
                    boolean checkdes;
                    if(ckdialogdes.isChecked())
                        checkdes=true;
                    else
                        checkdes=false;
                    editdiades.putBoolean("description_app" , checkdes);
                editdiades.commit();




                SharedPreferences.Editor editdiames = dialogmes.edit();
                boolean checkmes;
                if(ckdialogmes.isChecked())
                    checkmes=true;
                else
                    checkmes=false;
                editdiames.putBoolean("message_app" , checkmes);
                editdiames.commit();





                finish();

            }
        });






    }


    public void setradiogroup()
    {

        font = myfont.getString("font" , "3");
        switch (font)
        {
            case "1" : rdfontone.setChecked(true) ;break;
            case "2" : rdfonttwo.setChecked(true); break;
            case "3" : rdfontthere.setChecked(true);break;

        }
    }

    public void setcheckbox()
    {

        boolean ckdes = dialogdes.getBoolean("description_app" , false);
        boolean ckmes = dialogmes.getBoolean("message_app" , true);

        ckdialogdes.setChecked(ckdes);
        ckdialogmes.setChecked(ckmes);

    }




}
