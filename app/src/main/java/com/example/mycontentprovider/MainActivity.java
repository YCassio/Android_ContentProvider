package com.example.mycontentprovider;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       insertData();

       Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("content://com.example.mycontentprovider.provider/contacts");
                    // 鏌ヨBook琛ㄤ腑鎵€鏈夌殑鏁版嵁
                    Cursor cursor = getContentResolver().query(uri,null,
                            null, null, null);
                    if (cursor!=null) {
                        while (cursor.moveToNext()){
                            // 閬嶅巻Cursor瀵硅薄锛屽彇鍑烘暟鎹苟鎵撳嵃
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String phone = cursor.getString(cursor.getColumnIndex("phone"));
                            String sex = cursor.getString(cursor.getColumnIndex("sex"));
                            Log.d("MainActivity", "name :" + name+"     "
                                    +"phone :"+phone+"     "+sex);
                        }
                    }
                    cursor.close();
                }
            });


        Button query_item_Button = (Button) findViewById(R.id.query_data_item);
        query_item_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.mycontentprovider.provider/contacts/#");
                EditText editText = (EditText) findViewById(R.id.edit_item);
                String item_name = editText.getText().toString();
                // 鏌ヨBook琛ㄤ腑鎵€鏈夌殑鏁版嵁
                Cursor cursor = getContentResolver().query(uri,null,
                        "name=?", new String[]{item_name}, null);
                if (cursor!=null) {
                    while (cursor.moveToNext()){
                        // 閬嶅巻Cursor瀵硅薄锛屽彇鍑烘暟鎹苟鎵撳嵃
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String phone = cursor.getString(cursor.getColumnIndex("phone"));
                        String sex = cursor.getString(cursor.getColumnIndex("sex"));
                        Log.d("MainActivity", "name :" + name+"     "
                                +"phone :"+phone+"     "+sex);
                    }
                    Log.d("MainActivity","Query Successful");
                }
                cursor.close();
            }
        });
        }

    public void insertData(){
        Uri uri = Uri.parse("content://com.example.mycontentprovider.provider/contacts");
        ContentValues values = new ContentValues();
        values.put("name","Karl");
        values.put("phone","1234567891");
        values.put("sex","man");
        getContentResolver().insert(uri,values);
        values.clear();
        values.put("name","Cally");
        values.put("phone","13431813270");
        values.put("sex","female");
        getContentResolver().insert(uri,values);
        values.clear();
        values.put("name","Snore");
        values.put("phone","15016132820");
        values.put("sex","female");
        getContentResolver().insert(uri,values);
        Log.d("MainActivity","AddData successful!");
    }

    }
