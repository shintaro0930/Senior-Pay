package com.example.senior_pay;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LookHistory extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private TestOpenHelper helper;
    private static final String DATABASE_NAME = "TestDB.db";
    private static final String TABLE_NAME = "testdb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE = "company";
    private static final String COLUMN_NAME_SUBTITLE = "stockprice";
    private SQLiteDatabase db;
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + "testdb";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_SUBTITLE + " INTEGER)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_history);
        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.button).setOnClickListener(this);

        startDB();
        //データベースに繋げてみる

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.back):
                Intent go_back = new Intent(getApplication(), MainActivity.class);
                startActivity(go_back);
                break;

            case (R.id.button):
                Intent go_camera = new Intent(getApplication(), TestCamera.class);
                startActivity(go_camera);
                break;
        }
    }

    private void startDB() {

        if(helper == null){
            helper = new TestOpenHelper(getApplicationContext());
        }
        if(db == null){
            db = helper.getWritableDatabase();
        }

        if(helper == null){
            helper = new TestOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "testdb",
                new String[] { "company", "stockprice" },
                null,
                null,
                null,
                null,
                null
        );
        db.execSQL(
                SQL_DELETE_ENTRIES
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        //for文をまるまるいらない
        //SWL select したものをリストビューで
        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        //insertData(db, "スーパー", 3400);
        //insertData(db, "薬局", 750);

        Log.d("debug","**********"+sbuilder);
        Log.d("debug", "***sbuild***" + sbuilder.toString());
        //ListView.setText(sbuilder.toString());
    }

    private void readData(){
        //helper.refresh(db);
        if(helper == null){
            helper = new TestOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "testdb",
                new String[] { "company", "stockprice" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        Log.d("debug","**********"+sbuilder);
        textView.setText(sbuilder.toString());
    }

    private void insertData(SQLiteDatabase db, String com, int price){

        ContentValues values = new ContentValues();
        values.put("company", com);
        values.put("stockprice", price);


        db.insert("testdb", null, values);
    }
}