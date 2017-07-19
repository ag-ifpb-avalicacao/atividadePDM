package com.example.rodrigobento.questao1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button tableLayout, linearLayout, relativeLayout, absoluteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Pegando referencia aos botoes
        tableLayout = (Button) findViewById(R.id.btn1);
        linearLayout = (Button) findViewById(R.id.btn4);
        relativeLayout = (Button) findViewById(R.id.btn3);
        absoluteLayout = (Button) findViewById(R.id.btn2);
        // Adicionando os Listeners
        tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TableLayout.class));
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListView.class));
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RelativeLayout.class));
            }
        });
        absoluteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AbsoluteLayout.class));
            }
        });
    }

}
