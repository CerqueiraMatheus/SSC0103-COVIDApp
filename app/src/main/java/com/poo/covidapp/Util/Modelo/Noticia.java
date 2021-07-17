package com.poo.covidapp.Util.Modelo;

public class Noticia {
    String titulo;
    String resumo;
    String publicadaPor;
    String url;

    public Noticia(String titulo, String resumo, String publicadaPor, String url) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.publicadaPor = publicadaPor;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getPublicadaPor() {
        return publicadaPor;
    }

    public String getUrl() {
        return url;
    }
}