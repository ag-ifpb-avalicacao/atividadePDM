package com.example.rodrigobento.questao1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TableLayout extends AppCompatActivity {

    private Button botaoSalvar, botaoCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        // Pegando uma referencia aos botões da Activity TableLayout
        botaoSalvar = (Button) findViewById(R.id.btnSalvar);
        botaoCancelar = (Button) findViewById(R.id.btnCancelar);
        // Definindo um ouvinte para os botões
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt1 = (EditText) findViewById(R.id.nome);
                EditText edt2 = (EditText) findViewById(R.id.cpf);
                edt1.setText("");
                edt2.setText("");
                Toast.makeText(TableLayout.this, "Compra finalizada com sucesso", Toast.LENGTH_SHORT).show();
                Toast.makeText(TableLayout.this, "Voltando para a tela de Layouts", Toast.LENGTH_LONG).show();
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 3000);
            }
        });
        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
