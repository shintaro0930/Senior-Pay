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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class TestCamera extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_camera);

        imageView = findViewById(R.id.image_view);
        this.findViewById(R.id.back).setOnClickListener(this);
        this.findViewById(R.id.go).setOnClickListener(this);

        Button cameraButton = findViewById(R.id.camera_button);
        // lambda式
        cameraButton.setOnClickListener( v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            resultLauncher.launch(intent);
        });
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

    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case (R.id.back):
                    Intent go_back = new Intent(getApplication(), MainActivity.class);
                    startActivity(go_back);
                    break;

                case (R.id.go):
                    Intent go_history = new Intent(getApplication(), LookHistory.class);
                    startActivity(go_history);
                    break;
            }
    }
}