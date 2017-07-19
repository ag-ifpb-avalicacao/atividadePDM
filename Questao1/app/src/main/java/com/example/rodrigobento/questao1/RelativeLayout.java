package com.example.rodrigobento.questao1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RelativeLayout extends AppCompatActivity {

    private Button botaoCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        // Pegando uma referencia ao botao
        botaoCancelar = (Button) findViewById(R.id.button6);
        // Adicionado um Listener
        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
