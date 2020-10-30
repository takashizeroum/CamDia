package com.example.camdia;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewLogin extends AppCompatActivity {
    ArrayList<ModelUser> lista = new ArrayList<ModelUser>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Botões e seus listeners
        Button btnEntrar = findViewById(R.id.btnEntrar);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {
            Log.d("entre", "onClick: entrou");

        }});
        btnCadastrar.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {cadasta();}});

    }

/*
    private void valida () {
        EditText login = findViewById(R.id.txtlogLogin);
        EditText senha = findViewById(R.id.txtlogSenha);

        final String log = login.getText().toString();
        final String senh= senha.getText().toString();

        Cursor cursorlista = meuBd.rawQuery("SELECT * FROM users WHERE login=? AND senha=?" , new String[]{log, senh});

        if (cursorlista != null && cursorlista.moveToFirst()) {
            ModelUser iuser = new ModelUser(
                    cursorlista.getInt(0),
                    cursorlista.getString(1),
                    cursorlista.getString(2),
                    cursorlista.getString(3)
            );


            startActivity(new Intent(getApplicationContext(), ViewPrincipal.class));
            
            Toast.makeText(getApplicationContext(), "Bem Vindo!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "ViewLogin e/ou Senha Inválidos", Toast.LENGTH_SHORT).show();
        }
        cursorlista.close();



    }
*/

    //abrir formulario cadstro
    private void cadasta() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.cadastro, null);

        final EditText cmplogin = view.findViewById(R.id.cadtxtlogin);
        final EditText cmpnome = view.findViewById(R.id.cadtxtnome);
        final EditText cmpsenha = view.findViewById(R.id.cadtxtSenha);
        final EditText cmpemp = view.findViewById(R.id.cademp);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastro");
        builder.setView(view);
        final AlertDialog dia = builder.create();
        dia.show();

        //sair form cadstro
        view.findViewById(R.id.btnSaircad).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg) {
                dia.dismiss();
            }
        });


        //insere bd
       view.findViewById(R.id.btncadcad).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg) {

                final String log = cmplogin.getText().toString();
                final String nome = cmpnome.getText().toString();
                final String senh= cmpsenha.getText().toString();
                final String emp = cmpemp.getText().toString();



                DBLocalController db = new DBLocalController(getApplicationContext());
                db.insereDado(new ModelUser(nome,log,emp,senh,1,"",0,10.6,1,5.3));

                Toast.makeText(getApplicationContext(), "cadastrado na tabela ", Toast.LENGTH_SHORT).show();




            }
        });



    }


}