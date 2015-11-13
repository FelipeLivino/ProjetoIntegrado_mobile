package br.usjt.veiculop3.controller;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.veiculop3.R;
import br.usjt.veiculop3.data.CategoriasDb;
import br.usjt.veiculop3.model.Veiculo;
import br.usjt.veiculop3.network.VeiculoRequester;

public class MainActivity extends ActionBarActivity {

    Spinner spinnerMarca;
    Spinner spinnerCidade;
    Spinner spinnerModelo;
    Button btnConsultar;
    String modelo, cidade, marca;
    ArrayList<Veiculo> veiculos;
    //final String servidor = "colocar o servidor aqui";
    final String servidor = "192.168.1.51:8080/Projeto_integrado2.4";
    VeiculoRequester requester;
    ProgressBar mProgress;
    Intent intent;
    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.contexto = this;
        setupViews();

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        spinnerCidade.setSelection(0);
        spinnerMarca.setSelection(0);
        spinnerModelo.setSelection(0);
    }

    private void setupViews() {
        marca = "";
        cidade = "";
        modelo = "";
        btnConsultar = (Button) findViewById(R.id.botao_enviar);

        mProgress = (ProgressBar) findViewById(R.id.carregando);

        spinnerMarca = (Spinner) findViewById(R.id.dropdown_marcas);
        new CarregaSpinnerMarca().execute(CategoriasDb.MARCA);
        spinnerMarca.setOnItemSelectedListener(new MarcaSelecionado());

        spinnerCidade = (Spinner) findViewById(R.id.dropdown_cidades);
        new CarregaSpinnerCidade().execute(CategoriasDb.CIDADE);
        spinnerCidade.setOnItemSelectedListener(new CidadeSelecionada());

        spinnerModelo = (Spinner) findViewById(R.id.dropdown_modelos);
        new CarregaSpinnerModelo().execute(CategoriasDb.MODELO);
        spinnerModelo.setOnItemSelectedListener(new ModeloSelecionado());


        mProgress.setVisibility(View.INVISIBLE);

    }

    private class MarcaSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            marca = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class CidadeSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            cidade = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class ModeloSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            modelo = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // constante static para identificar o parametro
    public final static String VEICULOS = "br.usjt.VEICULOS";
    //será chamado quando o usuário clicar em enviar
    public void consultarVeiculos(View view) {
        final String pMarca = this.marca.equals("Escolha a marca")?"":marca;
        final String pCidade = this.cidade.equals("Escolha a cidade")?"":cidade;
        final String pModelo = this.modelo.equals("Escolha o modelo")?"":modelo;

        requester = new VeiculoRequester();
        if(requester.isConnected(this)) {
            intent = new Intent(this, ListaVeiculoActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        veiculos = requester.get("http://" + servidor + "/WscVeiculo.json", pMarca, pModelo, pCidade);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(VEICULOS, veiculos);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private class CarregaSpinnerMarca extends AsyncTask<String, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            CategoriasDb db = new CategoriasDb(contexto);
            ArrayList<String> lista = db.selecionaMarca();
            if(lista.size() == 1)
                db.insereMarca();
            lista = db.selecionaMarca();
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result){
            ArrayAdapter<String> marcaAdapter = new ArrayAdapter<String>(contexto,
                    android.R.layout.simple_spinner_item, result);
            spinnerMarca.setAdapter(marcaAdapter);
        }
    }

    private class CarregaSpinnerCidade extends AsyncTask<String, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            CategoriasDb db = new CategoriasDb(contexto);
            ArrayList<String> lista = db.selecionaCidades();
            if(lista.size() == 1)
                db.insereCidade();
            lista = db.selecionaCidades();
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result){
            ArrayAdapter<String> cidadeAdapter = new ArrayAdapter<String>(contexto,
                    android.R.layout.simple_spinner_item, result);
            spinnerCidade.setAdapter(cidadeAdapter);
        }
    }

    private class CarregaSpinnerModelo extends AsyncTask<String, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            CategoriasDb db = new CategoriasDb(contexto);
            ArrayList<String> lista = db.selecionaModelos();
            if(lista.size() == 1)
                db.insereModelo();
            lista = db.selecionaModelos();
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result){
            ArrayAdapter<String> modeloAdapter = new ArrayAdapter<String>(contexto,
                    android.R.layout.simple_spinner_item, result);
            spinnerModelo.setAdapter(modeloAdapter);
        }
    }

}
