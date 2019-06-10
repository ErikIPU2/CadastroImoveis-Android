package com.example.cadastroimoveis.imoveis;

import java.util.Calendar;

public class Comercial extends Apartamento {
    public Comercial(String proprietario, String endereco, double tamanho, int anoConstrucao, int andar) {
        super(proprietario, endereco, tamanho, anoConstrucao, andar);
    }

    @Override
    public double getImposto() {
        return this.getTamanho() * 10.7;
    }
}
