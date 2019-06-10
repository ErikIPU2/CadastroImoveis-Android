package com.example.cadastroimoveis.imoveis;

import java.util.Calendar;

public class Apartamento extends Condominio {
    private int andar;

    public Apartamento(String proprietario, String endereco, double tamanho, int anoConstrucao, int andar) {
        super(proprietario, endereco, tamanho, anoConstrucao);
        this.andar = andar;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    @Override
    public double getImposto() {
        return this.getTamanho() * 8.5;
    }
}
