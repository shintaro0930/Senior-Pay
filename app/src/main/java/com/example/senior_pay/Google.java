package com.example.senior_pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Google extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);
        this.findViewById(R.id.back).setOnClickListener(this);
        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl("https://www.google.com");


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case(R.id.back):
                Intent go_back = new Intent(getApplication(), MainActivity.class);
                startActivity(go_back);
                break;
        }
    }
}