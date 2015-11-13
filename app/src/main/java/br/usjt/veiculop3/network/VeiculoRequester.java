package br.usjt.veiculop3.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.usjt.veiculop3.model.Veiculo;

/**
 * Created by asbonato on 2/6/15.
 * Vai fazer a requisição na API REST
 */
public class VeiculoRequester {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Veiculo> get(String url, String pMarca, String pModelo, String pCidade) throws IOException {

        ArrayList<Veiculo> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormEncodingBuilder()
                .add("cidade", pCidade)
                .add("marca", pMarca)
                .add("modelo", pModelo)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        try {
              Log.v("VeiculoRequester", jsonStr);
              JSONArray root = new JSONArray(jsonStr);
              JSONObject item = null;
              for (int i = 0; i < root.length(); i++) {
                  item = (JSONObject) root.get(i);

                  String modelo = item.getString("modelo");
                  String imagem = item.getString("imagem");
                  double kmlivre = item.getDouble("vlrTarifaLivre");
                  String cidade = item.getString("cidade");
                  String marca = item.getString("marca");
                  String categoria = item.getString("categoria");

                  lista.add(new Veiculo(cidade, modelo, marca, categoria, imagem, kmlivre));
              }

        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if(lista.size() == 0)
                lista.add(new Veiculo(pCidade, Veiculo.NAO_ENCONTRADO, pMarca, Veiculo.NAO_ENCONTRADO, "carro_desconhecido", 0.0));
            //Log.v("VeiculoRequester", jsonStr);
        }
        return lista;
    }
    public Bitmap getImage(String url) throws IOException {

        Bitmap img = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
