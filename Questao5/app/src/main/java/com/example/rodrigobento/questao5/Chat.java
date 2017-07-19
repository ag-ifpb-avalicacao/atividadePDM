package com.example.rodrigobento.questao5;

import java.io.Serializable;

public class Chat implements Serializable{
    private static int id = 0;
    private String nome, mensagem;
    private boolean vrf;

    public Chat(String nome, String mensagem, boolean vrf){
        this.nome = nome;
        this.mensagem = mensagem;
        this.vrf = vrf;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Chat.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isVrf() {
        return vrf;
    }

    public void setVrf(boolean vrf) {
        this.vrf = vrf;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "nome='" + nome + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", vrf=" + vrf +
                '}';
    }
}
