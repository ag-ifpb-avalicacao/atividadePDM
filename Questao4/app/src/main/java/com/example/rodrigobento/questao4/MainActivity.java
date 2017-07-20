package com.example.rodrigobento.questao4;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ResultReceiverListener rs;
    private Button botao;
    private EditText texto;
    private AlertDialog a;
    private static final int INICIO_DOWNLOAD = 1;
    private static final int FIM_DOWNLOAD = 2;
    private static final int ERRO_DOWNLOAD = 3;


    // Verifica se o dispositivo permite a leitura e escrita
    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    // Solicita ao usuário se ele decide ou não a permissão
    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Verifica e solicita a permissão
        if (shouldAskPermissions()) {
            askPermissions();
        }
        // Busca a referencia aos componentes da Activity (Texto e Botão).
        texto = (EditText) findViewById(R.id.textView);
        botao = (Button) findViewById(R.id.download);
        // Adiciona um Listener ao botão
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("URL", "=" + texto.getText().toString());
                // Pega a conectividade do contexto o qual o metodo foi chamado
                ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(CONNECTIVITY_SERVICE);
                // Objeto netInfo que recebe as informacoes da Network
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                //Se o objeto for nulo ou nao tem conectividade retorna false
                if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())){
                    // Se o campo estiver preenchido, iniciar o serviço
                    String textoURL = texto.getText().toString();
                    if(textoURL!=null && !textoURL.equals("")){
                        // Cria uma intenção para chamar o serviço de Download
                        Intent i = new Intent(MainActivity.this, DownloadURL.class);
                        // Cria o ResultReceiver, que tem por função atualiza a tela apartir de alguns pontos do serviço (Intent Service).
                        rs = new ResultReceiverListener(new Handler());
                        // Adiciona a URL escrita pelo usuário e o ResultReceiver  a intenção
                        i.putExtra("url", textoURL);
                        i.putExtra("receiver", rs);
                        // Inicia o serviço
                        startService(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Informe a URL", Toast.LENGTH_LONG).show();
                    }
                } else {
                    alertaModificado("Sem conexão com a internet", "Por favor, conecte-se a rede e tente novamente");
                }

            }
        });
    }


    // Classe que tem por função atualizar a Activity apartir de alguns pontos do serviço
    private class ResultReceiverListener extends ResultReceiver {

        public ResultReceiverListener(Handler handler) {
            super(handler);
        }


        // Metodo responsavel por receber a chamada (send) e fazer algo.
        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData) {
            // Verifica se o resultCode existe e atribui uma função. Geralmente será criado e mostrado um Alerta
            switch (resultCode){
                // Quando o download iniciar, fazer com que o botão de download desapareça e seja mostrado um Alerta ao usuário.
                case INICIO_DOWNLOAD:
                    texto.setEnabled(false);
                    botao.setEnabled(false);
                    a = criarAlerta(resultData.getString("inicio"), "Aguarde enquanto o download é feito...");
                    a.show();
                    break;
                // Caso o download seja concluido, reativar o botão de download e mostrar ao usuário que o download foi concluido.
                case FIM_DOWNLOAD:
                    texto.setEnabled(true);
                    texto.setText("");
                    botao.setEnabled(true);
                    a.dismiss();
                    alertaModificado(resultData.getString("fim"), "Verifique o arquivo na pasta de download");
                    break;
                case ERRO_DOWNLOAD:
                    texto.setEnabled(true);
                    texto.setText("");
                    botao.setEnabled(true);
                    a.dismiss();
                    alertaModificado(resultData.getString("erro"), "Tente novamente...");
                    break;
            }
            super.onReceiveResult(resultCode, resultData);
        }
    }

    // Metodo responsavel por criar o alerta que será mostrado ao usuário, recebendo o titulo e a mensagem que será mostrada
    private AlertDialog criarAlerta(String titulo, String mensagem){
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle(titulo);
        b.setMessage(mensagem);
        return b.create();
    }

    // Cria um alerta diferente ao do metodo acima
    private void alertaModificado(String titulo, String mensagem){
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle(titulo);
        b.setMessage(mensagem);
        b.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        b.show();
    }


}
