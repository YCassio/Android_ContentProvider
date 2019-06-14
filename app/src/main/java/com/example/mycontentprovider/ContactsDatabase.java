package com.example.mycontentprovider;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ContactsDatabase extends SQLiteOpenHelper{
    private Context mContext;

    public static final String CREATE_CONTECTOR = "create table Contacts ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "phone text,"
            + "sex text)";

    public ContactsDatabase(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //创建联系人表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTECTOR);
        //Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    //更新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Contacts");
        onCreate(db);
    }

}

