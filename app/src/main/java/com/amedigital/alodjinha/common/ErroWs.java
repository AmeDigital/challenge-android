package com.amedigital.alodjinha.common;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amedigital.alodjinha.R;

public class ErroWs {

    public static void retornarErroWS(Context context, ProgressBar pbProgresso){
        Toast.makeText(context, R.string.erro_conexao, Toast.LENGTH_SHORT).show();
        pbProgresso.setVisibility(View.GONE);
    }
}
