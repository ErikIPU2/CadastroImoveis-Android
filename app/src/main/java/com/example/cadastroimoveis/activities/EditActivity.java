package com.example.cadastroimoveis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadastroimoveis.R;
import com.example.cadastroimoveis.imoveis.Apartamento;
import com.example.cadastroimoveis.imoveis.Casa;
import com.example.cadastroimoveis.imoveis.Comercial;
import com.example.cadastroimoveis.imoveis.Condominio;
import com.example.cadastroimoveis.util.GetImovelType;
import com.example.cadastroimoveis.util.SharedPreferencesSaver;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private class ViewHolder {
        public TextView proprietarioEdit = findViewById(R.id.edit_proprietario_edit);
        public TextView enderecoEdit = findViewById(R.id.edit_endereco_edit);
        public TextView tamanhoEdit = findViewById(R.id.edit_tamanho_edit);
        public TextView anoEdit = findViewById(R.id.edit_ano_edit);
        public TextView andarEdit = findViewById(R.id.edit_andar_edit);
    }


    private ViewHolder viewHolder;
    private SharedPreferencesSaver sharedPreferencesSaver;
    private int position;
    private Casa casa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.viewHolder = new ViewHolder();
        this.sharedPreferencesSaver = new SharedPreferencesSaver(this);

        this.loadIntentData();
        this.update();
    }

    public void onEditButtonClick(View view) {
        boolean error = this.checkInputs();
        if (!error) {
            ArrayList<Casa> casas = this.sharedPreferencesSaver.loadData();
            Casa tempCasa = casas.get(this.position);
            String proprietario;
            String endereco;
            double tamaho;
            int anoConstrucao;
            int andar;

            proprietario = String.valueOf(viewHolder.proprietarioEdit.getText());
            endereco = String.valueOf(viewHolder.enderecoEdit.getText());
            tamaho = Double.valueOf(String.valueOf(viewHolder.tamanhoEdit.getText()));
            anoConstrucao = Integer.valueOf(String.valueOf(viewHolder.anoEdit.getText()));

            tempCasa.setProprietario(proprietario);
            tempCasa.setEndereco(endereco);
            tempCasa.setTamanho(tamaho);
            tempCasa.setAnoConstrucao(anoConstrucao);

            if (GetImovelType.isComercial(tempCasa)) {
                andar = Integer.valueOf(String.valueOf(viewHolder.andarEdit.getText()));
                Comercial comercial = (Comercial) tempCasa;
                comercial.setAndar(andar);
            } else if (GetImovelType.isApartamento(tempCasa)) {
                andar = Integer.valueOf(String.valueOf(viewHolder.andarEdit.getText()));
                Apartamento apartamento = (Apartamento) tempCasa;
                apartamento.setAndar(andar);
            }
            this.sharedPreferencesSaver.saveData(casas);
            Toast.makeText(this, getString(R.string.alteracoes_realizadas), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadIntentData() {
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            int position = extras.getInt("position");
            this.position = position;
            this.casa = this.sharedPreferencesSaver.loadData().get(position);
        }
    }

    private void update() {
        if (!GetImovelType.haveAndar(this.casa)) {
            viewHolder.andarEdit.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.andarEdit.setVisibility(View.VISIBLE);
            viewHolder.andarEdit.setText(String.valueOf(((Apartamento) this.casa).getAndar()));
        }

        viewHolder.proprietarioEdit.setText(this.casa.getProprietario());
        viewHolder.enderecoEdit.setText(this.casa.getEndereco());
        viewHolder.tamanhoEdit.setText(String.valueOf(this.casa.getTamanho()));
        viewHolder.anoEdit.setText(String.valueOf(this.casa.getAnoConstrucao()));
    }

    private boolean checkInputs() {
        StringBuilder tempToastErrorMessage = new StringBuilder(getString(R.string.faltou_preenches_os_seguintes_campos));
        tempToastErrorMessage.append("\n");
        boolean haveError = false;
        if (String.valueOf(viewHolder.proprietarioEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.nome_do_proprietario));
            haveError = true;
        }
        if (String.valueOf(viewHolder.enderecoEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.endereco));
            haveError = true;
        }
        if (String.valueOf(viewHolder.tamanhoEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.tamanho_em_metros_quadrados));
            haveError = true;
        }
        if (String.valueOf(viewHolder.anoEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.ano_de_construcao));
            haveError = true;
        }
        if (viewHolder.andarEdit.getVisibility() == View.VISIBLE &&
            String.valueOf(viewHolder.andarEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.andar));
            haveError = true;
        }

        if (haveError) {
            Toast.makeText(this, tempToastErrorMessage.toString(), Toast.LENGTH_LONG).show();
        }
        return haveError;
    }
}
