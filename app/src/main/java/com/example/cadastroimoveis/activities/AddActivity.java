package com.example.cadastroimoveis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cadastroimoveis.R;
import com.example.cadastroimoveis.imoveis.Apartamento;
import com.example.cadastroimoveis.imoveis.Casa;
import com.example.cadastroimoveis.imoveis.Comercial;
import com.example.cadastroimoveis.imoveis.Condominio;
import com.example.cadastroimoveis.util.SharedPreferencesSaver;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private class ViewHolder {
        public RadioGroup imovelTypeRGroup = findViewById(R.id.imovel_type_rGroup);

        public RadioButton casaRadio = findViewById(R.id.casa_radio);
        public RadioButton condominioRadio = findViewById(R.id.condominio_radio);
        public RadioButton apartamento = findViewById(R.id.apartamento_radio);
        public RadioButton comercial = findViewById(R.id.comercial_radio);

        public EditText proprietarioEdit = findViewById(R.id.proprietario_edit);
        public EditText enderecoEdit = findViewById(R.id.endereco_edit);
        public EditText tamanhoEdit = findViewById(R.id.tamanho_edit);
        public EditText anoConstrucaoEdit = findViewById(R.id.ano_construcao_edit);
        public EditText andarEdit = findViewById(R.id.andar_edit);

        public Button addButton = findViewById(R.id.add_button);
    }

    private ViewHolder viewHolder;
    private SharedPreferencesSaver sharedPreferencesSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.viewHolder = new ViewHolder();
        this.sharedPreferencesSaver = new SharedPreferencesSaver(this);
        this.update();
    }

    public void radioImovelTypeClick(View view) {
        this.update();
    }

    public void addButtonClick(View view) {
        boolean error = this.checkInputs();
        if (!error) {
            Casa casa;
            String proprietario;
            String endereco;
            double tamanho;
            int anoConstrucao;
            int andar;

            proprietario = String.valueOf(this.viewHolder.proprietarioEdit.getText());
            endereco = String.valueOf(this.viewHolder.enderecoEdit.getText());
            tamanho = Double.valueOf(String.valueOf(this.viewHolder.tamanhoEdit.getText()));
            anoConstrucao = Integer.valueOf(String.valueOf(this.viewHolder.anoConstrucaoEdit.getText()));
            int selectedRadio = this.viewHolder.imovelTypeRGroup.getCheckedRadioButtonId();

            if (selectedRadio == R.id.casa_radio) {
                casa = new Casa(proprietario, endereco, tamanho, anoConstrucao);
            } else if (selectedRadio == R.id.condominio_radio) {
                casa = new Condominio(proprietario, endereco, tamanho, anoConstrucao);
            } else {
                andar = Integer.valueOf(String.valueOf(this.viewHolder.andarEdit.getText()));
                if (selectedRadio == R.id.apartamento_radio) {
                    casa = new Apartamento(proprietario, endereco, tamanho, anoConstrucao, andar);
                } else if (selectedRadio == R.id.comercial_radio) {
                    casa = new Comercial(proprietario, endereco, tamanho, anoConstrucao, andar);
                } else {
                    casa = null;
                }
            }

            if (casa != null) {
                ArrayList<Casa> casas = this.sharedPreferencesSaver.loadData();
                casas.add(casa);
                this.sharedPreferencesSaver.saveData(casas);
                Toast.makeText(this, "Imovel Adicionado com sucesso", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void update() {
        int checkedId = this.viewHolder.imovelTypeRGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.apartamento_radio || checkedId == R.id.comercial_radio) {
            this.viewHolder.andarEdit.setVisibility(View.VISIBLE);
        } else {
            this.viewHolder.andarEdit.setVisibility(View.INVISIBLE);
        }
    }

    private boolean checkInputs() {
        StringBuilder tempToastErrorMessage = new StringBuilder(getString(R.string.faltou_preenches_os_seguintes_campos));
        tempToastErrorMessage.append("\n");
        boolean haveError = false;
        if (this.viewHolder.imovelTypeRGroup.getCheckedRadioButtonId() == -1) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.selecionar_o_tipo_do_imovel));
        }
        if (String.valueOf(this.viewHolder.proprietarioEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.nome_do_proprietario));
            haveError = true;
        }
        if (String.valueOf(this.viewHolder.enderecoEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.endereco));
            haveError = true;
        }
        if (String.valueOf(this.viewHolder.tamanhoEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.tamanho_em_metros_quadrados));
            haveError = true;
        }
        if (String.valueOf(this.viewHolder.anoConstrucaoEdit.getText()).equals("")) {
            tempToastErrorMessage.append("\n");
            tempToastErrorMessage.append(getString(R.string.ano_de_construcao));
            haveError = true;
        }
        if (String.valueOf(this.viewHolder.andarEdit.getText()).equals("") && this.viewHolder.andarEdit.getVisibility() == View.VISIBLE) {
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
