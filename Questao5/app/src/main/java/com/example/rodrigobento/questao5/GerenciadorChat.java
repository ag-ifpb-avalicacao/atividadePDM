package com.example.rodrigobento.questao5;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class GerenciadorChat extends ListActivity {

    private String pessoa;
    private ListView l;
    private ImageButton b;
    private EditText mensagem;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciador_chat);
        Intent i = getIntent();
        // String para saber se quem esta navegando na Activity é a pessoa 1 ou 2.
        pessoa = i.getStringExtra("pessoa");
        // Pegando referencia aos componentes da Activity
        txt = (TextView) findViewById(R.id.textViewPessoa);
        b = (ImageButton) findViewById(R.id.botaoEnviar);
        l = (ListView) findViewById(android.R.id.list);
        mensagem = (EditText) findViewById(R.id.mensagem);
        txt.setText(pessoa);
        listar();
        // Adiciona um Listener ao botão de envio de mensagem
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cria a String advindo do EditText
                String msg = mensagem.getText().toString();
                // Troca os espaços pelo sinal de + para salvar no bd.
                String msgFinal = msg.replace(" ", "+");
                String auxiliar = pessoa.replace(" ", "+");
                // Cria a String responsavel por criar a URL, concatenando os valores para uma requisição GET
                String urlParams = "https://peaceful-shore-40477.herokuapp.com/add?nome="+auxiliar+"&mensagem="+msgFinal;
                // Instancia e executa a AsynTask responsável por salvar a mensagem no servidor
                AdicionarMensagem add = new AdicionarMensagem();
                add.execute(urlParams);
                mensagem.setText("");
                listar();
            }
        });
    }

    // Metodo responsavel para listar todas as mensagens salvas no Servidor.
    protected void listar(){
        // Instancia e executa a AsynTask responsável por listar as mensagens no servidor
        ListarMensagens add = new ListarMensagens(this, pessoa, l);
        add.execute("https://peaceful-shore-40477.herokuapp.com/list");
    }
}

