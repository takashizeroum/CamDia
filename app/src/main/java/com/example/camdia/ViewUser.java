package com.example.camdia;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // cadastrando para teste
        DBLocalController control = new DBLocalController(getBaseContext());
        control.insereDado(new ModelUser("jose","ana","kkk","ps",1,"simsss",1,200.0,
                1,10.0));
        control.resgata();

        Toast.makeText(getApplicationContext(), "cadastrado na tabela", Toast.LENGTH_SHORT).show();


        //adapta spinner
        Spinner spn = findViewById(R.id.spinerstatuscomp);
        ADPSpinner spinner = new ADPSpinner(spn, getApplicationContext());

        // chama classe que carrega imagem de perfil
        ImageView i = findViewById(R.id.imgPerfil);
        VoleiAPImg img = new VoleiAPImg();
        img.carregaImg(i, getApplicationContext());

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


}