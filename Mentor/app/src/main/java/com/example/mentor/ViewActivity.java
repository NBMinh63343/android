package com.example.mentor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import adapter.SlotsAdapter;
import adapter.SlotsAdapterAll;
import api.SlotsRepository;
import api.SlotsService;
import model.Slots;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {

    SlotsService slotsService;
    ArrayList<Slots> slotsList;
    String mentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_slots);
        Intent intent = getIntent();
        mentor = intent.getStringExtra("mentor");

        slotsService = SlotsRepository.getSlotsService();
        getAllSlots();


    }

    private void getAllSlots() {
        slotsList= new ArrayList<>();
        Call<Slots[]> call = slotsService.getAllSlots();
        call.enqueue(new Callback<Slots[]>() {
            @Override
            public void onResponse(Call<Slots[]> call, Response<Slots[]> response) {
                Slots[] slots1 = response.body();
                if (slots1 == null) {
                    return;
                }
                for (Slots slots : slots1) {
                    long id = (long)slots.getId();
                    String name = slots.getName();
                    String date = slots.getDate();
                    String timeStart = slots.getTimeStart();
                    String timeEnd = slots.getTimeEnd();
                    String mentor = slots.getMentor();

                    slotsList.add(new Slots(id,name,date,timeStart,timeEnd,mentor));

                }
                RecyclerView rvUser = findViewById(R.id.listViewAll);
                SlotsAdapterAll adapter = new SlotsAdapterAll(slotsList,ViewActivity.this,R.layout.row_slots_mentor_viewall);
                rvUser.setAdapter(adapter);
                rvUser.setLayoutManager(new LinearLayoutManager(ViewActivity.this));

            }

            @Override
            public void onFailure(Call<Slots[]> call, Throwable t) {

            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.itemvMentor:
                intent = new Intent(ViewActivity.this, SlotCreateActivity.class);
                intent.putExtra("mentor", mentor);
                startActivity(intent);
                break;
            case R.id.itemvView:
                intent = new Intent(ViewActivity.this, ViewActivity.class);
                intent.putExtra("mentor", mentor);
                startActivity(intent);
                break;
            case R.id.itemvProfiler:
                break;
            case R.id.itemvExit:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}