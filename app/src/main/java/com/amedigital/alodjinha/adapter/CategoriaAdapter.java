package com.amedigital.alodjinha.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amedigital.alodjinha.R;
import com.amedigital.alodjinha.fragment.CategoriaProdutoFragment;
import com.amedigital.alodjinha.model.Categoria;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {
    private List<Categoria> listaCategoria;
    private Context context;
    private FragmentManager fM;

    public CategoriaAdapter(ArrayList<Categoria> listaCategoria, Context context, FragmentManager fM) {
        this.listaCategoria = listaCategoria;
        this.context = context;
        this.fM = fM;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_categoria, parent, false);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = listaCategoria.get(position);
        final int idcategoria = categoria.getId();
        final String descricao = categoria.getDescricao();
        holder.setFotoCategoria(categoria.getUrlImagem());
        holder.setDescricao(descricao);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                fragment = CategoriaProdutoFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putInt("id", idcategoria);
                bundle.putString("descricao", descricao);
                fragment.setArguments(bundle);
                fM.beginTransaction().replace(R.id.frameLayout, fragment, "categoriaproduto").addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }

    class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgFotoCategoria;
        private TextView txtDescricao;

        private CategoriaViewHolder(View view) {
            super(view);
            imgFotoCategoria = view.findViewById(R.id.imgFotoCategoria);
            txtDescricao = view.findViewById(R.id.txtNome);
        }

        private void setFotoCategoria(String urlImagem) {
            imgFotoCategoria.setImageURI(urlImagem.replace("http://", "https://"));
        }

        private void setDescricao(String descricao){ txtDescricao.setText(descricao);}
    }
}