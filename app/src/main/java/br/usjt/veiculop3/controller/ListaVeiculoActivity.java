package br.usjt.veiculop3.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.veiculop3.adapter.VeiculoAdapter;
import br.usjt.veiculop3.R;
import br.usjt.veiculop3.model.Veiculo;

public class ListaVeiculoActivity extends ActionBarActivity {
    ListView listView;
    Activity atividade;
    public final static String VEICULO = "br.usjt.VEICULO";
    Veiculo[] veiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veiculo);
        atividade = this;

        //pega a mensagem do intent
        Intent intent = getIntent();
        veiculos = ((ArrayList<Veiculo>)intent.getSerializableExtra(MainActivity.VEICULOS)).toArray(new Veiculo[0]);

      //cria o listview de veiculos
        listView = (ListView) findViewById(R.id.view_lista_veiculo);

        VeiculoAdapter adapter = new VeiculoAdapter(this, veiculos);

        listView.setAdapter(adapter);

        // listener de click em um item do listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheVeiculoActivity.class);
                intent.putExtra(VEICULO, veiculos[position]);

                startActivity(intent);

            }

        });
    }

}