package com.example.camdia;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBLocalController {
    private SQLiteDatabase db;
    private DBLocal banco;

    public DBLocalController(Context context) {
        banco = new DBLocal(context);
    }
    public String insereDado(ModelUser usr) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", usr.getNome());
        valores.put("login", usr.getLogin());
        valores.put("empresa", usr.getEmpresa());
        valores.put("senha", usr.getSenha());
        valores.put("id", usr.getId());
        valores.put("des", usr.getDesc());
        valores.put("rank", usr.getRank());
        valores.put("km", usr.getKm());
        valores.put("compete", usr.getCompete());
        valores.put("tempo", usr.getTempo());
        resultado = db.insert("Memo", null, valores);
        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro Inserido com sucesso";
        }
    }

    public Cursor resgata() {
        Cursor cursor;
        String[] campos = {"nome", "login", "empresa", "senha", "id", "des", "rank", "km", "compete", "tempo"};
        db = banco.getReadableDatabase();
        cursor = db.query("Memo", campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Log.d("testing", "resgata: "+cursor);
        db.close();
        return cursor;
    }
}




