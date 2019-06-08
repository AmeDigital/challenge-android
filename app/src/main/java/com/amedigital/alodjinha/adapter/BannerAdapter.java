package com.amedigital.alodjinha.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.amedigital.alodjinha.R;
import com.amedigital.alodjinha.model.Banner;
import com.facebook.drawee.view.SimpleDraweeView;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private List<Banner> listaBanner;
    private Context context;

    public BannerAdapter(ArrayList<Banner> listaBanner, Context context) {
        this.listaBanner = listaBanner;
        this.context = context;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_banner, parent, false);

        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = listaBanner.get(position);
        holder.setFotoBanner(banner.getUrlImagem());
    }

    @Override
    public int getItemCount() {
        return listaBanner.size();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgFotoBanner;

        private BannerViewHolder(View view) {
            super(view);
            imgFotoBanner = view.findViewById(R.id.imgFotoBanner);
        }

        private void setFotoBanner(String urlimagem) {
            imgFotoBanner.setImageURI(urlimagem.replace("http://", "https://"));
        }
    }
}