package com.example.rodrigobento.questao5;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AdicionarMensagem extends AsyncTask<String, Void, Boolean> {

    private URL url;

    /*O método doInBackground é o responsável pelo o processamento pesado, pois ele é executado
    em uma Thread separada. Enquanto é processada a informação, a tela do usuário não ficará travada*/
    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            // Cria o objeto URL com a String passada
            this.url = new URL(strings[0]);
            // Abre a conexao
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Atribui para que ela seja uma requisicao do tipo GET
            connection.setRequestMethod("GET");
            connection.connect();
            // Busca uma resposta
            connection.getResponseCode();
            // Fecha a conexao
            connection.disconnect();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
