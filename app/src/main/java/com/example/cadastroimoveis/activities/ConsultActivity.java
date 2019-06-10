package com.example.cadastroimoveis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.cadastroimoveis.R;
import com.example.cadastroimoveis.util.SharedPreferencesSaver;
import com.example.cadastroimoveis.util.RecyclerAdapter;

public class ConsultActivity extends AppCompatActivity {

    private class ViewHolder {
        public RecyclerView imoveisRecycler = findViewById(R.id.imoveis_recycler);
    }

    private ViewHolder viewHolder;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        this.viewHolder = new ViewHolder();
        this.viewHolder.imoveisRecycler.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.viewHolder.imoveisRecycler.setLayoutManager(this.layoutManager);

        this.adapter = new RecyclerAdapter(new SharedPreferencesSaver(this).loadData(), this);
        this.viewHolder.imoveisRecycler.setAdapter(this.adapter);
    }

    public void showItem(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
