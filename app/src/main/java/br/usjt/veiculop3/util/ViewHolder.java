package br.usjt.veiculop3.util;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asbonato on 9/7/15.
 */
public class ViewHolder {
    private ImageView fotinhoVeiculo;
    private TextView modeloVeiculo, detalhesVeiculo;

    public ViewHolder(ImageView fotinhoVeiculo, TextView modeloVeiculo, TextView detalhesVeiculo) {
        this.fotinhoVeiculo = fotinhoVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.detalhesVeiculo = detalhesVeiculo;
    }

    public ImageView getFotinhoVeiculo() {
        return fotinhoVeiculo;
    }

    public void setFotinhoVeiculo(ImageView fotinhoVeiculo) {
        this.fotinhoVeiculo = fotinhoVeiculo;
    }

    public TextView getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(TextView modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public TextView getDetalhesVeiculo() {
        return detalhesVeiculo;
    }

    public void setDetalhesVeiculo(TextView detalhesVeiculo) {
        this.detalhesVeiculo = detalhesVeiculo;
    }
}
