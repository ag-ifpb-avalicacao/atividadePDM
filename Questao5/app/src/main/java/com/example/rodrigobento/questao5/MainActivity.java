package com.example.rodrigobento.questao5;

import android.content.Intent;
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
                Intent i = new Intent(getApplicationContext(), GerenciadorChat.class);
                i.putExtra("pessoa", "Pessoa 1");
                startActivity(i);
            }
        });
        botao2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), GerenciadorChat.class);
                i.putExtra("pessoa", "Pessoa 2");
                startActivity(i);
            }
        });
    }
}
