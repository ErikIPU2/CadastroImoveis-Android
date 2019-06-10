package com.example.cadastroimoveis.imoveis;

import java.util.Calendar;

public class Condominio extends Casa {
    public Condominio(String proprietario, String endereco, double tamanho, int anoConstrucao) {
        super(proprietario, endereco, tamanho, anoConstrucao);
    }

    @Override
    public double getImposto() {
        return this.getTamanho() * 9;
    }
}
