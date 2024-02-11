package com.example.seoulwifiexplorer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiStatusChecker {
    private static final String API_KEY = "51735952526b746838316a4266444c";
    private static final String API_BASE_URL = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/1/1";
    private final OkHttpClient client = new OkHttpClient();

    public boolean isApiOperational() {
        Request request = new Request.Builder()
                .url(API_BASE_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.code() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
