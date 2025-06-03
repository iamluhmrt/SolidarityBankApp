package com.pucpr.bancosolidario;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarDoacaoActivity extends AppCompatActivity {

    private EditText etNomeDoador, etNomeItem, etDescricao, etQuantidade;
    private Button btnAtualizar, btnVoltar;
    private DBHelper dbHelper;
    private Doacao doacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_doacao);

        etNomeDoador = findViewById(R.id.etNomeDoador);
        etNomeItem = findViewById(R.id.etNomeItem);
        etDescricao = findViewById(R.id.etDescricao);
        etQuantidade = findViewById(R.id.etQuantidade);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnVoltar = findViewById(R.id.btnVoltar);

        dbHelper = new DBHelper(this);

        // Recuperar doação enviada pela Intent
        doacao = (Doacao) getIntent().getSerializableExtra("doacao");

        if (doacao != null) {
            etNomeDoador.setText(doacao.getNomeDoador());
            etNomeItem.setText(doacao.getNomeItem());
            etDescricao.setText(doacao.getDescricao());
            etQuantidade.setText(String.valueOf(doacao.getQuantidade()));
        }

        btnAtualizar.setOnClickListener(v -> {
            String nomeDoador = etNomeDoador.getText().toString().trim();
            String nomeItem = etNomeItem.getText().toString().trim();
            String descricao = etDescricao.getText().toString().trim();
            String quantidadeStr = etQuantidade.getText().toString().trim();

            if (nomeDoador.isEmpty() || nomeItem.isEmpty() || descricao.isEmpty() || quantidadeStr.isEmpty()) {
                Toast.makeText(this, getString(R.string.preencha_todos_os_campos), Toast.LENGTH_SHORT).show();
                return;
            }

            int quantidade = Integer.parseInt(quantidadeStr);

            doacao.setNomeDoador(nomeDoador);
            doacao.setNomeItem(nomeItem);
            doacao.setDescricao(descricao);
            doacao.setQuantidade(quantidade);

            boolean sucesso = dbHelper.atualizarDoacao(doacao);

            if (sucesso) {
                Toast.makeText(this, getString(R.string.doacao_atualizada), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, getString(R.string.erro_ao_atualizar), Toast.LENGTH_SHORT).show();
            }
        });

        btnVoltar.setOnClickListener(v -> finish());
    }
}
