package br.usjt.veiculop3.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by asbonato on 9/20/15.
 */
public class CategoriasDb {
    CategoriasDbHelper dbHelper;
    public static final String CIDADE = "cidade";
    public static final String MARCA = "marca";
    public static final String MODELO = "modelo";

    public CategoriasDb(Context context){
        dbHelper = new CategoriasDbHelper(context);
    }

    public void insereModelo(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Palio");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Onix");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Strada");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "HB20");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Ka");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Jetta");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Uno");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Sandero");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Prisma");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME, "Corolla");
        db.insert(CategoriasContract.ModeloEntry.TABLE_NAME, null, values);
    }

    public void insereMarca(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME, "Fiat");
        db.insert(CategoriasContract.MarcaEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME, "Chevrolet");
        db.insert(CategoriasContract.MarcaEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME, "Toyota");
        db.insert(CategoriasContract.MarcaEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME, "Hyundai");
        db.insert(CategoriasContract.MarcaEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME, "Volkswagen");
        db.insert(CategoriasContract.MarcaEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME, "Renault");
        db.insert(CategoriasContract.MarcaEntry.TABLE_NAME, null, values);

    }

    public void insereCidade(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CategoriasContract.CidadeEntry.COLUMN_NAME_CIDADE_NOME, "Sao Paulo");
        db.insert(CategoriasContract.CidadeEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.CidadeEntry.COLUMN_NAME_CIDADE_NOME, "Rio de Janeiro");
        db.insert(CategoriasContract.CidadeEntry.TABLE_NAME, null, values);
        values.put(CategoriasContract.CidadeEntry.COLUMN_NAME_CIDADE_NOME, "Bahia");
        db.insert(CategoriasContract.CidadeEntry.TABLE_NAME, null, values);
    }

    public ArrayList<String>  selecionaModelos(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Escolha o modelo");

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {CategoriasContract.ModeloEntry._ID,
                CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME};

        String ordem = CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME;

        Cursor c = db.query(CategoriasContract.ModeloEntry.TABLE_NAME, colunas, null, null, ordem, null, null );

        while(c.moveToNext()){
            lista.add(c.getString(c.getColumnIndex(CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME)));
        }

        c.close();

        return lista;
    }


    public ArrayList<String> selecionaMarca(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Escolha a marca");

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME};

        String ordem = CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME;

        Cursor c = db.query(CategoriasContract.MarcaEntry.TABLE_NAME, colunas, null, null, null, null, ordem);

        while(c.moveToNext()){
            lista.add(c.getString(c.getColumnIndex(CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME)));
        }

        c.close();

        return lista;
    }

    public ArrayList<String> selecionaCidades(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Escolha a cidade");

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {CategoriasContract.CidadeEntry._ID,
                CategoriasContract.CidadeEntry.COLUMN_NAME_CIDADE_NOME};

        String ordem = CategoriasContract.CidadeEntry.COLUMN_NAME_CIDADE_NOME;

        Cursor c = db.query(CategoriasContract.CidadeEntry.TABLE_NAME, colunas, null, null, ordem, null, null );

        while(c.moveToNext()){
            lista.add(c.getString(c.getColumnIndex(CategoriasContract.CidadeEntry.COLUMN_NAME_CIDADE_NOME)));
        }

        c.close();
        return lista;
    }

}
