package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {

    List<Humano> listaGenerica;

    Adaptador Adapter;
    SQLiteDatabase meubd;
    ListView listView;
    String nometabela = "ex";
    Bundle put;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        put = getIntent().getExtras();
        nometabela = put.getString("chave");
        listView = findViewById(R.id.listRanking);
        listaGenerica = new ArrayList<>();
        meubd = openOrCreateDatabase(User.NOME_BANCO_DE_DADOS, MODE_PRIVATE, null);

        visualRank();

        ImageView btnvolt = findViewById(R.id.backusr);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent casa = new Intent(getApplicationContext(), User.class);
                startActivity(casa);
            }
        });

    }




    private void visualRank() {
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
        Adapter = new Adaptador(this, R.layout.listitemforuser, listaGenerica, meubd, nometabela);
        listView.setAdapter(Adapter);


    }




}