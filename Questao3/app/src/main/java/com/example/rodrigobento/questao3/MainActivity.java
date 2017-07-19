package com.example.rodrigobento.questao3;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class MainActivity extends AppCompatActivity {

    private EditText valorCEP;
    private Button buscarCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Pegando referencia ao Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // Removendo o Toolbar default e atribuindo o que foi criado no local
        setSupportActionBar(myToolbar);
        // Pegando referencia aos componentes da Activity
        valorCEP = (EditText) findViewById(R.id.valorCEP);
        buscarCep = (Button) findViewById(R.id.button);
        // Adicionando uma mascara ao campo do CEP
        MaskEditTextChangedListener mascaraCep = new MaskEditTextChangedListener("#####-###", valorCEP);
        valorCEP.addTextChangedListener(mascaraCep);
        // Atribuindo um Listener ao botão de buscar o CEP
        buscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pega a conectividade do contexto o qual o metodo foi chamado
                ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(CONNECTIVITY_SERVICE);
                // Objeto netInfo que recebe as informacoes da Network
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                //Se o objeto for nulo ou nao tem conectividade retorna false
                if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())){
                    String cep = valorCEP.getText().toString();
                    // Caso o campo de CEP não seja informado
                    if(cep.equals("")){
                        String titulo = "Campo vazio";
                        String msg = "Informe o CEP antes de pressionar o botão";
                        AlertDialog alerta = construirAlerta(titulo, msg);
                        alerta.show();
                    } else {
                        Intent i = new Intent(MainActivity.this, ConsultaCep.class);
                        i.putExtra("cep", cep);
                        startActivity(i);
                    }
                } else {
                    String titulo = "Sem conexão com a internet";
                    String msg = "Por favor, conecte-se com alguma rede e tente novamente";
                    AlertDialog alerta = construirAlerta(titulo, msg);
                    alerta.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate o menu; adicionando os itens ao ToolBar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shared = menu.findItem(R.id.action_shared);
        ShareActionProvider mActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shared);
        return true;
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
