package com.example.mohandespejman.informationjack;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by MohandesPejman on 11/17/2017.
 */

public class ActivityMyList extends AppCompatActivity {



    ListView mylist ;
    ArrayList<PartsList> arrayList;
    AdaptorMyList adaptorMyList ;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mylist);


        try
        {

            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        }
        catch (Exception e)
        {

        }

        mylist = (ListView) findViewById(R.id.mylist);






        arrayList = new ArrayList<>();
        adaptorMyList = new AdaptorMyList(this , R.layout.view_listview , arrayList , false , null , null);

        mylist.setAdapter(adaptorMyList);





        InformationMyList();








    }




    

    public void InformationMyList()
    {


        arrayList.add(new PartsList("بخش اول" , getResources().getString(R.string.listpartone_1)));
        arrayList.add(new PartsList("بخش دوم" , getResources().getString(R.string.listpartone_2)));
        arrayList.add(new PartsList("بخش سوم" , getResources().getString(R.string.listpartone_3)));
        arrayList.add(new PartsList("بخش چهارم" , getResources().getString(R.string.listpartone_4)));
        arrayList.add(new PartsList("بخش پنجم" , getResources().getString(R.string.listpartone_5)));
        arrayList.add(new PartsList("بخش ششم" , getResources().getString(R.string.listpartone_6)));
        arrayList.add(new PartsList("بخش هفتم" , getResources().getString(R.string.listpartone_7)));
        arrayList.add(new PartsList("بخش هشتم" , getResources().getString(R.string.listpartone_8)));
        arrayList.add(new PartsList("بخش نهم" , getResources().getString(R.string.listpartone_9)));
        arrayList.add(new PartsList("بخش دهم" , getResources().getString(R.string.listpartone_10)));
        arrayList.add(new PartsList("بخش یازدهم" , getResources().getString(R.string.listpartone_11)));
        arrayList.add(new PartsList("بخش دوازدهم" , getResources().getString(R.string.listpartone_12)));
        arrayList.add(new PartsList("بخش سیزدهم" , getResources().getString(R.string.listpartone_13)));
        arrayList.add(new PartsList("بخش چهاردهم" , getResources().getString(R.string.listpartone_14)));
        arrayList.add(new PartsList("بخش پانزدهم" , getResources().getString(R.string.listpartone_15)));
        arrayList.add(new PartsList("بخش شانزدهم" , getResources().getString(R.string.listpartone_16)));
        arrayList.add(new PartsList("بخش هفدهم" , getResources().getString(R.string.listpartone_17)));
        arrayList.add(new PartsList("بخش هجدهم" , getResources().getString(R.string.listpartone_18)));
        arrayList.add(new PartsList("بخش نوزدهم" , getResources().getString(R.string.listpartone_19)));
        adaptorMyList.notifyDataSetChanged();


    }
}
