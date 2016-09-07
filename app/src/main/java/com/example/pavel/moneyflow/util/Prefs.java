package com.example.pavel.moneyflow.util;

import android.net.Uri;

public class Prefs {
    public static final String LOG_TAG = "MoneyFlow";

    //------------------------Provider----------------------------
    public static final String AUTHORITY = "com.example.pavel.moneyflow.provider";

    public static String URI_TYPE_EXPENSES = "expenses";
    public static String URI_TYPE_EXPENSES_NAME = "expenses_desctiption";
    public static String URI_TYPE_EXPENSES_JOINED = "expenses_joined";
    public static final Uri URI_EXPENSES = Uri.parse("content://" + AUTHORITY + "/" + URI_TYPE_EXPENSES);
    public static final Uri URI_EXPENSES_NAME = Uri.parse("content://" + AUTHORITY + "/" + URI_TYPE_EXPENSES_NAME);
//    public static final Uri URI_EXPENSE_NAME_ID = Uri.parse("content://" + AUTHORITY + "/" + URI_TYPE_EXPENSE_NAME + "/#");
    public static final Uri URI_EXPENSES_JOINED = Uri.parse("content://" + AUTHORITY + "/" + URI_TYPE_EXPENSES_JOINED);

    //--------------------DB Constants-----------------------------------
    public static final String DB_NAME = "MoneyFlowDB";
    public static final int DB_CURRENT_VERSION = 1;
    public static final String FIELD_ID = "_id";

    //___________________TABLE EXPENSES  ____________________________
    public static final String TABLE_EXPENSES = "expenses";
    public static final String EXPENSES_FIELD_ID_PASSIVE = "id_passive";
    public static final String EXPENSES_FIELD_VOLUME = "volume";
    public static final String EXPENSES_FIELD_DATE = "date";

    //_________________TABLE EXPENSES DESCRIPTION_______________________
    public static final String TABLE_EXPENSES_DESCRIPTION = "expenses_desctiption";
    public static final String EXPENSES_DESCRIPTION_FIELDS_NAME = "desctiption";

    //_________________LOADER_______________________
    public static final int ID_EXPENSES_DESCRIPTION = 1;

}
