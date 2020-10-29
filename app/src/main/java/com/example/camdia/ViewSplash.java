package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ViewSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Chama ViewLogin Após 3s
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {startActivity(new Intent(getApplicationContext(), ViewLogin.class));
                                    finish();
                                }
                            }
                , 3000);


    }
}