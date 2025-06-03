package com.pucpr.bancosolidario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "doacoes.db";
    private static final int VERSAO_BANCO = 1;

    public static final String TABELA_DOACOES = "doacoes";
    public static final String COL_ID = "id";
    public static final String COL_NOME_DOADOR = "nome_doador";
    public static final String COL_NOME_ITEM = "nome_item";
    public static final String COL_DESCRICAO = "descricao";
    public static final String COL_QUANTIDADE = "quantidade";

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_DOACOES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOME_DOADOR + " TEXT, " +
                COL_NOME_ITEM + " TEXT NOT NULL, " +
                COL_DESCRICAO + " TEXT, " +
                COL_QUANTIDADE + " INTEGER NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_DOACOES);
        onCreate(db);
    }

    // Inserir uma nova doação no banco de dados
    public boolean inserirDoacao(Doacao doacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(COL_NOME_DOADOR, doacao.getNomeDoador());
        valores.put(COL_NOME_ITEM, doacao.getNomeItem());
        valores.put(COL_DESCRICAO, doacao.getDescricao());
        valores.put(COL_QUANTIDADE, doacao.getQuantidade());

        long resultado = db.insert(TABELA_DOACOES, null, valores);
        db.close();

        return resultado != -1;
    }

    // Listar todas as doações cadastradas
    public List<Doacao> listarDoacoes() {
        List<Doacao> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_DOACOES,
                null, // todas as colunas
                null,
                null,
                null,
                null,
                COL_ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                Doacao d = new Doacao();
                d.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
                d.setNomeDoador(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOME_DOADOR)));
                d.setNomeItem(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOME_ITEM)));
                d.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRICAO)));
                d.setQuantidade(cursor.getInt(cursor.getColumnIndexOrThrow(COL_QUANTIDADE)));

                lista.add(d);
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return lista;
    }

    public void excluirDoacao(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_DOACOES, COL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}
