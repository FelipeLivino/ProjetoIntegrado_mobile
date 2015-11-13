package br.usjt.veiculop3.data;

import android.provider.BaseColumns;

/**
 * Created by asbonato on 9/19/15.
 */
public class CategoriasContract {
    public CategoriasContract(){}

    public static abstract class CidadeEntry implements BaseColumns {
        public static final String TABLE_NAME = "cidade";
        public static final String COLUMN_NAME_CIDADE_NOME = "nomeCidade";
    }

    public static abstract class MarcaEntry implements BaseColumns{
        public static final String TABLE_NAME = "marca";
        public static final String COLUMN_NAME_MARCA_NOME = "nomeMarca";
    }

    public static abstract class ModeloEntry implements BaseColumns{
        public static final String TABLE_NAME = "modelo";
        public static final String COLUMN_NAME_MODELO_NOME = "nomeModelo";
    }
}