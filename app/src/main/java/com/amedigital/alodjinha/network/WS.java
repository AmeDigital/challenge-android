package com.amedigital.alodjinha.network;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WS extends AsyncTask<String,Void,String> {
    private AsyncTaskCompleteListener<String> callback;

    private boolean get;
    private String url;
    private Map<String, String> parametros;

    public WS(AsyncTaskCompleteListener<String> cb, boolean get, String url, Map<String, String> parametros){
        this.callback = cb;
        this.get = get;
        this.url = url;
        this.parametros = parametros;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "Erro";
        ApiCall apiCall = new ApiCall();
        try {
            if (get){
                response = apiCall.GET(url, parametros);
            }else{
                response = apiCall.POST(url, parametros);
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