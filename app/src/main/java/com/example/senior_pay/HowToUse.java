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

        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.charge_button).setOnClickListener(this);
        this.findViewById(R.id.look_history_button).setOnClickListener(this);
        this.findViewById(R.id.do_payment_button).setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case(R.id.back):
                Intent go_back = new Intent(getApplication(), MainActivity.class);
                startActivity(go_back);
                break;

            case(R.id.charge_button):
                Intent how_to_charge = new Intent(getApplication(), how_to_use_charge.class);
                startActivity(how_to_charge);
                break;

            case(R.id.look_history_button):
                Intent how_to_look_history = new Intent(getApplication(), HowToLookHistory.class);
                startActivity(how_to_look_history);
                break;

            case(R.id.do_payment_button):
                Intent how_to_do_payment = new Intent(getApplication(), HowToDoPayment.class);
                startActivity(how_to_do_payment);
                break;
        }
    }
}