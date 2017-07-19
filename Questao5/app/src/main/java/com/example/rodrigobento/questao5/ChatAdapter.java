package com.example.rodrigobento.questao5;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

// Classe responsavel para criar uma Adapter personalizado
public class ChatAdapter extends BaseAdapter {

    private Context contexto;
    private ArrayList<Chat> lista;

    public ChatAdapter(Context ctx, ArrayList<Chat> list){
        this.contexto = ctx;
        this.lista = list;

    }

    // Retorna o numero de elementos que a lista possui
    @Override
    public int getCount() {
        return lista.size();
    }

    // Retorna o item da lista, necessitando informar o indice
    @Override
    public Chat getItem(int i) {
        return lista.get(i);
    }

    // Retorna o id do item da lista, necessitando informar o indice
    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    // Metodo responsável por atribuir elementos ao LisView, ou seja, o personalizando
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Chat c = getItem(i);

        String msg = c.getMensagem();

        LayoutInflater inflar = (LayoutInflater) contexto.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View visao = inflar.inflate(R.layout.balao, null);

        RelativeLayout lay = (RelativeLayout) visao.findViewById(R.id.balaoEsquerdo);
        LinearLayout parent = (LinearLayout) visao.findViewById(R.id.pai);

        TextView t = (TextView) visao.findViewById(R.id.textoChat);
        t.setText(msg);

        if(c.isVrf()){
            parent.setGravity(Gravity.RIGHT);

        } else {
            t.setBackgroundResource(R.drawable.bubble_yellow);
            parent.setGravity(Gravity.LEFT);
        }
        return visao;
    }
}
