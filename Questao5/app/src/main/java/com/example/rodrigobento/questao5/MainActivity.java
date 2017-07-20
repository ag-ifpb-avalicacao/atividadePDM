package com.example.rodrigobento.questao5;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botao1;
    private Button botao2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Pega referencia aos componentes da Activity
        botao1 = (Button) findViewById(R.id.pessoa1);
        botao2 = (Button) findViewById(R.id.pessoa2);
        // Adiciona Listeners aos botões
        botao1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Pega a conectividade do contexto o qual o metodo foi chamado
                ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(CONNECTIVITY_SERVICE);
                // Objeto netInfo que recebe as informacoes da Network
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                //Se o objeto for nulo ou nao tem conectividade retorna false
                if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())){
                    Intent i = new Intent(getApplicationContext(), GerenciadorChat.class);
                    i.putExtra("pessoa", "Pessoa 1");
                    startActivity(i);
                } else {
                    String titulo = "Sem conexão com a internet";
                    String msg = "Por favor, conecte-se com alguma rede e tente novamente";
                    AlertDialog alerta = construirAlerta(titulo, msg);
                    alerta.show();
                }
            }
        });

        botao2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Pega a conectividade do contexto o qual o metodo foi chamado
                ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(CONNECTIVITY_SERVICE);
                // Objeto netInfo que recebe as informacoes da Network
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                //Se o objeto for nulo ou nao tem conectividade retorna false
                if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())){
                    Intent i = new Intent(getApplicationContext(), GerenciadorChat.class);
                    i.putExtra("pessoa", "Pessoa 2");
                    startActivity(i);
                } else {
                    String titulo = "Sem conexão com a internet";
                    String msg = "Por favor, conecte-se com alguma rede e tente novamente";
                    AlertDialog alerta = construirAlerta(titulo, msg);
                    alerta.show();
                }
            }
        });
    }

    // Metodo responsavel por criar o AlertDialog, recebendo o titulo e a mensagem que será mostrada em tela.
    private AlertDialog construirAlerta(String titulo, String mensagem){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(titulo);
        b.setMessage(mensagem);
        b.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alerta = b.create();
        return alerta;
    }
}
