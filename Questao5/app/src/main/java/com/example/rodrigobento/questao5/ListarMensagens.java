package com.example.rodrigobento.questao5;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListarMensagens extends AsyncTask<String, Void, String>{

    private Context contexto;
    private String pessoa;
    private ListView listView;
    private URL url;

    public ListarMensagens(Context ctx, String pessoa, ListView lv){
        this.contexto = ctx;
        this.pessoa = pessoa;
        this.listView = lv;
    }

    /*O método doInBackground é o responsável pelo o processamento pesado, pois ele é executado
    em uma Thread separada. Enquanto é processada a informação, a tela do usuário não ficará travada*/
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder s = null;
        try {
            // Cria o objeto URL concatendo a URL do serviço com o caminho passado
            this.url = new URL(strings[0]);
            // Abre a URL
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.connect();
            int respCode = conexao.getResponseCode();
            // Verifica se a conexão existe (não é nula),
            // ficando nesse lop até que seja estabelecida uma conexão
            do {
                if (conexao != null) {
                    respCode = conexao.getResponseCode();
                }
            } while (respCode == -1);
            if(respCode == HttpURLConnection.HTTP_OK){
                // Cria um objeto BufferedReader com o InputStream fornecido pela conexão
                BufferedReader bf = new BufferedReader
                        (new InputStreamReader(conexao.getInputStream()));
                /*Constroi o objeto StringBuilder para concatenar toda a String advinda da conexão,
                serve posteriormente para criar o objeto JSON*/
                s = new StringBuilder();
                String linha;
                // Lê a linha fornecida e adiciona ao objeto StringBuilder
                while ((linha = bf.readLine()) != null){
                    s.append(linha);
                }
                // Fecha o BufferReader e a conexao
                bf.close();
                conexao.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString();
    }

    /*O método que recebe o retorno do doInBackground e é chamado utilizando um Handler.
     Sua execução se dá na mesma Thread que a da interface gráfica*/
    @Override
    protected void onPostExecute(String s) {
        try {
            // Cria o objeto JSON com a String vinda do metodo doInBackground
            JSONObject json = new JSONObject(s);
            // Cria um objeto do tipo JSONArray contendo os campos (nome, mensagem e verificador)
            JSONArray jsonArray = json.getJSONArray("chat");
            int tam = jsonArray.length();
            ArrayList<Chat> lista = new ArrayList<>();
            // Percorre a lista contido no objeto jsonArray
            for (int i = 0; i < tam; i++){
                // Cria um objeto JSON apartir do indice fornecido
                JSONObject auxiliar = jsonArray.getJSONObject(i);
                // Constroi o objeto chat, com os valores do JSON
                Chat m = new Chat(
                        auxiliar.getString("nome"),
                        auxiliar.getString("mensagem"),
                        auxiliar.getBoolean("verificador"));
                // Verificação necessária para atribuir o lado em que o balão será posicionado
                if(pessoa.equals(m.getNome())){
                    m.setVrf(true);
                } else {
                    m.setVrf(false);
                }
                // Adiciona a lista
                lista.add(m);
            }
            // Seta o Adapter
            listView.setAdapter(new ChatAdapter(contexto, lista));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
