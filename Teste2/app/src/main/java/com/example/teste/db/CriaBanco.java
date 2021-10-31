package com.example.teste.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

public class CriaBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "dados.db";
    public static final String TABELA = "Dados";
    public static final String ID = "Id";
    public static final String IP = "Ip";
    public static final String PORTA = "Porta";
    public static final int VERSAO = 1;


    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + IP + " text,"
                + PORTA + " text"
                +")";
        String sql = "CREATE TABLE "+TABELA+"(Id integer primary key autoincrement, Ip text, Porta text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA );
        onCreate(db);

    }


}
