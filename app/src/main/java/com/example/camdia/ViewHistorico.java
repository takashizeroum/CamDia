package com.example.camdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;

public class ViewHistorico extends AppCompatActivity {

    AdaptadorH Adapter ;
    private ArrayList<ModelUser> listar = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        visualHist();
        Bundle put;
        String nometabela;
        put = getIntent().getExtras();
        nometabela = put.getString("chave");


    if(nometabela.equals("historico")){
        Log.d("caso", "onCreate: his caso 2 mano ");
        Log.d("caso", "onCreate: "+nometabela);
    }else if (nometabela.equals("ranking")){
        Log.d("caso", "onCreate: ran caso 1 mano");
        Log.d("caso", "onCreate: "+nometabela);
    }

        ImageView btnvolt = findViewById(R.id.backbtn);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent casa = new Intent(getApplicationContext(), ViewUser.class);
                startActivity(casa);
            }
        });
    }



    private void visualHist() {
       /* DBLocalController controller = new DBLocalController(ViewHistorico.this);
        Cursor cursor = controller.resgata();
        if (cursor.moveToFirst()) {
            do {
                listaGenerica.add(new ModelUser(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        cursor.getDouble(9)

                ));
            } while (cursor.moveToNext());
        }*/

        DBJsonReqVoleiAPI jso = new DBJsonReqVoleiAPI(ViewHistorico.this,"http://192.168.0.11/CAMDIA/Query.php");
        jso.getList(new VolleyCallBack() {
            @Override
            public void onSuccess(ArrayList<ModelUser> listaGenerica) {
                listar = listaGenerica;
                Adapter = new AdaptadorH(ViewHistorico.this, R.layout.listitemforhistory, listar);
                listView = findViewById(R.id.listHist);
                listView.setAdapter(Adapter);
            }
        });
    }

}