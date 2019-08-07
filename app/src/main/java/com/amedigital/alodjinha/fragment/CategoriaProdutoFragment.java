package com.amedigital.alodjinha.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.amedigital.alodjinha.R;
import com.amedigital.alodjinha.adapter.ProdutoAdapter;
import com.amedigital.alodjinha.common.ErroWs;
import com.amedigital.alodjinha.common.PaginationScrollListener;
import com.amedigital.alodjinha.model.Produto;
import com.amedigital.alodjinha.network.AsyncTaskCompleteListener;
import com.amedigital.alodjinha.network.WS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class CategoriaProdutoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;
    private RecyclerView rvProduto;
    private ProgressBar pbProgresso;
    private SwipeRefreshLayout swipeRefresh;

    private ProdutoAdapter adapterProdutos;
    private JSONArray jsonArray;

    private final int PAGE_START = 1, ITEM_PAGE = 20;
    private int id, currentPage = PAGE_START, totalPage, itemCount = 0;
    private boolean isLastPage = false, isLoading = false, carregouLista = false;

    public CategoriaProdutoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categoria_produto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializar(view);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            toolbar.setTitle(bundle.getString("descricao"));
        }
        if (getActivity() != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            appCompatActivity.setSupportActionBar(toolbar);
            if (appCompatActivity.getSupportActionBar() != null) {
                appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        setHasOptionsMenu(true);

        ButterKnife.bind(getActivity());

        swipeRefresh.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        setParametrosRecyclerView(view, layoutManager);

        rvProduto.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                preparedListItem();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        if (!carregouLista) {
            carregarListaProdutoWs();
            carregouLista = true;
        }else {
            rvProduto.setAdapter(adapterProdutos);
            pbProgresso.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStackImmediate();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public static CategoriaProdutoFragment newInstance() {
        return new CategoriaProdutoFragment();
    }

    private void inicializar(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        rvProduto = view.findViewById(R.id.rvProduto);
        pbProgresso = view.findViewById(R.id.pbProgresso);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
    }

    private void setParametrosRecyclerView(@NonNull View view, LinearLayoutManager layoutManager) {
        rvProduto.setHasFixedSize(true);
        rvProduto.setLayoutManager(layoutManager);
        rvProduto.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void carregarListaProdutoWs(){
        Map<String, String> parametros = new HashMap<>();
        parametros.put("categoriaId", String.valueOf(id));

        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                carregarRetornoWs(result);
            }
        }, true, "produto", parametros).execute();
    }

    public void carregarRetornoWs(String response) {
        try {
            if ("Erro".equals(response)) {
                ErroWs.retornarErroWS(getContext(), pbProgresso);
            } else {
                JSONObject jsonObjectData = new JSONObject(response);
                try {
                    jsonArray = jsonObjectData.getJSONArray("data");
                    if (getActivity() != null) {
                        adapterProdutos = new ProdutoAdapter(new ArrayList<Produto>(),
                                getContext(), getActivity().getSupportFragmentManager());
                        rvProduto.setAdapter(adapterProdutos);
                    }
                    totalPage = (jsonArray.length() + (ITEM_PAGE - 1)) / ITEM_PAGE;
                    preparedListItem();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErroWs.retornarErroWS(getContext(), pbProgresso);
        }
    }

    private void preparedListItem() {
        final ArrayList<Produto> listaProdutos = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                int qtdeProduto = jsonArray.length() - itemCount;

                for (int i = 0; i < qtdeProduto && i < ITEM_PAGE; i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(itemCount);
                        Produto produto = new Produto();
                        produto.setId(jsonObject.getInt("id"));
                        produto.setNome(jsonObject.getString("nome"));
                        produto.setUrlImagem(jsonObject.getString("urlImagem"));
                        produto.setDescricao(jsonObject.getString("descricao"));
                        produto.setPrecoDe(jsonObject.getDouble("precoDe"));
                        produto.setPrecoPor(jsonObject.getDouble("precoPor"));
                        listaProdutos.add(produto);

                        itemCount++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (itemCount <= ITEM_PAGE) pbProgresso.setVisibility(View.GONE);

                if (currentPage != PAGE_START) adapterProdutos.removeLoading();
                adapterProdutos.addAll(listaProdutos);
                swipeRefresh.setRefreshing(false);
                if (currentPage < totalPage){
                    adapterProdutos.addLoading();
                }else{
                    isLastPage = true;
                }
                isLoading = false;


            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapterProdutos.clear();
        preparedListItem();
    }
}
