package com.example.rodrigobento.questao1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ListView extends ListActivity {

    private Button botaoVoltar;
    private android.widget.ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        // Pegando a referencia ao ListView
        lv = (android.widget.ListView) findViewById(android.R.id.list);

        // Inserindo dados ao ArrayList utilizado para criar o ListView, dados que serão exibidos
        ArrayList<String> lista = new ArrayList<>();
        int cont = 1;
        for (int i = 0; i < 10; i++){
            String s = "Menu " + cont++;
            lista.add(s);
        }

        // Atribuindo os dados ao ListView, desde os TextView utilizados até o ArrayList
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, lista));

        // Pegando referencia ao botao
        botaoVoltar = (Button) findViewById(R.id.button);
        // Adicionando um Listener
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Metodo atribuido a um clique do usuário ao item mostrado na tela
    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, l.getAdapter().getItem(position).toString() + " selecionado",
                Toast.LENGTH_SHORT).show();
    }

}
