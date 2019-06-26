package com.example.mohandespejman.informationjack;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by MohandesPejman on 11/20/2017.
 */

public class DataBase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "DataBaseSoldier.db";
    public static final int DATABASE_VERSION = 1;


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}
