package com.pucpr.bancosolidario;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    EditText etNomeItem, etDescricao, etQuantidade, etNomeDoador;
    Button btnSalvar, btnVoltar;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbHelper = new DBHelper(this); // cria instância do DBHelper

        etNomeDoador = findViewById(R.id.etNomeDoador);
        etNomeItem = findViewById(R.id.etNomeItem);
        etDescricao = findViewById(R.id.etDescricao);
        etQuantidade = findViewById(R.id.etQuantidade);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnSalvar.setOnClickListener(v -> {
            String nomeDoador = etNomeDoador.getText().toString().trim();
            String nome = etNomeItem.getText().toString().trim();
            String descricao = etDescricao.getText().toString().trim();
            String quantidadeStr = etQuantidade.getText().toString().trim();

            if (nome.isEmpty() || quantidadeStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show();
            } else {
                int quantidade = Integer.parseInt(quantidadeStr);

                Doacao doacao = new Doacao(nomeDoador, nome, descricao, quantidade);

                boolean sucesso = dbHelper.inserirDoacao(doacao);

                if (sucesso) {
                    Toast.makeText(this, "Doação cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

                    // Limpa os campos
                    etNomeDoador.setText("");
                    etNomeItem.setText("");
                    etDescricao.setText("");
                    etQuantidade.setText("");
                } else {
                    Toast.makeText(this, "Erro ao cadastrar doação", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltar.setOnClickListener(v -> finish());
    }
}