package com.amedigital.alodjinha.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.amedigital.alodjinha.R;
import com.amedigital.alodjinha.adapter.BannerAdapter;
import com.amedigital.alodjinha.adapter.CategoriaAdapter;
import com.amedigital.alodjinha.adapter.ProdutoAdapter;
import com.amedigital.alodjinha.common.CirclePagerIndicatorDecoration;
import com.amedigital.alodjinha.common.ErroWs;
import com.amedigital.alodjinha.interfaces.ComunicadorMainActvityInterface;
import com.amedigital.alodjinha.model.Banner;
import com.amedigital.alodjinha.model.Categoria;
import com.amedigital.alodjinha.model.Produto;
import com.amedigital.alodjinha.network.AsyncTaskCompleteListener;
import com.amedigital.alodjinha.network.WS;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private Toolbar toolbar;
    private ConstraintLayout constraintLayout;
    private RecyclerView rvBanner, rvCategoria, rvMaisVendido;
    private ProgressBar pbProgresso;
    private ArrayList<Banner> listaBanner;
    private ArrayList<Categoria> listaCategoria;
    private ArrayList<Produto> listaProduto;
    private BannerAdapter adapterBunner;
    private CategoriaAdapter adapterCategoria;
    private ProdutoAdapter adapterProdutos;
    private ComunicadorMainActvityInterface comunicadorMainActvityInterface;
    private boolean carregouListas = false;

    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Fresco.initialize(view.getContext());
        inicializar(view);
        toolbar.setTitle("");
        comunicadorMainActvityInterface.setMenu(toolbar);
        setParametrosRecyclerView(view);
        if (!carregouListas) {
            listaBanner = new ArrayList<>();
            listaCategoria = new ArrayList<>();
            listaProduto = new ArrayList<>();
            carregarListaBannerWs();
            carregarListaCategoriaWs();
            carregarListaMaisVendidoWs();
            carregouListas = true;
        }else{
            rvBanner.setAdapter(adapterBunner);
            rvCategoria.setAdapter(adapterCategoria);
            rvMaisVendido.setAdapter(adapterProdutos);
            pbProgresso.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        comunicadorMainActvityInterface = (ComunicadorMainActvityInterface)getActivity();
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private void inicializar(View view){
        toolbar =  view.findViewById(R.id.toolbar);
        constraintLayout = view.findViewById(R.id.constraintLayout);
        rvBanner =  view.findViewById(R.id.rvBanner);
        rvCategoria = view.findViewById(R.id.rvCategoria);
        rvMaisVendido = view.findViewById(R.id.rvProduto);
        pbProgresso = view.findViewById(R.id.pbProgresso);
    }

    private void setParametrosRecyclerView(@NonNull View view) {
        rvBanner.setHasFixedSize(true);
        rvBanner.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvBanner.addItemDecoration(new CirclePagerIndicatorDecoration());
        rvCategoria.setHasFixedSize(true);
        rvCategoria.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMaisVendido.setHasFixedSize(true);
        rvMaisVendido.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        rvMaisVendido.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void carregarListaBannerWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                new ThreadCarregarRetornoBannerWs(result).start();
            }
        }, true, "banner", null).execute();
    }

    private void carregarListaCategoriaWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                new ThreadCarregarRetornoCategoriaWs(result).start();
            }
        }, true, "categoria", null).execute();
    }

    private void carregarListaMaisVendidoWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                new ThreadCarregarRetornoMaisVendidoWs(result).start();
            }
        }, true, "produto/maisvendidos", null).execute();
    }

    private class ThreadCarregarRetornoBannerWs extends Thread{
        private String result;

        ThreadCarregarRetornoBannerWs(String result){
            this.result = result;
        }

        @Override
        public void run() {
            try {
                if (result.equals("Erro")) {
                    ErroWs.retornarErroWS(getContext(), pbProgresso);
                }else {
                    JSONObject jsonObjectData = new JSONObject(result);
                    JSONArray jsonArray = jsonObjectData.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Banner banner = new Banner();
                        banner.setId(jsonObject.getInt("id"));
                        banner.setUrlImagem(jsonObject.getString("urlImagem"));
                        listaBanner.add(banner);
                    }
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterBunner = new BannerAdapter(listaBanner, getContext());
                                rvBanner.setAdapter(adapterBunner);
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ErroWs.retornarErroWS(getContext(), pbProgresso);
            }
        }
    }

    private class ThreadCarregarRetornoCategoriaWs extends Thread{
        private String result;

        ThreadCarregarRetornoCategoriaWs(String result){
            this.result = result;
        }

        @Override
        public void run() {
            try {
                if (result.equals("Erro")) {
                    ErroWs.retornarErroWS(getContext(), pbProgresso);
                }else {
                    JSONObject jsonObjectData = new JSONObject(result);
                    JSONArray jsonArray = jsonObjectData.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Categoria categoria = new Categoria();
                        categoria.setId(jsonObject.getInt("id"));
                        categoria.setDescricao(jsonObject.getString("descricao"));
                        categoria.setUrlImagem(jsonObject.getString("urlImagem"));
                        listaCategoria.add(categoria);
                    }
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterCategoria = new CategoriaAdapter(listaCategoria, getContext(), getActivity().getSupportFragmentManager());
                                rvCategoria.setAdapter(adapterCategoria);
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ErroWs.retornarErroWS(getContext(), pbProgresso);
            }
        }
    }

    private class ThreadCarregarRetornoMaisVendidoWs extends Thread{
        private String result;

        ThreadCarregarRetornoMaisVendidoWs(String result){
            this.result = result;
        }

        @Override
        public void run() {
            try {
                if (result.equals("Erro")) {
                    ErroWs.retornarErroWS(getContext(), pbProgresso);
                }else {
                    JSONObject jsonObjectData = new JSONObject(result);
                    JSONArray jsonArray = jsonObjectData.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Produto produto = new Produto();
                        produto.setId(jsonObject.getInt("id"));
                        produto.setNome(jsonObject.getString("nome"));
                        produto.setUrlImagem(jsonObject.getString("urlImagem"));
                        produto.setDescricao(jsonObject.getString("descricao"));
                        produto.setPrecoDe(jsonObject.getDouble("precoDe"));
                        produto.setPrecoPor(jsonObject.getDouble("precoPor"));
                        listaProduto.add(produto);
                    }
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterProdutos = new ProdutoAdapter(listaProduto, getContext(), getActivity().getSupportFragmentManager());
                                rvMaisVendido.setAdapter(adapterProdutos);
                                pbProgresso.setVisibility(View.GONE);
                                constraintLayout.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ErroWs.retornarErroWS(getContext(), pbProgresso);
            }
        }
    }
}