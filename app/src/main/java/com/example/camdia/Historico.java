package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Historico extends AppCompatActivity {

    List<Humano> listaGenerica;
    AdaptadorH AdapterH;

    SQLiteDatabase meubd;
    ListView listView;
    String nometabela = "ex";
    Bundle put;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        put = getIntent().getExtras();
        nometabela = put.getString("chave");
        listView = findViewById(R.id.listHist);
        listaGenerica = new ArrayList<>();

    if(nometabela =="historico"){
        Log.d("caso", "onCreate: his caso 2 mano ");
        Log.d("caso", "onCreate: "+nometabela);
    }else{
        Log.d("caso", "onCreate: ran caso 1 mano");
        Log.d("caso", "onCreate: "+nometabela);
    }

        meubd = openOrCreateDatabase(User.NOME_BANCO_DE_DADOS, MODE_PRIVATE, null);

        visualHist();

        ImageView btnvolt = findViewById(R.id.backbtn);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent casa = new Intent(getApplicationContext(), User.class);
                startActivity(casa);
            }
        });

    }



    private void visualHist() {
        Cursor cursorlista = meubd.rawQuery("SELECT * FROM "+ nometabela , null);

        if (cursorlista.moveToFirst()) {
            do {
                listaGenerica.add(new Humano(
                        cursorlista.getString(0),
                        cursorlista.getInt(1),
                        cursorlista.getString(2),
                        cursorlista.getInt(3),
                        cursorlista.getDouble(4),
                        cursorlista.getInt(5)


                ));
            } while (cursorlista.moveToNext());
        }
        cursorlista.close();

        //Verificar o layout
        AdapterH = new AdaptadorH(this, R.layout.listitemforhistory, listaGenerica, meubd, nometabela);
        listView.setAdapter(AdapterH);


    }


}