package com.example.mohandespejman.informationjack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by MohandesPejman on 11/21/2017.
 */

public class ActivityBook extends AppCompatActivity {


    DataBase dataBase;
    SQLiteDatabase sqLiteDatabase;

    ArrayList<Integer> arraylikes = new ArrayList<>();
    ArrayList<Integer> arrayid = new ArrayList<>();
    ListView mylist;
    ArrayList<PartsList> arrayList;
    AdaptorMyList adaptorMyList;

    MyAsyncTask myAsyncTask;




    ProgressBar progressBar ;

    int Group = 0;
    Cursor cursor;

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

        Bundle bundle = getIntent().getExtras();
        Group = bundle.getInt("Group");

        mylist = (ListView) findViewById(R.id.mylist);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);


        arrayList = new ArrayList<>();

        myAsyncTask = new MyAsyncTask();


        dataBase = new DataBase(this);
        sqLiteDatabase = dataBase.getReadableDatabase();







        adaptorMyList = new AdaptorMyList(this , R.layout.view_listview , arrayList , true , arraylikes , arrayid);

        mylist.setAdapter(adaptorMyList);

        Animation animation = AnimationUtils.loadAnimation(this , R.anim.animationlistview);
        mylist.setAnimation(animation);


        setinfbook();



    }


    public void setinfbook()
    {



        myAsyncTask.execute();




    }





    public class MyAsyncTask extends AsyncTask
    {

        @Override
        protected Object doInBackground(Object[] params) {


            try
            {




                if(Group == 0)
                {
                    cursor = sqLiteDatabase.rawQuery("Select * From information where  like = 1", null);
                }
                else
                {
                    cursor = sqLiteDatabase.rawQuery("Select * From information where  type="+Group+"", null);
                }

                while (cursor.moveToNext()) {

                    if(myAsyncTask.isCancelled())
                    {
                        return false;
                    }
                    else
                    {
                        String label = cursor.getString(cursor.getColumnIndex("label"));
                        String description = cursor.getString(cursor.getColumnIndex("description"));

                        arrayList.add(new PartsList(label, description));
                        adaptorMyList.notifyDataSetChanged();

                    }





                }



                if(Group == 0)
                {
                    cursor = sqLiteDatabase.rawQuery("Select * From information where  like = 1", null);
                }
                else
                {
                    cursor = sqLiteDatabase.rawQuery("Select * From information where  type="+Group+"", null);
                }
                while (cursor.moveToNext())
                {


                    Integer select = cursor.getInt(cursor.getColumnIndex("like"));
                    Integer id = cursor.getInt(cursor.getColumnIndex("id"));

                    arraylikes.add(select);
                    arrayid.add(id);





                }

                sqLiteDatabase.close();
            }
            catch (Exception e)
            {

            }

            return null;


        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            progressBar.setVisibility(View.GONE);

            super.onPostExecute(o);
        }
    }



    @Override
    protected void onPause() {
        myAsyncTask.cancel(true);
        super.onPause();
    }
}
