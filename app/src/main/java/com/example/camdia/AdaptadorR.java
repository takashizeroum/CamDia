package com.example.camdia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorR extends ArrayAdapter<ModelUser> {

    Context contexto;
    int listaLay;
    List<ModelUser> lista;



    public AdaptadorR(Context contexto, int itemlista, List<ModelUser> lista) {

        super(contexto, itemlista, lista);

        this.contexto = contexto;
        this.listaLay = itemlista;
        this.lista = lista;

    }

    //Inflar layout
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(contexto);
            View view = inflater.inflate(listaLay, null);

            final ModelUser ModelUser = lista.get(position);

            TextView txtViewNome = view.findViewById(R.id.nomeRank);
            TextView txtViewKm = view.findViewById(R.id.kmRank);
            TextView txtViewklf = view.findViewById(R.id.NumRank);
            ImageView ftlist = view.findViewById(R.id.rankimg);

            VoleiAPImg img = new VoleiAPImg();
            img.carregaImg(ftlist, contexto,ModelUser.getExtras());

            txtViewNome.setText(ModelUser.getNome());
            txtViewKm.setText(ModelUser.getKm().toString());
            txtViewklf.setText(Integer.toString(ModelUser.getRank()));

            return view;

    }


}
