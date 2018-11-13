package com.example.fabricio.lembretes;

public class Lembrete {

    String titulo;
    String conteudo;

    public Lembrete(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return String.format("#\n%s\n%s", titulo, conteudo);
    }
}
