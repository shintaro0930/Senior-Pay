package com.example.senior_pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DoTransfer extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_transfer);

        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.fund).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case(R.id.back):
                Intent go_back = new Intent(getApplication(), MainActivity.class);
                startActivity(go_back);
                break;

            case(R.id.fund):
                Intent go_fund = new Intent(getApplication(), SendMoney.class);
                startActivity(go_fund);
                break;
        }
    }
}