package com.pucpr.bancosolidario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaDoacoesActivity extends AppCompatActivity implements DoacaoAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private DoacaoAdapter adapter;
    private DBHelper dbHelper;
    private List<Doacao> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_doacoes);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        lista = dbHelper.listarDoacoes();

        adapter = new DoacaoAdapter(lista, this); // this implementa OnItemClickListener
        recyclerView.setAdapter(adapter);
    }

    // Clique longo: excluir doação
    @Override
    public void onItemLongClick(Doacao doacao, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.excluir_doacao_title));
        builder.setMessage(getString(R.string.excluir_doacao_message));
        builder.setPositiveButton(getString(R.string.sim), (dialog, which) -> {
            dbHelper.excluirDoacao(doacao.getId());
            adapter.removerItem(position);
        });
        builder.setNegativeButton(getString(R.string.cancelar), null);
        builder.show();
    }

    // Clique curto: editar doação
    @Override
    public void onItemClick(Doacao doacao) {
        Intent intent = new Intent(ListaDoacoesActivity.this, EditarDoacaoActivity.class);
        intent.putExtra("doacao", doacao);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lista.clear();
        lista.addAll(dbHelper.listarDoacoes());
        adapter.notifyDataSetChanged();
    }
}
