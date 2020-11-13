package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPrincipal extends AppCompatActivity {



    Chronometer cronometro;
    ImageView play;
    ImageView stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_d_l);

        //Botões e seus listeners
        findViewById(R.id.cronoplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cronometroStart();
            }
        });


        findViewById(R.id.cronostop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cronometroStop();
            }
        });


        findViewById(R.id.sairbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.simuladorbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                Toast.makeText(getApplicationContext(), "Simulador Iniciado", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.perfilbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), ViewUser.class));
                Toast.makeText(getApplicationContext(), "Perfil de Usuário", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cronometroStart() {
        cronometro = findViewById(R.id.chronometro);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
    }

    public void cronometroStop() {
        cronometro.stop();
    }


}