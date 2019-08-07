package com.amedigital.alodjinha.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class ApiCall{
    private final String URL = "https://alodjinha.herokuapp.com/";

    String GET(String finalurl, Map<String, String> parametro) throws IOException {
        HttpUrl.Builder httpBuider = HttpUrl.parse(URL + finalurl).newBuilder();
        if (parametro != null) {
            for(Map.Entry<String, String> param : parametro.entrySet()) {
                if (param.getKey().equals("produtoId")){
                    httpBuider.addPathSegment(param.getValue());
                }else {
                    httpBuider.addQueryParameter(param.getKey(), param.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .url(httpBuider.build())
                .get()
                .addHeader("cache-control", "no-cache")
                .build();
        return respostaWS(request);
    }

    String POST(String finalurl, Map<String, String> parametro) throws IOException {
        HttpUrl.Builder httpBuider = HttpUrl.parse(URL + finalurl).newBuilder();
        if (parametro != null) {
            for(Map.Entry<String, String> param : parametro.entrySet()) {
                httpBuider.addPathSegment(param.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(httpBuider.build())
                .post(new FormBody.Builder().build())
                .addHeader("cache-control", "no-cache")
                .build();
        return respostaWS(request);
    }

    private String respostaWS(Request request) throws IOException{
        OkHttpClient client;
        String resposta = "";

        client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()){
            resposta = "Erro";
        }else {
            if (response.body() != null) {
                resposta = response.body().string();
            }
        }
        return resposta;
    }
}
