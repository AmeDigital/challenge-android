package com.amedigital.alodjinha.network;

import okhttp3.FormBody;
import okhttp3.RequestBody;

class WSRequest {

    static RequestBody reservarProdutoBody() {
        return new FormBody.Builder()
                .build();
    }
}
