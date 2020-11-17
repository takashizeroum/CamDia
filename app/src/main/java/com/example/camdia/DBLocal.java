package com.example.camdia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class DBLocal extends SQLiteOpenHelper {

    SQLiteDatabase meuBd;
    private static final String NOME_BANCO = "Memo";


    public DBLocal(Context context) {
        super(context, NOME_BANCO, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS Memo (" +
                "nome varchar(200) NOT NULL," +
                "login varchar(20) NOT NULL," +
                "empresa varchar(30) NOT NULL," +
                "senha varchar(20) NOT NULL ," +
                "id integer DEFAULT 0," +
                "des varchar(200) DEFAULT 0 ," +
                "rank integer DEFAULT 0 ," +
                "km double DEFAULT 0 ," +
                "compete integer DEFAULT 0 ," +
                "tempo double DEFAULT 0," +
                "extras varchar(200))";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}



