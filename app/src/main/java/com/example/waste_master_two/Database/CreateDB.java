package com.example.waste_master_two.Database;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.provider.BaseColumns;

        import java.util.ArrayList;
        import java.util.List;

public class CreateDB extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CreateDatabase.db";

    public CreateDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CreateDBProfile.CreateUsers.TABLE_NAME + " (" +
                    CreateDBProfile.CreateUsers._ID + " INTEGER PRIMARY KEY," +
                    CreateDBProfile.CreateUsers.COLUMN_1 + " TEXT," +
                    CreateDBProfile.CreateUsers.COLUMN_2 + " TEXT," +
                    CreateDBProfile.CreateUsers.COLUMN_3 + " TEXT," +
                    CreateDBProfile.CreateUsers.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CreateDBProfile.CreateUsers.TABLE_NAME;



    public long addInfo (String city, String lordtype, String cleaningperiod, String location){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CreateDBProfile.CreateUsers.COLUMN_1, city);
        values.put(CreateDBProfile.CreateUsers.COLUMN_2, lordtype);
        values.put(CreateDBProfile.CreateUsers.COLUMN_3, cleaningperiod);
        values.put(CreateDBProfile.CreateUsers.COLUMN_4, location);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(CreateDBProfile.CreateUsers.TABLE_NAME, null, values);

        return newRowId;

    }

    public Boolean updateInfo (String city, String lordtype, String cleaningperiod, String location){

        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(CreateDBProfile.CreateUsers.COLUMN_2, city);
        values.put(CreateDBProfile.CreateUsers.COLUMN_3, lordtype);
        values.put(CreateDBProfile.CreateUsers.COLUMN_4, cleaningperiod);

        // Which row to update, based on the title
        String selection = CreateDBProfile.CreateUsers.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { city };

        int count = db.update(
                CreateDBProfile.CreateUsers.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >=1 ) {
            return true;
        }
        else {
            return false;
        }

    }

    public void deleteInfo (String city){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = CreateDBProfile.CreateUsers.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { city };
        // Issue SQL statement.
        int deletedRows = db.delete(CreateDBProfile.CreateUsers.TABLE_NAME, selection, selectionArgs);


    }

    public List readAllInfo (){

        String city = "Malabe";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                CreateDBProfile.CreateUsers.COLUMN_1,
                CreateDBProfile.CreateUsers.COLUMN_2,
                CreateDBProfile.CreateUsers.COLUMN_3,
                CreateDBProfile.CreateUsers.COLUMN_4
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = CreateDBProfile.CreateUsers.COLUMN_1 + " = ?";
        String[] selectionArgs = { city };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                CreateDBProfile.CreateUsers.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                CreateDBProfile.CreateUsers.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List citys = new ArrayList<>();
        while(cursor.moveToNext()) {
            String cityies = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            citys.add(cityies);
        }
        cursor.close();
        return citys;
    }

    public List readAllInfo (String city){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                CreateDBProfile.CreateUsers.COLUMN_1,
                CreateDBProfile.CreateUsers.COLUMN_2,
                CreateDBProfile.CreateUsers.COLUMN_3,
                CreateDBProfile.CreateUsers.COLUMN_4
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = CreateDBProfile.CreateUsers.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { city };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                CreateDBProfile.CreateUsers.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                CreateDBProfile.CreateUsers.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List CreateUserInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String cityies = cursor.getString(cursor.getColumnIndexOrThrow(CreateDBProfile.CreateUsers.COLUMN_1));
            String lordtype = cursor.getString(cursor.getColumnIndexOrThrow(CreateDBProfile.CreateUsers.COLUMN_2));
            String cleaningperiod = cursor.getString(cursor.getColumnIndexOrThrow(CreateDBProfile.CreateUsers.COLUMN_3));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(CreateDBProfile.CreateUsers.COLUMN_4));
            CreateUserInfo.add(cityies);//0
            CreateUserInfo.add(lordtype);//1
            CreateUserInfo.add(cleaningperiod);//2
            CreateUserInfo.add(location);//3
        }
        cursor.close();
        return CreateUserInfo;
    }




}

