package com.example.mentor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import api.TestRepository;
import api.TestService;
import model.TestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    TestService testService;
    Button btnnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testapi);

        testService = TestRepository.getTestService();

        btnnn = findViewById(R.id.btnnnn);
        btnnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllSlots();
            }
        });

    }


    private void getAllSlots() {
        Call<TestApi> call = testService.getAllSlots();
        call.enqueue(new Callback<TestApi>() {
            @Override
            public void onResponse(Call<TestApi> call, Response<TestApi> response) {
                System.out.println(response.body());
                TestApi slots1 = response.body();
                if (slots1 == null) {
                    return;
                }

                    System.out.println(slots1.g);




            }

            @Override
            public void onFailure(Call<TestApi> call, Throwable t) {
                        t.printStackTrace();
            }


        });
    }
}
