package com.example.cadastroimoveis.util;

import com.example.cadastroimoveis.imoveis.Apartamento;
import com.example.cadastroimoveis.imoveis.Casa;
import com.example.cadastroimoveis.imoveis.Comercial;
import com.example.cadastroimoveis.imoveis.Condominio;

public class GetImovelType {
    public static String getType(Casa casa) {
        if (casa instanceof Comercial) {
            return "Imovel Comercial";
        } else if (casa instanceof Apartamento) {
            return "Apartamento";
        } else if (casa instanceof Condominio) {
            return "Casa em Condominio";
        } else {
            return "Casa";
        }
    }

    public static boolean haveAndar(Casa casa) {
        return isApartamento(casa) || isComercial(casa);
    }

    public static boolean isComercial(Casa casa) {
        return casa instanceof Comercial;
    }

    public static boolean isApartamento(Casa casa) {
        return casa instanceof Apartamento;
    }

    public static boolean isCondominio(Casa casa) {
        return casa instanceof Condominio;
    }

    public static boolean haveAndar(String s) {
        return isApartamento(s) || isComercial(s);
    }

    public static boolean isComercial(String s) {
        return s.equals("Imovel Comercial");
    }

    public static boolean isApartamento(String s) {
        return s.equals("Apartamento");
    }

    public static boolean isCondominio(String s) {
        return s.equals("Casa em Condominio");
    }
}
