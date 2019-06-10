package com.example.cadastroimoveis.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroimoveis.activities.ConsultActivity;
import com.example.cadastroimoveis.R;
import com.example.cadastroimoveis.imoveis.Casa;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Casa> casas;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView proprietarioText;
        public TextView imovelTipoText;
        public Button verButton;

        public ViewHolder(View itemView) {
            super(itemView);
            proprietarioText = itemView.findViewById(R.id.proprietario_line_view);
            imovelTipoText = itemView.findViewById(R.id.tipo_imovel_line_view);
            verButton = itemView.findViewById(R.id.ver_button_line_view);
        }
    }

    public RecyclerAdapter(ArrayList<Casa> casas, Context context) {
        this.casas = casas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.line_view, parent, false));
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, final int position) {
        holder.proprietarioText.setText(this.casas.get(position).getProprietario());
        holder.imovelTipoText.setText(GetImovelType.getType(this.casas.get(position)));
        holder.verButton.setOnClickListener(view -> ((ConsultActivity) this.context).showItem(position));
    }

    @Override
    public int getItemCount() {
        return this.casas.size();
    }

}