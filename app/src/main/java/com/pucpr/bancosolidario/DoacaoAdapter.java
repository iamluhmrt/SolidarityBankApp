package com.pucpr.bancosolidario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoacaoAdapter extends RecyclerView.Adapter<DoacaoAdapter.DoacaoViewHolder> {

    private final List<Doacao> listaDoacoes;
    private final OnItemClickListener itemClickListener;

    // Interface para clique curto e longo
    public interface OnItemClickListener {
        void onItemClick(Doacao doacao);                     // clique normal (editar)
        void onItemLongClick(Doacao doacao, int position);   // clique longo (excluir)
    }

    public DoacaoAdapter(List<Doacao> listaDoacoes, OnItemClickListener itemClickListener) {
        this.listaDoacoes = listaDoacoes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DoacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doacao, parent, false);
        return new DoacaoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DoacaoViewHolder holder, int position) {
        Doacao doacao = listaDoacoes.get(position);
        Context context = holder.itemView.getContext();

        holder.tvNomeDoador.setText(context.getString(R.string.doador_format, doacao.getNomeDoador()));
        holder.tvNomeItem.setText(context.getString(R.string.item_format, doacao.getNomeItem()));
        holder.tvDescricao.setText(context.getString(R.string.descricao_format, doacao.getDescricao()));
        holder.tvQuantidade.setText(context.getString(R.string.quantidade_format, doacao.getQuantidade()));

        // Clique normal = editar
        holder.btnEditar.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(doacao);
            }
        });

        // Clique longo = excluir
        holder.btnExcluir.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemLongClick(doacao, holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaDoacoes.size();
    }

    public void removerItem(int position) {
        listaDoacoes.remove(position);
        notifyItemRemoved(position);
    }

    public static class DoacaoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeDoador, tvNomeItem, tvDescricao, tvQuantidade;
        Button btnExcluir, btnEditar;

        public DoacaoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeDoador = itemView.findViewById(R.id.tvNomeDoador);
            tvNomeItem = itemView.findViewById(R.id.tvNomeItem);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }
}
