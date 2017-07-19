package com.example.rodrigobento.questao2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText numAdultos;
    private EditText numCriancas;
    private EditText bolo;
    private EditText doce;
    private EditText salgado;
    private EditText refri;
    private Button calcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Pegando referencia aos componentes da Activity
        this.numAdultos = (EditText) findViewById(R.id.numAdultos);
        this.numCriancas = (EditText) findViewById(R.id.numCriancas);
        this.bolo = (EditText) findViewById(R.id.bolo);
        this.doce = (EditText) findViewById(R.id.doces);
        this.salgado = (EditText) findViewById(R.id.salgados);
        this.refri = (EditText) findViewById(R.id.refrigerante);
        this.calcular = (Button) findViewById(R.id.botaoCalcular);
        // Colocando os componentes que serão atribuidos os valores para invisivel
        bolo.setEnabled(false);
        doce.setEnabled(false);
        salgado.setEnabled(false);
        refri.setEnabled(false);
        // Atribuindo um Listener ao botão
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificar se os valores foram atribuidos aos campos
                if(numAdultos.getText().toString().equals("") &&
                        numCriancas.getText().toString().equals("")){
                    // Pede para o metodo criar um AlertDialog e mostrar na tela
                    AlertDialog alerta = criarAlerta("Informe os valores",
                            "Campos vazios, informe os valores desejados");
                    alerta.show();
                } else {
                    int adultos;
                    int criancas;
                    // Caso o numero de adultos não seja especificado, atribui-se a 0 e é feito o calculo
                    if (numAdultos.getText().toString().equals("")){
                        adultos = 0;
                        // Busca os valores do EditText
                        criancas = Integer.parseInt(numCriancas.getText().toString());
                        // Delega o calculo para o metodo
                        contaFinal(adultos, criancas);
                        // Caso o numero de crianças não seja especificado, atribui-se a 0 e é feito o calculo
                    } else if (numCriancas.getText().toString().equals("")){
                        criancas = 0;
                        // Busca os valores do EditText
                        adultos = Integer.parseInt(numAdultos.getText().toString());
                        // Delega o calculo para o metodo
                        contaFinal(adultos, criancas);
                    } else {
                        // Busca os valores do EditText
                        adultos = Integer.parseInt(numAdultos.getText().toString());
                        criancas = Integer.parseInt(numCriancas.getText().toString());
                        // Delega o calculo para o metodo
                        contaFinal(adultos, criancas);
                    }
                }
            }
        });
    }

    // Metodo responsavel para efetuar o calculo pedido e modificando os EditText
    private void contaFinal(int adultos, int criancas){
        // Calculo dos valores em KG e arredondando para o mais proximo
        double valorBolo = Math.round((adultos * 0.6) + (criancas * 0.4));
        double valorDoces = Math.round((adultos * 8) + (criancas * 6));
        double valorSalgados = Math.round((adultos * 6) + (criancas * 4));
        double valorRefri = Math.round((adultos * 0.6) + (criancas * 0.5));
        // Modificando os EditText com os valores ja calculados
        bolo.setText(String.valueOf(valorBolo));
        doce.setText(String.valueOf(valorDoces));
        salgado.setText(String.valueOf(valorSalgados));
        refri.setText(String.valueOf(valorRefri));
    }

    // Metodo responsavel por criar um alerta, necessitando apenas do Titulo e da Mensagem que será mostrada em tela
    private AlertDialog criarAlerta(String titulo, String mensagem){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(titulo);
        b.setMessage(mensagem);
        b.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alerta = b.create();
        return alerta;
    }
}
