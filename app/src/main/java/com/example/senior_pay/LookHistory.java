package com.example.senior_pay;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LookHistory extends AppCompatActivity implements View.OnClickListener {

    private TextView textView, textView2, textView3;
    private TestOpenHelper helper2;
    private SQLiteDatabase db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_history);
        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.button).setOnClickListener(this);

        textView = findViewById(R.id.text_view);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        readData();


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

    private void readData(){

        //この1行で全てが始まる。TestOpenHelper.javaでSQLiteOpenHelperを呼んでる
        if(helper2 == null){
            helper2 = new TestOpenHelper(getApplicationContext());
        }

        //ここでデータベーステーブルをcreate or open
        if(db2 == null) {
            db2 = helper2.getReadableDatabase();
            //getWritableDatabase(): 書き込みをしたいときに
        }

        //ログ解析
        Log.d("debug","**********Cursor");

        Cursor cursor = db2.query(
                "testdb",
                new String[] { "company", "stockprice" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder_date = new StringBuilder();
        StringBuilder sbuilder_point = new StringBuilder();
        StringBuilder sbuilder_price = new StringBuilder();
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("渋谷駅のコンビニ\n");
        sbuilder_price.append("500円\n");
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("新宿駅のコンビニ\n");
        sbuilder_price.append("800円\n");
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("カレーライス屋\n");
        sbuilder_price.append("100円\n");
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("スーパー\n");
        sbuilder_price.append("4200円\n");

//        for (int i = 0; i < cursor.getCount(); i++) {
//            sbuilder.append(cursor.getString(0));
//            sbuilder.append(": ");
//            sbuilder.append(cursor.getInt(1));
//            sbuilder.append("\n");
//            cursor.moveToNext();
//        }

        // 忘れずに！
        cursor.close();

        //Log.d("debug","**********"+sbuilder_date);
        textView.setText(sbuilder_date.toString());
        textView2.setText(sbuilder_point.toString());
        textView3.setText(sbuilder_price.toString());

    }
}