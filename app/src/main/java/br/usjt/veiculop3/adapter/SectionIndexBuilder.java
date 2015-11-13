package br.usjt.veiculop3.adapter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

import br.usjt.veiculop3.model.Veiculo;

/**
 * Created by asbonato on 9/7/15.
 */
public class SectionIndexBuilder {
    //cria um array de cabecalhos unicos de secao; os dados devem estar ordenados por modelo
    public static Object[] BuildSectionHeaders(Veiculo[] veiculos)
    {
        ArrayList<String> results = new ArrayList<>();
        TreeSet<String> used    = new TreeSet<>();
        if(veiculos != null) {
            for (Veiculo item : veiculos) {
                String letter = item.getModelo().substring(0, 1);

                if (!used.contains(letter))
                    results.add(letter);

                used.add(letter);
            }
        }
        return results.toArray(new Object[0]);
    }

    //cria um mapa para responder: posicao --> secao de dados ordenados pelo modelo
    public static Hashtable<Integer, Integer> BuildSectionForPositionMap(Veiculo[] veiculos)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(veiculos != null) {
            for (int i = 0; i < veiculos.length; i++) {
                String letter = veiculos[i].getModelo().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                }

                results.put(i, section);
            }
        }
        return results;
    }

    //cria um mapa para responder: secao --> posicao de dados ordenados pelo modelo
    public static Hashtable<Integer, Integer> BuildPositionForSectionMap(Veiculo[] veiculos)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(veiculos != null) {
            for (int i = 0; i < veiculos.length; i++) {
                String letter = veiculos[i].getModelo().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                    results.put(section, i);
                }
            }
        }
        return results;
    }
}