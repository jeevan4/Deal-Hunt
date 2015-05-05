package edu.unm.jeevan.xml_list;

/**
 * Created by Jeevan on 4/25/2015.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class db_handler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "db_deal";

    // Contacts table name
    private static final String TABLE_CONTACTS = "deal_data";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_PRICE = "price";
    private static final String KEY_SAVING = "savings";
    private static final String KEY_IMAGEURL = "imageurl";
    private static final String KEY_URL = "url";

    public db_handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_PRICE + " TEXT," + KEY_SAVING + " TEXT,"+ KEY_IMAGEURL + " TEXT," + KEY_URL + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

<<<<<<< HEAD
    // Saving a new Deal
=======
    // Adding new contact
>>>>>>> origin/master
    void savedeal(Context myContext,String deal_id,String title,String price, String saving, String imageurl, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, deal_id);
        values.put(KEY_TITLE, title);
        values.put(KEY_PRICE, price);
        values.put(KEY_SAVING, saving);
        values.put(KEY_IMAGEURL, imageurl);
        values.put(KEY_URL, url);

        // Inserting Row
        long rowInserted = db.insert(TABLE_CONTACTS, null, values);
        if(rowInserted != -1)
            Toast.makeText(myContext, "Deal Saved ", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(myContext, "Deal Already Saved", Toast.LENGTH_SHORT).show();
        db.close(); // Closing database connection
    }

<<<<<<< HEAD
    // Getting All Deals
=======
    // Getting All Contacts
>>>>>>> origin/master
    public List<EbayData> getAllDeals() {
        List<EbayData> ebaylist = new ArrayList<EbayData>();
        // Select All Query
        String selectQuery = "SELECT id,title,savings,price,imageurl,url FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EbayData ebaydt = new EbayData();
                ebaydt.setId(cursor.getString(0));
                ebaydt.setTitle(cursor.getString(1));
                ebaydt.setSavings(cursor.getString(2));
                ebaydt.setPricenow(cursor.getString(3));
                ebaydt.setImgUrl(cursor.getString(4));
                ebaydt.setDealurl(cursor.getString(5));
                ebaylist.add(ebaydt);
            } while (cursor.moveToNext());
        }

        // return ebay list
        return ebaylist;
    }

    // Deleting single deal
    public void deleteDeal(Context context, String deal_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { deal_id }) > 0)
        {
           Toast.makeText(context, "Deal Removed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Couldn't Remove", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


}