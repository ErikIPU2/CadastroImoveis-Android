package com.example.cadastroimoveis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.cadastroimoveis.R;
import com.example.cadastroimoveis.util.RecyclerEditAdapter;
import com.example.cadastroimoveis.util.SharedPreferencesSaver;

public class EditListActivity extends AppCompatActivity {

    private class ViewHolder {
        public RecyclerView imoveisEditRecycler = findViewById(R.id.imoveis_edit_recycler);
    }

    private ViewHolder viewHolder;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        this.viewHolder = new ViewHolder();
        this.viewHolder.imoveisEditRecycler.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.viewHolder.imoveisEditRecycler.setLayoutManager(layoutManager);

        this.adapter = new RecyclerEditAdapter(new SharedPreferencesSaver(this).loadData(), this);
        this.viewHolder.imoveisEditRecycler.setAdapter(this.adapter);
    }

    public void showEditMenu(int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
