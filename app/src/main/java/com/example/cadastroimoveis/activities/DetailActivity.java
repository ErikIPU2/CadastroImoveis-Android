package com.example.cadastroimoveis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cadastroimoveis.R;
import com.example.cadastroimoveis.imoveis.Apartamento;
import com.example.cadastroimoveis.imoveis.Casa;
import com.example.cadastroimoveis.imoveis.Condominio;
import com.example.cadastroimoveis.util.GetImovelType;
import com.example.cadastroimoveis.util.SharedPreferencesSaver;

public class DetailActivity extends AppCompatActivity {

    private class ViewHolder {
        public TextView proprietarioText = findViewById(R.id.proprietario_detail_text);
        public TextView enderecoText = findViewById(R.id.endereco_detail_text);
        public TextView tamanhoText = findViewById(R.id.tamanho_detail_text);
        public TextView anoText = findViewById(R.id.ano_detail_text);
        public TextView andarText = findViewById(R.id.andar_detail_text);
        public TextView tipoText = findViewById(R.id.tipo_imovel_detail_text);
        public TextView impostoText = findViewById(R.id.imposto_detail_text);
    }

    private ViewHolder viewHolder;
    private Casa casa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.viewHolder = new ViewHolder();
        this.loadIntentData();
        this.update();
    }

    private void loadIntentData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int position = extras.getInt("position");
            casa = new SharedPreferencesSaver(this).loadData().get(position);
        }
    }

    private void update() {
        viewHolder.proprietarioText.setText(String.format(
                getString(R.string.nome_do_proprietario_layout),
                casa.getProprietario()
        ));

        viewHolder.enderecoText.setText(String.format(
                getString(R.string.endereco_layout),
                casa.getEndereco()
        ));

        viewHolder.tamanhoText.setText(String.format(
                getString(R.string.tamanho_layout),
                casa.getTamanho()
        ));

        viewHolder.anoText.setText(String.format(
                getString(R.string.ano_de_construcao_layout),
                casa.getAnoConstrucao()
        ));

        viewHolder.tipoText.setText(String.format(
                getString(R.string.tipo_do_imovel_layout),
                GetImovelType.getType(casa)
        ));

        viewHolder.impostoText.setText(String.format(
                getString(R.string.valor_do_imposto_layout),
                casa.getImposto()
        ));

        if (!GetImovelType.haveAndar(casa)) {
            viewHolder.andarText.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.andarText.setText(String.format(
                    getString(R.string.andar_layout),
                    ((Apartamento) casa).getAndar()
            ));
        }
    }
}
