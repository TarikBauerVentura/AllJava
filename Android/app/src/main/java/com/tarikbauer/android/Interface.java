package com.tarikbauer.android;

import retrofit2.Call;
import retrofit2.http.*;
import java.util.Map;

public interface Interface {

    @POST("callback")
    Call<Implementation> send_post(
            @HeaderMap Map<String, String> headers,
            @Body Implementation data);
}
