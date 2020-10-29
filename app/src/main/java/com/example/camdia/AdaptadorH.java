package com.example.camdia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorH extends ArrayAdapter<ModelUser> {

    String nometabela;
    Context contexto;
    int listaLay;
    List<ModelUser> listahumanos;




    public AdaptadorH(Context contexto, int itemlista, List<ModelUser> hList) {

        super(contexto, itemlista, hList);

        this.contexto = contexto;
        this.listaLay = itemlista;
        this.listahumanos = hList;


    }

    //Inflar layout
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(contexto);
        View view = inflater.inflate(listaLay, null);

        final ModelUser ModelUser = listahumanos.get(position);

        TextView txtViewNome = view.findViewById(R.id.nomeHist);
        TextView txtViewKm = view.findViewById(R.id.kmHist);
        TextView txtViewklf = view.findViewById(R.id.statsHist);

        txtViewNome.setText(ModelUser.getNome());
        txtViewKm.setText(ModelUser.getKm().toString());
        txtViewklf.setText(Integer.toString(ModelUser.getRank()));

        return view;

    }


}
