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

public class ViewRanking extends AppCompatActivity {

    AdaptadorR Adapter;
    private ArrayList<ModelUser> listaGenerica= new ArrayList<ModelUser>();
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        visualRank();

        ImageView btnvolt = findViewById(R.id.backusr);
        btnvolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent casa = new Intent(getApplicationContext(), ViewUser.class);
                startActivity(casa);
            }
        });


    }

    private void visualRank() {
        DBLocalController controller = new DBLocalController(ViewRanking.this);
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
        }
        Log.d("testing", "visualRank: "+listaGenerica.get(0).getNome());
        //Verificar o layout
        Adapter = new AdaptadorR(this, R.layout.listitemforuser, listaGenerica);
        listView = findViewById(R.id.listRanking);
        listView.setAdapter(Adapter);
    }




}