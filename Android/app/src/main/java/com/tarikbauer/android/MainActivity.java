package com.tarikbauer.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;

@SuppressWarnings("NullableProblems")
public class MainActivity extends AppCompatActivity {

    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Implementation data = new Implementation(input.getText().toString());
                String url = "http://192.168.0.25:8080/";
                Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
                Interface anInterface = retrofit.create(Interface.class);
                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("Content-Type", "application/json");
                Call<Implementation> call_object = anInterface.send_post(headerMap, data);
                call_object.enqueue(new Callback<Implementation>() {
                    @Override
                    public void onResponse(Call<Implementation> call, Response<Implementation> response) {
                        Intent result_intent = new Intent(MainActivity.this, ResultActivity.class);
                        result_intent.putExtra("result", response.body() != null ? response.body().result : null);
                        MainActivity.this.startActivity(result_intent);
                    }
                    @Override
                    public void onFailure(Call<Implementation> call, Throwable throwable) {
                        Log.d("onFailure", String.valueOf(throwable));
                    }
                });
            }
        });
    }
}
