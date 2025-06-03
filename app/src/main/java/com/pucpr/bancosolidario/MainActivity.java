package com.pucpr.bancosolidario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrar, btnVerDoacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referência dos botões
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnVerDoacoes = findViewById(R.id.btnVerDoacoes);

        // Ação para abrir tela de cadastro
        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
        });

        // Ação para abrir a lista de doações
        btnVerDoacoes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaDoacoesActivity.class);
            startActivity(intent);
        });
    }
}
