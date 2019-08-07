package com.amedigital.alodjinha.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amedigital.alodjinha.R;
import com.amedigital.alodjinha.common.ErroWs;
import com.amedigital.alodjinha.network.AsyncTaskCompleteListener;
import com.amedigital.alodjinha.network.WS;
import com.amedigital.alodjinha.util.FormataCampoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DetalheProdutoFragment extends Fragment {
    private ImageView imgVoltar;
    private ConstraintLayout constraintLayout;
    private SimpleDraweeView imgFotoProduto;
    private TextView txtNomeDescricao, txtDe, txtPor, txtNome, txtDescricao;
    private FloatingActionButton fabReservar;
    private ProgressBar pbProgresso;

    private int id;

    public DetalheProdutoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalhe_produto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializar(view);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }
        carregarProdutoWs();

        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });

        fabReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabReservar.setClickable(false);
                pbProgresso.setVisibility(View.VISIBLE);
                reservarProdutoWs();
            }
        });
    }


    private void inicializar(View view) {
        imgVoltar = view.findViewById(R.id.imgVoltar);
        constraintLayout = view.findViewById(R.id.constraintLayout);
        imgFotoProduto = view.findViewById(R.id.imgFotoProduto);
        txtNomeDescricao = view.findViewById(R.id.txtNomeDescricao);
        txtDe = view.findViewById(R.id.txtDe);
        txtPor = view.findViewById(R.id.txtPor);
        txtNome = view.findViewById(R.id.txtNome);
        txtDescricao = view.findViewById(R.id.txtDescricao);
        fabReservar = view.findViewById(R.id.fabReservar);
        pbProgresso = view.findViewById(R.id.pbProgresso);
    }

    public static DetalheProdutoFragment newInstance() {
        return new DetalheProdutoFragment();
    }

    private void carregarProdutoWs(){
        Map<String, String> parametros = new HashMap<>();
        parametros.put("produtoId", String.valueOf(id));
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                new ThreadCarregarRetornoProdutoWs(result).start();
            }
        }, true, "produto", parametros).execute();
    }

    private void reservarProdutoWs(){
        Map<String, String> parametros = new HashMap<>();
        parametros.put("produtoId", String.valueOf(id));
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                new ThreadCarregarRetornoReservarProdutoWs(result).start();
            }
        }, false, "produto", parametros).execute();
    }

    private class ThreadCarregarRetornoProdutoWs extends Thread{
        private String result;

        ThreadCarregarRetornoProdutoWs(String result){
            this.result = result;
        }

        @Override
        public void run() {
            try {
                if (result.equals("Erro")) {
                    ErroWs.retornarErroWS(getContext(), pbProgresso);
                }else {
                    final JSONObject jsonObject = new JSONObject(result);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    imgFotoProduto.setImageURI(jsonObject.getString("urlImagem"));
                                    if (getContext() != null) {
                                        txtNomeDescricao.setText((getContext().getString(R.string.nome_descricao_produto, jsonObject.getString("nome"), jsonObject.getString("descricao"))));
                                        txtDe.setText(getContext().getString(R.string.preco_de, FormataCampoUtil.formatarDecimal(jsonObject.getDouble("precoDe"))));
                                        FormataCampoUtil.riscarTextView(txtDe);
                                        txtPor.setText(getContext().getString(R.string.preco_por, FormataCampoUtil.formatarDecimal(jsonObject.getDouble("precoPor"))));
                                    }
                                    txtNome.setText(jsonObject.getString("nome"));
                                    txtDescricao.setText(Html.fromHtml(jsonObject.getString("descricao")));
                                    pbProgresso.setVisibility(View.GONE);
                                    constraintLayout.setVisibility(View.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    ErroWs.retornarErroWS(getContext(), pbProgresso);
                                }

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

    private class ThreadCarregarRetornoReservarProdutoWs extends Thread{
        private String result;

        ThreadCarregarRetornoReservarProdutoWs(String result){
            this.result = result;
        }

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.equals("Erro")) {
                            ErroWs.retornarErroWS(getContext(), pbProgresso);
                        }else {
                            pbProgresso.setVisibility(View.GONE);

                            AlertDialog alerta;
                            if (getContext() != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                if (result.equals("Erro")) {
                                    builder.setMessage(getContext().getString(R.string.erro_reserva_produto));
                                } else {
                                    builder.setMessage(getContext().getString(R.string.produto_reservado));
                                }
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        fabReservar.setClickable(true);
                                        if (!result.equals("Erro")) {
                                            if (getFragmentManager() != null) {
                                                getFragmentManager().popBackStackImmediate();
                                            }
                                        }
                                    }
                                });
                                alerta = builder.create();
                                alerta.setCanceledOnTouchOutside(false);
                                alerta.show();
                            }
                        }
                    }
                });
            }
        }
    }
}
