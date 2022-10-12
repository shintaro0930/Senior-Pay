package com.example.senior_pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HowToUse extends AppCompatActivity implements View.OnClickListener {
    //画面遷移を追加
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case(R.id.back):
                Intent go_charge = new Intent(getApplication(), MainActivity.class);
                startActivity(go_charge);
                break;

            case(R.id.button4):
                Intent go_history = new Intent(getApplication(), FundGuide.class);
                startActivity(go_history);
                break;

            case(R.id.button3):
                Intent go_transfer = new Intent(getApplication(),LogGuide.class);
                startActivity(go_transfer);
                break;

            case(R.id.button2):
                Intent go_to_use = new Intent(getApplication(), ChargeGuide.class);
                startActivity(go_to_use);
                break;

        }
    }
}




