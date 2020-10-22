package com.example.camdia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends ArrayAdapter<Humano> {

    String nometabela;
    Context contexto;
    int listaLay;
    List<Humano> listahumanos;
    SQLiteDatabase meuBancoDeDados;



    public Adaptador(Context contexto, int itemlista, List<Humano> hList, SQLiteDatabase meuBancoDeDados, String nometabela) {

        super(contexto, itemlista, hList);

        this.contexto = contexto;
        this.listaLay = itemlista;
        this.listahumanos = hList;
        this.meuBancoDeDados = meuBancoDeDados;
        this.nometabela = nometabela;

    }

    //Inflar layout
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(contexto);
            View view = inflater.inflate(listaLay, null);

            final Humano humano = listahumanos.get(position);

            TextView txtViewNome = view.findViewById(R.id.nomeRank);
            TextView txtViewKm = view.findViewById(R.id.kmRank);
            TextView txtViewklf = view.findViewById(R.id.NumRank);

            txtViewNome.setText(humano.getNome());
            txtViewKm.setText(humano.getKm().toString());
            txtViewklf.setText(Integer.toString(humano.getRank()));

            return view;

    }


}
