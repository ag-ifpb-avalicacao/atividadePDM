package com.example.rodrigobento.questao4;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class DownloadURL extends IntentService {

    private ResultReceiver rs;

    public DownloadURL() {
        super("DownloadURL");
    }

    // Inicia o serviço
    @Override
    protected void onHandleIntent(Intent intent) {
        // Busca e cria uma String com a URL especificada pelo usuário
        String link = intent.getStringExtra("url");
        // Pega a referencia do ResultReceiver.
        rs = intent.getParcelableExtra("receiver");
        // Cria um Bundle para adicionar os valores que serão chamados no metodo (onReceiveResult)
        Bundle b = new Bundle();
        // Adiciona a mensagem que será exibida no AlertDialog
        b.putString("inicio", "Download iniciado!");
        // Envia uma mensagem para que seja processado pelo ResultReceiver criado.
        rs.send(1, b);
        try {
            // Cria a URL pela String fornecida pelo usuario.
            URL url = new URL(link);
            // Cria a URI pela String fornecida, será responsavel por pegar o nome do arquivo.
            Uri u = Uri.parse(link);
            // Cria e abre uma conexão HTTP.
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            // Inicia o Download caso o retorno de getResponseCode seja igual ao atributo estatico da clase HttupUrlConnection, ou seja, o valor 200.
            if (conexao .getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Cria um inputStream da conexao
                InputStream streamEntrada =  new BufferedInputStream(conexao.getInputStream());
                int tamanhoArquivo = conexao.getContentLength();
                // Nome do arquivo que será utilizado para sua criação.
                String fileName = u.getLastPathSegment();

                // Pega referencia a pasta de Downloads do Android
                File f = new File(Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS).toString());

                // Caso não exista, criar.
                if(!f.exists()){
                    f.mkdirs();
                }

                // Cria um arquivo, com o caminho apontando para a pasta de Download do Android e com o nome do arquivo.
                File arquivo = new File(f.getPath() + "/" + URLDecoder.decode(fileName, "ISO8859_1"));

                // Caso o arquivo não existir, criar-lo.
                if (!arquivo.exists()) {
                    arquivo.createNewFile();
                }

                // Cria um objeto OutputStream, que tem por função salvar os bytes do arquivo.
                OutputStream s = new FileOutputStream(arquivo);

                int tam;
                byte[] arq = new byte[1024];
                // Escrever os bytes no arquivo especificado
                while((tam = streamEntrada.read(arq)) != -1){
                    s.write(arq, 0, tam);
                }

                // Fechando o InputStream e o OutputStream
                s.flush();
                streamEntrada.close();
                s.close();

                // Informando ao ResultReceiver que o Download foi concluido com sucesso e pedindo para que ele informe ao usuário.
                b.putString("fim", "Download concluido!");
                rs.send(2, b);
            } else {
                b.putString("erro", "Ocorreu um erro no processamento do download!");
                rs.send(3, b);
            }

        } catch (MalformedURLException e) {
            b.putString("erro", "Ocorreu um erro no processamento do download!");
            rs.send(3, b);
        } catch (IOException e) {
            b.putString("erro", "Ocorreu um erro em salvar o arquivo");
            rs.send(3, b);
        }
    }
}
