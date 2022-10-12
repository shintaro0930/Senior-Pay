package com.example.senior_pay;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SendMoney extends AppCompatActivity implements View.OnClickListener {

    private TextView name_text, phone_text;
    private TestOpenHelper2 helper3;
    private SQLiteDatabase db3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        this.findViewById(R.id.back).setOnClickListener(this);
        name_text = findViewById(R.id.name_text);
        phone_text = findViewById(R.id.phone_text);
        readData();
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case (R.id.back):
                    Intent go_back = new Intent(getApplication(), MainActivity.class);
                    startActivity(go_back);
                    break;
            }
    }

    private void readData(){

        //この1行で全てが始まる。TestOpenHelper.javaでSQLiteOpenHelperを呼んでる
        if(helper3 == null){
            helper3 = new TestOpenHelper2(getApplicationContext());
        }

        //ここでデータベーステーブルをcreate or open
        if(db3 == null) {
            db3 = helper3.getReadableDatabase();
            //getWritableDatabase(): 書き込みをしたいときに
        }

        //ログ解析
        Log.d("debug","**********Cursor");

        Cursor cursor = db3.query(
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
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("渋谷駅のコンビニ\n");
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("新宿駅のコンビニ\n");
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("カレーライス屋\n");
        sbuilder_date.append("2022/10/11\n");
        sbuilder_point.append("スーパー\n");

        // 忘れずに！
        cursor.close();

        Log.d("debug","**********"+sbuilder_date);
        name_text.setText(sbuilder_date.toString());
        phone_text.setText(sbuilder_point.toString());

    }
}