package com.example.rodrigobento.questao3;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConsultaCep extends AppCompatActivity {

    private String cep;
    private TextView logradouro;
    private TextView bairro;
    private TextView cidade;
    private TextView campoCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cep);
        Intent i = getIntent();
        this.cep = i.getStringExtra("cep");
        // Pegando referencia aos componentes da Activity
        this.logradouro = (TextView) findViewById(R.id.logradouro);
        this.cidade = (TextView) findViewById(R.id.municipio);
        this.bairro = (TextView) findViewById(R.id.bairro);
        this.campoCep = (TextView) findViewById(R.id.textView2);
        // Atribuindo ao TextView o valor do CEP que será buscado no webService
        campoCep.setText(cep);
        // Instanciando a classe responsável por consumir o webService.
        ServicoCEP2 buscarCep = new ServicoCEP2(this, logradouro, bairro, cidade);
        // Executando
        buscarCep.execute(cep);
        // Pegando referencia ao Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // Removendo o Toolbar default e atribuindo o que foi criado no local
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate o menu, adiciona os items ao toolbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shared = menu.findItem(R.id.action_shared);
        ShareActionProvider mActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shared);
        // Caso queira adicionar o botão de voltar no Toolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Fetch and store ShareActionProvider
        return true;
    }
}
