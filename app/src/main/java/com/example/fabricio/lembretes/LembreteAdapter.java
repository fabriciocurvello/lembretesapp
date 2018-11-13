package com.example.fabricio.lembretes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LembreteAdapter extends RecyclerView.Adapter {

    List<Lembrete> lembreteItems;

    //Contrutor que recebe um vetor de lembretes e adiciona os itens em List lembreteItens
    public LembreteAdapter(Lembrete[] lembretes) {
        lembreteItems = new ArrayList<>();
        for (Lembrete lembrete: lembretes) {
            lembreteItems.add(lembrete);
        }
    }

    //Construtor que recebe um List e o adiciona em List lembreteItens
    public LembreteAdapter(List<Lembrete> lembretes) {
        lembreteItems = lembretes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.lembrete_element_layout,
                viewGroup,
                false);

        return new LembreteViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Lembrete lembrete = lembreteItems.get(i);

        LembreteViewHolder lembreteViewHolder = (LembreteViewHolder) viewHolder;

        lembreteViewHolder.tituloTextView.setText(lembrete.getTitulo());
        lembreteViewHolder.conteudoTextView.setText(lembrete.getConteudo());
    }

    @Override
    public int getItemCount() {
        return lembreteItems.size();
    }

    public void addItem(Lembrete newLembrete) {
        lembreteItems.add(newLembrete);

        notifyItemInserted(lembreteItems.size()-1);
    }

    //Classe interna
    public class LembreteViewHolder extends RecyclerView.ViewHolder {

        public TextView tituloTextView;
        public TextView conteudoTextView;

        //construtor
        public LembreteViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloTextView = itemView.findViewById(R.id.titulo_lembrete_element_layout);
            conteudoTextView = itemView.findViewById(R.id.conteudo_lembrete_element_layout);
        }
    }
}
