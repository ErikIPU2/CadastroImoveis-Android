package com.example.cadastroimoveis.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroimoveis.activities.EditListActivity;
import com.example.cadastroimoveis.imoveis.Casa;

import com.example.cadastroimoveis.R;

import java.util.ArrayList;

public class RecyclerEditAdapter extends RecyclerView.Adapter<RecyclerEditAdapter.ViewHolder> {
    private ArrayList<Casa> casas;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView proprietarioText;
        public TextView imovelTipoText;
        public Button editButton;

        public ViewHolder(View itemView) {
            super(itemView);
            proprietarioText = itemView.findViewById(R.id.proprietario_line_edit_view);
            imovelTipoText = itemView.findViewById(R.id.tipo_imovel_line_edit_view);
            editButton = itemView.findViewById(R.id.edit_button_line_edit_view);
        }
    }

    public RecyclerEditAdapter(ArrayList<Casa> casas, Context context) {
        this.casas = casas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.line_edit_view, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.proprietarioText.setText(this.casas.get(position).getProprietario());
        holder.imovelTipoText.setText(GetImovelType.getType(this.casas.get(position)));
        holder.editButton.setOnClickListener(view -> ((EditListActivity) this.context).showEditMenu(position));
    }

    @Override
    public int getItemCount() {
        return this.casas.size();
    }
}
