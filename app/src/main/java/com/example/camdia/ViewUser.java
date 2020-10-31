package com.example.camdia;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewUser extends AppCompatActivity {

    TextView nome, desc,rank, km;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        VUCarrega();

        // cadastrando para teste
        DBLocalController control = new DBLocalController(getBaseContext());
        control.insereDado(new ModelUser("jose","ana","kkk","ps",1,"simsss",1,200.0,
                1,10.0));
        control.resgata();

        Toast.makeText(getApplicationContext(), "cadastrado na tabela", Toast.LENGTH_SHORT).show();


        //adapta spinner
        Spinner spn = findViewById(R.id.spinerstatuscomp);
        ADPSpinner spinner = new ADPSpinner(spn, getApplicationContext());


        //escutador do botão voltar
        final ImageView btnvolt = findViewById(R.id.backbtnus);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewPrincipal.class));
            }
        });
    }

    //botões de histórico e ranking
    public void historico(View view) {
        Intent go = new Intent(getApplicationContext(), ViewHistorico.class);
        go.putExtra("chave", "historico");
        startActivity(go);
    }
    public void ranking(View view) {
        Intent go = new Intent(getApplicationContext(), ViewRanking.class);
        go.putExtra("chave", "ranking");
        startActivity(go);
    }

    public void VUCarrega(){
        // chama classe que carrega imagem de perfil
        ImageView i = findViewById(R.id.imgPerfil);
        VoleiAPImg img = new VoleiAPImg();
        img.carregaImg(i, getApplicationContext());

       DBJsonReqVoleiAPI jso = new DBJsonReqVoleiAPI(ViewUser.this,"http://192.168.0.11/CAMDIA/QuerySingle.php");
       jso.getList(new VolleyCallBack() {
           @Override
            public void onSuccess(ArrayList<ModelUser> listaGenerica) {

               nome = findViewById(R.id.usrNameUsr);
               desc = findViewById(R.id.usrempUsr);
               rank = findViewById(R.id.usrcolocaUsr);
               km = findViewById(R.id.usrkmUsr);
               final ModelUser ModelUserV = listaGenerica.get(0);

               nome.setText(ModelUserV.getNome());
               desc.setText(ModelUserV.getEmpresa());
               rank.setText(String.valueOf(ModelUserV.getRank()));
               km.setText(ModelUserV.getKm().toString());

            }});}



}