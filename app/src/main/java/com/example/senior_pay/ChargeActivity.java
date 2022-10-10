package com.example.senior_pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChargeActivity extends AppCompatActivity implements View.OnClickListener{
    //final TextView text1 = findViewById(R.id.before_yen);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);

        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.clear).setOnClickListener(this);
        this.findViewById(R.id.plus).setOnClickListener(this);
        this.findViewById(R.id.minus).setOnClickListener(this);
        this.findViewById(R.id.account).setOnClickListener(this);
        this.findViewById(R.id.charge).setOnClickListener(this);



        //EditText text1 = findViewById(R.id.before_yen);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case(R.id.back):
                Intent go_back = new Intent(getApplication(), MainActivity.class);
                startActivity(go_back);
                break;

            case(R.id.add_yen):
                //Calcnum(view);
                break;
        }
    }

    //これもっと
    public void Calcnum(View view) {
        TextView text1 = findViewById(R.id.before_yen);
        TextView text2 = findViewById(R.id.add_yen);
        String str1 = text1.getText().toString();
        String str2 = text2.getText().toString();
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        int result = num1 + num2;
        TextView text3 = findViewById(R.id.after_yen);
        String str3 = String.valueOf(result);
        text3.setText(str3);

    }
}