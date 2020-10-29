package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ViewPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_d_l);

        //Botões e seus listeners
        findViewById(R.id.sairbtn).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {finish();}});

        findViewById(R.id.simuladorbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class ));
                Toast.makeText(getApplicationContext(), "Simulador Iniciado", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.perfilbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewUser.class ));
                Toast.makeText(getApplicationContext(), "Perfil de Usuário", Toast.LENGTH_SHORT).show();
            }
        });

    }





}