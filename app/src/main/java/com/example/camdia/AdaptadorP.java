package com.example.camdia;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class AdaptadorP extends ArrayAdapter<ModelUser> {


    Context contexto;
    int listaLay;
    List<ModelUser> listahumanos;




    public AdaptadorP(Context contexto, int itemlista, List<ModelUser> hList) {

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

        TextView txtViewNome = view.findViewById(R.id.muralnome);
        TextView txtdata = view.findViewById(R.id.muraldate);
        TextView txtmsg = view.findViewById(R.id.muraltexto);
        ImageView imgmural = view.findViewById(R.id.muralimg);




        VoleiAPImg img = new VoleiAPImg();
        img.carregaImg(imgmural, contexto,ModelUser.getSenha());

        final String nome = ModelUser.getNome();
                final String data = ModelUser.getLogin();
                        final String descricao = ModelUser.getEmpresa();
                        final String extras = ModelUser.getSenha();


        txtViewNome.setText(nome);
        txtdata.setText(data);
        txtmsg.setText(descricao);

        view.findViewById(R.id.muralmore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflardialog(nome, data, descricao,extras);
            }
        });



        return view;

    }

    public void inflardialog(String n, String d, String de, String ex) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        LayoutInflater inflater = LayoutInflater.from(contexto);

        View view2 = inflater.inflate(R.layout.paginainfladamural, null);

        TextView txtViewNome = view2.findViewById(R.id.nomepaginainfladamural);
        TextView txtdata = view2.findViewById(R.id.datapaginainfladamural);
        TextView txtmsg = view2.findViewById(R.id.textopaginainfladamural);
        ImageView imgmural = view2.findViewById(R.id.imgpaginainfladamural);
        ImageView close = view2.findViewById(R.id.closepaginainfladamural);
        txtViewNome.setText(n);
        txtdata.setText(d);
        txtmsg.setText(de);

        VoleiAPImg img = new VoleiAPImg();
        img.carregaImg(imgmural, contexto,ex);
        builder.setView(view2);
        final AlertDialog dialog = builder.create();
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });



    }


}





