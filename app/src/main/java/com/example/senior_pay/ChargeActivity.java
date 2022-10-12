package com.example.senior_pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChargeActivity extends AppCompatActivity implements View.OnClickListener{
    public static int num = 1000;

    private static String money = "";
    private static int result = num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);

        // 現在のintentを取得する
        Intent intent = getIntent();

        // intentから指定キーの文字列を取得する
        //money = intent.getStringExtra( "money" );


        ((TextView)findViewById(R.id.num_after)).setText("" + MainActivity.money);
        ((TextView)findViewById(R.id.num_before)).setText("" + MainActivity.money);
        //go_back.putExtra( "money", String.valueOf(R.id.num_after ));



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {//if分みたいなもの
            case(R.id.back)://戻るボタンが押された時
                Intent go_back = new Intent(getApplication(), MainActivity.class);
                MainActivity.money = Integer.valueOf(result);
                startActivity(go_back);

                break;

            case(R.id.account)://銀行口座が押された時
                Intent account = new Intent(getApplication(), RegisterAccount.class);
                startActivity(account);
                break;

            case(R.id.plus)://1000円追加が押された時
                TextView m_text_plus = (TextView)findViewById(R.id.maintext);
                //Editの取得
                TextView text_add = findViewById(R.id.num_add);
                //Edit->String
                String str = text_add.getText().toString();
                //str->int
                int num = Integer.parseInt(str);
                //和
                if(num<20000) {
                    int result = num + 1000;
                    ((TextView) findViewById(R.id.num_add)).setText("" + result);
                    m_text_plus.setText(R.string.maintext);
                }
                else{
                    m_text_plus.setText(R.string.maintext_plusError);
                }
                break;

            case(R.id.minus)://1000円減らすが押された時
                TextView m_text_minus = (TextView)findViewById(R.id.maintext);
                //Editの取得
                text_add = findViewById(R.id.num_add);
                //Edit->String
                str = text_add.getText().toString();
                //str->int
                num = Integer.parseInt(str);
                //和
                if(num>0){
                    int result = num - 1000;
                    ((TextView)findViewById(R.id.num_add)).setText("" + result);
                    m_text_minus.setText(R.string.maintext);
                }
                else{

                }
                break;

            case(R.id.charge)://チャージが押された時
                boolean num_changed = false;
                TextView m_text_charge = (TextView)findViewById(R.id.maintext);
                //Editの取得
                text_add = findViewById(R.id.num_add);
                TextView text_before = findViewById((R.id.num_before));
                TextView text_after = findViewById((R.id.num_after));
                //Edit->String
                str = text_add.getText().toString();
                String str2 = text_before.getText().toString();
                String str3 = text_after.getText().toString();
                //str->int
                num = Integer.parseInt(str);//チャージする金額のint
                int num2 = Integer.parseInt(str2);//チャージ前の金額のint
                int num3 = Integer.parseInt(str3);//チャージ後の金額のint
                //処理
                //チャージ前の金額が足りない場合
                if(num>num2){
                    m_text_charge.setText(R.string.maintext_chargeError);//警告文
                    //m_text_charge.setText(name);
                }
                else if(num != 0){
                    //num2-=num;//チャージ前の金額は，チャージ分減算
                    num3+=num;//チャージ後の金額はチャージ分加算
                    num = 0;//チャージ金額は0に戻す


                    ((TextView)findViewById(R.id.num_add)).setText("" + num);
                    ((TextView)findViewById(R.id.num_before)).setText("" + num2);
                    ((TextView)findViewById(R.id.num_after)).setText("" + num3);

                    result = num3;
                    m_text_charge.setText(R.string.maintext);//元に戻す

                    num_changed = true;
                }
                if (num_changed) {
                    Intent go_back_kai = new Intent(getApplication(), MainActivity.class);
                    MainActivity.money = Integer.valueOf(result);
                    startActivity(go_back_kai);
                }

        }
    }
}