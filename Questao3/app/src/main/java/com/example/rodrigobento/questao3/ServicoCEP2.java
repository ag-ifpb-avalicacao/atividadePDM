package com.example.rodrigobento.questao3;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServicoCEP2 extends AsyncTask<String, Void, String> {

    URL url = null;
    HttpURLConnection httpURLConnection = null;

    private Context contexto;

    private TextView tx1;
    private TextView tx2;
    private TextView tx3;


    public ServicoCEP2(Context ctx, TextView txt1, TextView txt2, TextView txt3){
        this.contexto = ctx;
        this.tx1 = txt1;
        this.tx2 = txt2;
        this.tx3 = txt3;
    }

    /*O método doInBackground é o responsável pelo o processamento pesado, pois ele é executado
    em uma Thread separada. Enquanto é processada a informação, a tela do usuário não ficará travada*/
    @Override
    protected String doInBackground(String... params) {
        StringBuilder result = null;
        int respCode = -1;
        try {
            // Cria o objeto URL concatendo a URL do serviço com o CEP passado
            url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep="+ params[0] +"&formato=json");
            // Abre uma conexão
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // Verifica se a conexão existe (não é nula), ficando nesse lop até que seja estabelecida uma conexão
            do {
                if (httpURLConnection != null) {
                    respCode = httpURLConnection.getResponseCode();
                    Log.i("RespCode", "" + respCode);
                }
            } while (respCode == -1);
            // Caso a conexão exista
            if (respCode == HttpURLConnection.HTTP_OK) {
                // Cria um objeto BufferedReader com o InputStream fornecido pela conexão
                BufferedReader br = new BufferedReader
                        (new InputStreamReader(httpURLConnection.getInputStream()));
                /*Constroi o objeto StringBuilder para concatenar toda a String advinda da conexão,
                serve posteriormente para criar o objeto JSON*/
                result = new StringBuilder();
                String line;
                // Lê a linha fornecida e adiciona ao objeto StringBuilder
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                // Fecha o Buffered
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                httpURLConnection = null;
            }
        }
        return (result != null) ? result.toString() : null;
    }

    /*O método que recebe o retorno do doInBackground e é chamado utilizando um Handler.
     Sua execução se dá na mesma Thread que a da interface gráfica*/
    @Override
    protected void onPostExecute(String s) {
        try {
            // Cria o objeto JSON com a String vinda do metodo doInBackground
            JSONObject object = new JSONObject(s);
            // Cria um objeto AlertDialog, para caso ocorra um erro
            AlertDialog.Builder construtor = new AlertDialog.Builder(contexto);
            AlertDialog alerta;
            // Erro de CEP não encontrado
            if(object.getInt("resultado") == 0){
                // Atribui os valores ao AlertDialog
                construtor.setTitle("CEP Invalido");
                construtor.setMessage("Informe outro CEP");
                construtor.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contexto.startActivity(new Intent(contexto, MainActivity.class));
                    }
                });
                alerta = construtor.create();
                // Exibe o AlertDialog
                alerta.show();
            } else {
                // Altera os campos da Activity consulta_cep para as informações advindas do CEP e WebService
                tx1.setText(object.getString("logradouro"));
                tx2.setText(object.getString("bairro"));
                tx3.setText(object.getString("cidade"));
            }
        }  catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
