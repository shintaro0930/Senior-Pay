package com.example.senior_pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiveMoney extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_money);

        this.findViewById(R.id.back).setOnClickListener(this);
        ImageView imageView2 = findViewById(R.id.image_view_2);
        imageView2.setImageResource(R.drawable.img_2);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            Intent go_back = new Intent(getApplication(), MainActivity.class);
            startActivity(go_back);
        }
    }
}