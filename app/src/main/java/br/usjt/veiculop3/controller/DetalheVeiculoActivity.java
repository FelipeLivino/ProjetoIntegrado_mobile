package br.usjt.veiculop3.controller;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import br.usjt.veiculop3.R;
import br.usjt.veiculop3.model.Veiculo;
import br.usjt.veiculop3.network.VeiculoRequester;

public class DetalheVeiculoActivity extends ActionBarActivity {
    TextView veiculoModelo;
    ImageView veiculoImageView;
    TextView veiculoKmlivre;
    TextView veiculoCidade;
    TextView veiculoMarca;
    TextView veiculoCategoria;
    VeiculoRequester requester;
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_veiculo);

        Intent intent = getIntent();
        final Veiculo veiculo = (Veiculo)intent.getSerializableExtra(ListaVeiculoActivity.VEICULO);
        setupViews(veiculo);

        if(!veiculo.getModelo().equals(Veiculo.NAO_ENCONTRADO)) {
            requester = new VeiculoRequester();
            if (requester.isConnected(this)) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mProgress.setVisibility(View.VISIBLE);
                            final Bitmap img = requester.getImage(veiculo.getImagem());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    veiculoImageView.setImageBitmap(img);
                                    mProgress.setVisibility(View.INVISIBLE);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.carro_desconhecido);
                veiculoImageView.setImageDrawable(drawable);
                Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.carro_desconhecido);
            veiculoImageView.setImageDrawable(drawable);
        }

    }

    private void setupViews(Veiculo veiculo) {
        veiculoModelo = (TextView) findViewById(R.id.txt_veiculo_modelo);
        veiculoModelo.setText(veiculo.getModelo());
        veiculoImageView = (ImageView) findViewById(R.id.veiculo_image_view);
        veiculoKmlivre = (TextView) findViewById(R.id.txt_veiculo_kmlivre);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        veiculoKmlivre.setText(""+formatter.format(veiculo.getKmlivre()));
        veiculoCidade = (TextView) findViewById(R.id.txt_veiculo_cidade);
        veiculoCidade.setText(veiculo.getCidade());
        veiculoMarca = (TextView) findViewById(R.id.txt_veiculo_marca);
        veiculoMarca.setText(veiculo.getMarca());
        veiculoCategoria = (TextView) findViewById(R.id.txt_veiculo_categoria);
        veiculoCategoria.setText(veiculo.getCategoria());
        mProgress = (ProgressBar) findViewById(R.id.carregando_veiculo);
        mProgress.setVisibility(View.INVISIBLE);
    }

}