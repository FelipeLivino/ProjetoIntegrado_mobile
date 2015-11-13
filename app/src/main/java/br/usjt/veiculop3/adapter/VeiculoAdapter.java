package br.usjt.veiculop3.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

import br.usjt.veiculop3.R;
import br.usjt.veiculop3.util.Util;
import br.usjt.veiculop3.util.ViewHolder;
import br.usjt.veiculop3.model.Veiculo;

/**
 * Created by asbonato on 9/6/15.
 */
public class VeiculoAdapter extends BaseAdapter implements SectionIndexer
{
    Activity context;
    Veiculo[] veiculos;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public VeiculoAdapter(Activity context, Veiculo[] veiculos){
        this.context = context;
        this.veiculos = veiculos;
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(veiculos);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(veiculos);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(veiculos);

    }
    @Override
    public int getCount() {
        return veiculos.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < veiculos.length)
            return veiculos[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_veiculo, parent, false);

            ImageView fotinhoVeiculo = (ImageView)view.findViewById(R.id.fotinhoVeiculoImageView);
            TextView modeloVeiculo = (TextView)view.findViewById(R.id.modeloVeiculoTextView);
            TextView detalhesVeiculo = (TextView)view.findViewById(R.id.detalhesVeiculoTextView);
            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(fotinhoVeiculo, modeloVeiculo, detalhesVeiculo));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        Drawable drawable = Util.getDrawable(context, veiculos[position].getMarca());
        holder.getFotinhoVeiculo().setImageDrawable(drawable);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        holder.getModeloVeiculo().setText(veiculos[position].getModelo());
        holder.getDetalhesVeiculo().setText(String.format("%s - %s", veiculos[position].getCidade(),
                formatter.format(veiculos[position].getKmlivre())));

        return view;
    }
//metodos da interface SectionIndexer


    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }
}
