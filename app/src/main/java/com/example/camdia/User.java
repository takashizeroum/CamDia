package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class User extends AppCompatActivity {

    public SQLiteDatabase meubd;
    static final String NOME_BANCO_DE_DADOS = "Rankingv1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



        // Cria BD

        meubd = openOrCreateDatabase(NOME_BANCO_DE_DADOS, MODE_PRIVATE, null);


        meubd.execSQL(
                "CREATE TABLE IF NOT EXISTS ranking (" +
                        "nome varchar(200) NOT NULL," +
                        "id integer PRIMARY KEY AUTOINCREMENT," +
                        "descricao varchar(200) ," +
                        "ranking integer , " +
                        "km double NOT NULL," +
                        "competindo integer NOT NULL );"
        );

        meubd.execSQL(
                "CREATE TABLE IF NOT EXISTS historico (" +
                        "nome varchar(200) NOT NULL," +
                        "id integer PRIMARY KEY AUTOINCREMENT," +
                        "descricao varchar(200) ," +
                        "ranking integer , " +
                        "km double NOT NULL," +
                        "competindo integer NOT NULL );"
        );


        final ImageView btnvolt = findViewById(R.id.backbtnus);
                btnvolt.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {startActivity(new Intent(getApplicationContext(), TDL.class));}});


    }
    public void historico(View view) {

        //TESTE para sempre haver ao menos um registro
        adicionarr("historico");


        Intent go = new Intent(getApplicationContext(), Historico.class);
        go.putExtra("chave", "historico");
        startActivity(go);

    }

    public void ranking(View view) {

        //TESTE para sempre haver ao menos um registro
        adicionarr("ranking");


        Intent go = new Intent(getApplicationContext(), Ranking.class);
        go.putExtra("chave", "ranking");
        startActivity(go);


    }

    private void adicionarr(String TesteNomeTab) {
        final String txtnome = "Belchior Buarque de Oliveira";
        String descr = "Uma Breve descrição do sujeito ao criterio do mesmo com aproximadamente cem a duzentos caracteres, isso tudo, opcional é claro. ";
        int rank = 2;
        Double km = 100.0;
        int comp = 1;



        String insertSQL = "INSERT INTO " + TesteNomeTab + " (" +
                "nome, " +
                "descricao,"+
                "ranking," +
                "km,"+
                "competindo)"+
                "VALUES(?,?,?,?,?);";

        meubd.execSQL(insertSQL, new String[]{txtnome, descr, String.valueOf(rank), String.valueOf(km), String.valueOf(comp)});

        Toast.makeText(getApplicationContext(), "cadastrado na tabela ", Toast.LENGTH_SHORT).show();
    }

    /*public void objet(DAOUsuario user){
        TextView txtnome = findViewById(R.id.usrNameUsr);
        txtnome.setText(user.getLogin());
        Log.d("oqueoque2", "objet: "+user.getLogin());
    }*/


}