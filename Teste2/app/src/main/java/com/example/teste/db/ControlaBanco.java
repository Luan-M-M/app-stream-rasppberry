package com.example.teste.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ControlaBanco {
    private SQLiteDatabase db;
    private  CriaBanco banco;


    public ControlaBanco(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDados(String ip, String porta){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.IP, ip);
        valores.put(CriaBanco.PORTA, porta);
        resultado = db.insert(CriaBanco.TABELA,null,valores);
        db.close();

        if(resultado ==-1)
            return "Erro ao criar banco";
        else
            return "Registrado com sucesso";
    }

    @SuppressLint("Range")
    public List<Dados_banco> carregaDados(){
       //https://cursos.alura.com.br/forum/topico-gerar-lista-de-dados-sqlite-28030
        String sql = "SELECT * FROM Dados";
        db = banco.getReadableDatabase();
        String allChar;
        String[] campos =  {banco.ID,banco.PORTA};

        Cursor cursor = db.rawQuery(sql,null);
        List<Dados_banco> dados = new ArrayList<>();
        while(cursor.moveToNext()){
            allChar = "ID: "+ cursor.getString(cursor.getColumnIndex("Id")) + " IP: " + cursor.getString(cursor.getColumnIndex("Ip")) + " Porta: " + cursor.getString(cursor.getColumnIndex("Porta"));
            Log.d("LuaTeste", allChar);
            Dados_banco dado1 = new Dados_banco();
            dado1.setIp(cursor.getString(cursor.getColumnIndex("Ip")));
            dado1.setPorta(cursor.getString(cursor.getColumnIndex("Porta")));
            dados.add(dado1);
        }
        db.close();

        return dados;
    }

    @SuppressLint("Range")
    public Dados_banco rotornaIP(int id){
        String sql = "SELECT * FROM DADOS where Id == " + id;
        Dados_banco dado = new Dados_banco();
        String allChar;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            allChar = "ID: "+ cursor.getString(cursor.getColumnIndex("Id")) + " IP: " + cursor.getString(cursor.getColumnIndex("Ip")) + " Porta: " + cursor.getString(cursor.getColumnIndex("Porta"));
            Log.d("LuaTeste", allChar);
            dado.setIp(cursor.getString(cursor.getColumnIndex("Ip")));
            dado.setPorta(cursor.getString(cursor.getColumnIndex("Porta")));
        }
        return dado;
    }

    public void update(int id, String porta, String ip){
        db = banco.getReadableDatabase();
        db.execSQL("UPDATE Dados SET Ip='"+ip+"' WHERE id="+id);
        db.execSQL("UPDATE Dados SET Porta='"+porta+"' WHERE id="+id);

    }






}
