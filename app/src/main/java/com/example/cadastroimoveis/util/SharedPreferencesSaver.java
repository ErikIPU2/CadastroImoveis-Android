package com.example.cadastroimoveis.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cadastroimoveis.imoveis.Apartamento;
import com.example.cadastroimoveis.imoveis.Casa;
import com.example.cadastroimoveis.imoveis.Comercial;
import com.example.cadastroimoveis.imoveis.Condominio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferencesSaver {
    SharedPreferences sharedPreferences;
    private final String NAME = "imoveis";

    public SharedPreferencesSaver(Context context) {
        this.sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
    }

    public void saveData(ArrayList<Casa> casas) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(this.NAME, this.arrayListToJson(casas));
        editor.apply();
    }

    public ArrayList<Casa> loadData() {
        String json = this.sharedPreferences.getString(this.NAME, null);
//        Type type = new TypeToken<ArrayList<Casa>>() {}.getType();
//        ArrayList<Casa> casas = this.gson.fromJson(json, type);
        ArrayList<Casa> casas = this.jsonToArrayList(json);

        return casas;
    }

    private ArrayList<Casa> jsonToArrayList(String json) {
        ArrayList<Casa> casas = new ArrayList<>();
        if (json == null) {
            return casas;
        }
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(json);
            for (int i = 0; i < jsonArray.size(); i++) {
                casas.add(this.loadObject((JSONObject) jsonArray.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return casas;
    }

    private Casa loadObject(JSONObject jsonObject) {
        String proprietario, endereco;
        double tamanho;
        int anoConstrucao, andar;
        String type = (String) jsonObject.get("type");

        proprietario = (String) jsonObject.get("proprietario");
        endereco = (String) jsonObject.get("endereco");

        tamanho = Double.parseDouble(String.valueOf(jsonObject.get("tamanho")));
        anoConstrucao = Integer.parseInt(String.valueOf(jsonObject.get("anoConstrucao")));

        if (GetImovelType.isComercial(type)) {
            andar = Integer.parseInt(String.valueOf(jsonObject.get("andar")));
            return new Comercial(proprietario, endereco, tamanho, anoConstrucao, andar);
        } else if (GetImovelType.isApartamento(type)) {
            andar = Integer.parseInt(String.valueOf(jsonObject.get("andar")));
            return new Apartamento(proprietario, endereco,  tamanho,  anoConstrucao,  andar);
        } else if (GetImovelType.isCondominio(type)) {
            return new Condominio(proprietario, endereco, tamanho, anoConstrucao);
        } else {
            return new Casa(proprietario, endereco, tamanho, anoConstrucao);
        }
    }

    private String arrayListToJson(ArrayList<Casa> casas) {
        JSONArray jsonArray = new JSONArray();
        for (Casa casa : casas) {
            jsonArray.add(this.gerateJsonObject(casa));
        }
        return jsonArray.toString();
    }

    private JSONObject gerateJsonObject(Casa casa) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proprietario", casa.getProprietario());
        jsonObject.put("endereco", casa.getEndereco());
        jsonObject.put("tamanho", casa.getTamanho());
        jsonObject.put("anoConstrucao", casa.getAnoConstrucao());
        if (GetImovelType.haveAndar(casa)) {
            jsonObject.put("andar", ((Apartamento) casa).getAndar());
        }
        jsonObject.put("type", GetImovelType.getType(casa));
        return jsonObject;
    }
}