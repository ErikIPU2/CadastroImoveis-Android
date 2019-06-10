package com.example.cadastroimoveis.imoveis;


import java.io.Serializable;
import java.util.Calendar;

public class Casa {
    private String proprietario;
    private String endereco;

    private double tamanho;

    private int anoConstrucao;

    public Casa(String proprietario, String endereco, double tamanho, int anoConstrucao) {
        this.proprietario = proprietario;
        this.endereco = endereco;
        this.tamanho = tamanho;
        this.anoConstrucao = anoConstrucao;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public int getAnoConstrucao() {
        return anoConstrucao;
    }

    public void setAnoConstrucao(int anoConstrucao) {
        this.anoConstrucao = anoConstrucao;
    }

    public double getImposto() {
        return this.tamanho * 7;
    }

}