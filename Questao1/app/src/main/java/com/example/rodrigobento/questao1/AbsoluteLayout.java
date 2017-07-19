package com.example.rodrigobento.questao1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class AbsoluteLayout extends AppCompatActivity {

    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_layout);
        botaoVoltar = (Button) findViewById(R.id.botaoVoltar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
                if(cb.isChecked()){
                    finish();
                } else {
                    Toast.makeText(AbsoluteLayout.this, "Para voltar selecione o CheckBox", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
