package br.usjt.veiculop3.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asbonato on 9/19/15.
 */
public class CategoriasDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Categorias.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String OPEN_PAR = "(";
    public static final String CLOSE_PAR = ")";
    public static final String SQL_CREATE_CIDADE =
            "CREATE TABLE " + CategoriasContract.CidadeEntry.TABLE_NAME + OPEN_PAR +
                    CategoriasContract.CidadeEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    CategoriasContract.CidadeEntry.COLUMN_NAME_CIDADE_NOME + TEXT_TYPE + CLOSE_PAR;
    public static final String SQL_DROP_CIDADE =
            "DROP TABLE IF EXISTS " + CategoriasContract.CidadeEntry.TABLE_NAME;
    public static final String SQL_CREATE_MARCA =
            "CREATE TABLE " + CategoriasContract.MarcaEntry.TABLE_NAME + OPEN_PAR +
                    CategoriasContract.MarcaEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    CategoriasContract.MarcaEntry.COLUMN_NAME_MARCA_NOME + TEXT_TYPE + CLOSE_PAR;
    public static final String SQL_DROP_MARCA =
            "DROP TABLE IF EXISTS " + CategoriasContract.MarcaEntry.TABLE_NAME;
    public static final String SQL_CREATE_MODELO =
            "CREATE TABLE " + CategoriasContract.ModeloEntry.TABLE_NAME + OPEN_PAR +
                    CategoriasContract.ModeloEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    CategoriasContract.ModeloEntry.COLUMN_NAME_MODELO_NOME + TEXT_TYPE + CLOSE_PAR;
    public static final String SQL_DROP_MODELO=
            "DROP TABLE IF EXISTS " + CategoriasContract.ModeloEntry.TABLE_NAME;


    public CategoriasDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CIDADE);
        db.execSQL(SQL_CREATE_MARCA);
        db.execSQL(SQL_CREATE_MODELO);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //tabelas de parâmetros que podem ser recriadas
        db.execSQL(SQL_DROP_CIDADE);
        db.execSQL(SQL_DROP_MARCA);
        db.execSQL(SQL_DROP_MODELO);
        db.execSQL(SQL_CREATE_CIDADE);
        db.execSQL(SQL_CREATE_MARCA);
        db.execSQL(SQL_CREATE_MODELO);
    }

}
