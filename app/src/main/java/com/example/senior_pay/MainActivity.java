package com.example.senior_pay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

//Main画面

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;

    public static int money = 1000;

    //private static String money = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.charge).setOnClickListener(this);
        this.findViewById(R.id.history).setOnClickListener(this);
        this.findViewById(R.id.transfer).setOnClickListener(this);
        this.findViewById(R.id.how_to_use).setOnClickListener(this);
        this.findViewById(R.id.payment).setOnClickListener(this);
        this.findViewById(R.id.google).setOnClickListener(this);


        //チャージされた金額の更新
        // 現在のintentを取得する
        //TextView text = findViewById(R.id.all_price);

        //Intent intent_backed = getIntent();
        // intentから指定キーの文字列を取得する
        //money = intent_backed.getStringExtra( "money_after" );

/**
        if (money == null) {
            int price = ChargeActivity.num;
            Integer i = Integer.valueOf(price);
            //String str = "残額は" + i.toString() + "円です";
            ((TextView) findViewById(R.id.all_price)).setText(""+i);
        }else{
            //String str = "残額は" + money + "円です";
            ((TextView) findViewById(R.id.all_price)).setText(""+money);
        }
**/
        // old
        TextView text = findViewById(R.id.all_price);
        int price = money;
        Integer i = Integer.valueOf(price);
        String str = "残額は" + i.toString() + "円です";
        ((TextView) findViewById(R.id.all_price)).setText(str);


        //カメラの起動
        Button cameraButton = findViewById(R.id.payment);
        cameraButton.setOnClickListener( v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            resultLauncher.launch(intent);
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case(R.id.charge):
                Intent go_charge = new Intent(getApplication(), ChargeActivity.class);

                go_charge.putExtra( "money", String.valueOf(ChargeActivity.num));

                startActivity(go_charge);

                // 遷移先から返却されてくる際の識別コード
                //int requestCode = 1001;

                // 返却値を考慮したActivityの起動を行う
                //startActivityForResult( intent, requestCode );

                break;

            case(R.id.history):
                Intent go_history = new Intent(getApplication(), LookHistory.class);
                startActivity(go_history);
                break;

            case(R.id.transfer):
                Intent go_transfer = new Intent(getApplication(), DoTransfer.class);
                startActivity(go_transfer);
                break;

            case(R.id.how_to_use):
                Intent go_to_use = new Intent(getApplication(), HowToUse.class);
                startActivity(go_to_use);
                break;

            case(R.id.google):
                Intent go_google = new Intent(getApplication(), Google.class);
                startActivity(go_google);
                break;
        }
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data  = result.getData();
                    if(data != null) {
                        Bitmap bitmap;
                        // cancelしたケースも含む
                        if (data.getExtras() == null) {

                            Log.d("debug", "cancel ?");
                            return;
                        } else {
                            bitmap = (Bitmap) data.getExtras().get("data");
                            if (bitmap != null) {
                                // 画像サイズを計測
                                int bmpWidth = bitmap.getWidth();
                                int bmpHeight = bitmap.getHeight();
                                Log.d("debug", String.format("w= %d", bmpWidth));
                                Log.d("debug", String.format("h= %d", bmpHeight));
                            }
                        }

                        imageView.setImageBitmap(bitmap);
                    }
                }
            });
}