package com.mf.nio.httpclient;

import okhttp3.*;

import java.io.IOException;

public class OkClient {
    public static void main(String[] args) {
        OkClient okClient = new OkClient();
        okClient.call();

    }

    private void call () {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request req = new Request.Builder()
                .url("http://localhost:8888/")
                .get()
                .build();
        okHttpClient.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response);
                System.out.println(response.body().string());
            }
        });



    }
}
