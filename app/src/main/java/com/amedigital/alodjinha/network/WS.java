package com.amedigital.alodjinha.network;

import android.os.AsyncTask;

import java.io.IOException;

public class WS extends AsyncTask<String,Void,String> {
    private AsyncTaskCompleteListener<String> callback;

    private boolean get;
    private String url;

    public WS(AsyncTaskCompleteListener<String> cb, boolean get,  String url){
        this.callback = cb;
        this.get = get;
        this.url = url;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "Erro";
        try {
            if (get){
                response = ApiCall.GET(url);
            }else{
                response = ApiCall.POST(url, WSRequest.reservarProdutoBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        callback.onTaskComplete(response);
    }
}