package com.example.cadastroimoveis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cadastroimoveis.R;
import com.example.cadastroimoveis.activities.AddActivity;
import com.example.cadastroimoveis.activities.ConsultActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addClick(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void consultClick(View view) {
        Intent intent = new Intent(this, ConsultActivity.class);
        startActivity(intent);
    }
}
