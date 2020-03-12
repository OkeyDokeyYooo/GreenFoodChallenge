/* Copyright (C) 2018  Titus L */

package co3.greenfoodchallenge;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user";
    public static final String TABLE2_NAME = "food";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_FOODTYPE = "food_type";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_AMOUNT_AS_KG = "amountAsKG";


    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //create a database
    @Override
    public void onCreate(SQLiteDatabase db){

        /*First table is for storing food*/

        String query = "CREATE TABLE " + TABLE2_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FOODTYPE + " TEXT," +
                COLUMN_AMOUNT + " REAL," +
                COLUMN_AMOUNT_AS_KG + " REAL," +
                COLUMN_UNIT + " TEXT" +
                ");";

        /*Second table is for storing user info such as gender*/
        String query2 = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_GENDER + " TEXT);";

        db.execSQL(query);
        db.execSQL(query2);

    }

    // update the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        onCreate(db);
    }

    //add a new row of food into the database

    public void addFoodInfo(Food food) throws SQLiteException{
        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        values.put(COLUMN_FOODTYPE, food.getType().name());
        values.put(COLUMN_AMOUNT,food.getAmount());
        values.put(COLUMN_UNIT,food.getUnit().name());
        values.put(COLUMN_AMOUNT_AS_KG,food.getAmountAsKg());
        db.insert( TABLE2_NAME, null, values);
        db.close();
    }

    //add the gender of a user to the user table

    public void addUserInfo(String gender){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(COLUMN_GENDER,gender);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //Delete a user from the table
    public void deleteUserInfo(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    //Delete a food from the table
    public void deleteFood(Long id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE2_NAME, "ID = ?",new String[] {Long.toString(id)});
        db.close();
    }

    //Call this method to delete the entire table
    public void deleteFoodTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE2_NAME);
    }

    //This method output food table as a string table

    public String foodInfoToString(){
        String dbString = "";
        String query = "SELECT * FROM " + TABLE2_NAME + " WHERE 1";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query,null);

        //move to the first row

        c.moveToFirst();

        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex(COLUMN_ID)) != null){
                dbString += "\n";
                dbString += c.getString(c.getColumnIndex(COLUMN_ID));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_FOODTYPE));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_AMOUNT));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_UNIT));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_AMOUNT_AS_KG));
            }

            c.moveToNext();
        }

        db.close();
        c.close();
        return dbString;

    }
    /* This Method Converts table 2 from the database to an Array */
    public String[] foodDBtoStringArray(){
        String query = "SELECT * FROM " + TABLE2_NAME + " WHERE 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        String[] myArray = new String[c.getCount()];
        c.moveToFirst();
        int i = 0;
        while (!c.isAfterLast()){
            //String db_id = c.getString(c.getColumnIndex(COLUMN_ID));
            String foodtype = c.getString(c.getColumnIndex(COLUMN_FOODTYPE));
            String amount = c.getString(c.getColumnIndex(COLUMN_AMOUNT));
            String unit = c.getString(c.getColumnIndex(COLUMN_UNIT));
            //String amountAskG = " = "+ c.getString(c.getColumnIndex(COLUMN_AMOUNT_AS_KG)) + " KG";
            myArray[i] = foodtype + " " + amount + " " + unit;
            i++;
            c.moveToNext();
        }
        db.close();
        c.close();
        return myArray;
    }
    /* This method return all the Food from table2 excluding amount as Kg */
    public ArrayList<Food> foodDBtoFoodArray(){
        String query = "SELECT * FROM " + TABLE2_NAME + " WHERE 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        ArrayList<Food> myArray = new ArrayList<>();
        double amount;
        FoodType type;
        FoodUnit unit;
        c.moveToFirst();
        int i = 0;
        while (!c.isAfterLast()){
            try {
                type = FoodType.valueOf(c.getString(c.getColumnIndex(COLUMN_FOODTYPE)));
                amount = c.getDouble(c.getColumnIndex(COLUMN_AMOUNT));
                unit = FoodUnit.valueOf(c.getString(c.getColumnIndex(COLUMN_UNIT)));
       /*   Log.i("Type:",type.name());  //For testing Only
            Log.i("Amount:",String.valueOf(amount));
            Log.i("Unit:",unit.name());  */
                myArray.add(new Food(type,amount,unit));
            /* For Testing Only
            for (Food f:myArray){
                Log.d("myFoodArray",String.valueOf(f));
            }
            */
            } catch (IllegalArgumentException e){
                Log.e("Error",String.valueOf(e));
            }
            i++;
            c.moveToNext();
        }
        db.close();
        c.close();
        return myArray;
    }


    /* This method return all the ID from table2 */
    public ArrayList<Long> foodId (){
        String query = "SELECT "+COLUMN_ID+" FROM " + TABLE2_NAME + " WHERE 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        ArrayList<Long> myID = new ArrayList<>();
        c.moveToFirst();
        int i = 0;
        while(!c.isAfterLast()){
            myID.add(c.getLong(c.getColumnIndex(COLUMN_ID)));
            c.moveToNext();
            i++;
        }
        db.close();
        c.close();
        return myID;
    }
    /* This Method Converts table 2 from the database to a 2d Array */
    public String[][] foodDBto2DArray(){
        String query = "SELECT * FROM " + TABLE2_NAME + " WHERE 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        String[][] myArray = new String[c.getCount()][c.getColumnCount()];
        c.moveToFirst();
        int i = 0;
        while (!c.isAfterLast()){
            String db_id = c.getString(c.getColumnIndex(COLUMN_ID));
            String foodtype = c.getString(c.getColumnIndex(COLUMN_FOODTYPE));
            String amount = c.getString(c.getColumnIndex(COLUMN_AMOUNT));
            String unit = c.getString(c.getColumnIndex(COLUMN_UNIT));
            String amountAskG = "="+ c.getString(c.getColumnIndex(COLUMN_AMOUNT_AS_KG)) + "KG";
            myArray[i][0] = db_id;
            myArray[i][1] = foodtype;
            myArray[i][2] = amount;
            myArray[i][3] = unit;
            myArray[i][4] = amountAskG;
            i++;
            c.moveToNext();
        }
        db.close();
        c.close();
        return myArray;

    }


    // This method output user information such as gender and ID in to string table

    public String userInfoToString(){
        String dbString = "";
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query,null);

        //move to the first row

        c.moveToFirst();

        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex(COLUMN_ID)) != null){
                dbString += c.getString(c.getColumnIndex(COLUMN_ID));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_GENDER));
                dbString += "\n";
            }

            c.moveToNext();
        }

        db.close();
        c.close();
        return dbString;

    }
}
