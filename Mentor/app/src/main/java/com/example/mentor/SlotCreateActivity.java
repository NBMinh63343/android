package com.example.mentor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import adapter.SlotsAdapter;
import api.SlotsRepository;
import api.SlotsService;
import model.Slots;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SlotCreateActivity extends AppCompatActivity {

    SlotsService slotsService;
    EditText txtTimeStart, txtTimeEnd, txtDay, txtSlotName;
    Button btnAddSlot, btnCreate, btnCancel, btnDelete;
    Dialog slotDialog;
    ArrayList<Slots> slotsList;
    String mentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_slot_create);
        slotsService = SlotsRepository.getSlotsService();
        Intent intent = getIntent();
        mentor = intent.getStringExtra("mentor");
        getAllSlots(mentor);


        btnAddSlot = findViewById(R.id.btnAddSlot);
        btnAddSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCreateDialog();
            }
        });



    }

    public void editDialog(int slotsId, String name, String date, String timeStart, String timeEnd){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_edit);
        Intent intent = getIntent();
        String mentor = intent.getStringExtra("mentor");

        txtSlotName = dialog.findViewById(R.id.txtSlotname);
        txtDay = dialog.findViewById(R.id.txtDay);
        txtTimeStart = dialog.findViewById(R.id.txtTimeStar);
        txtTimeEnd = dialog.findViewById(R.id.txtTimeEnd);

        txtSlotName.setText(name);
        txtDay.setText(date);
        txtTimeStart.setText(timeStart);
        txtTimeEnd.setText(timeEnd);




        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDay();
            }
        });
        txtTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTimeStart();
            }
        });
        txtTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTimeEnd();
            }
        });

        btnCreate = dialog.findViewById(R.id.btnCreateSlot);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtSlotName.getText().toString();
                String day = txtDay.getText().toString();
                String timeStart = txtTimeStart.getText().toString();
                String timeEnd = txtTimeEnd.getText().toString();
                if(name.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "Title is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(day.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "Day is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(timeStart.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "Begin time is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(timeEnd.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "End time is required", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    updateSlots(slotsId,name,day,timeStart,timeEnd);
                    getAllSlots(mentor);
                }

            }
        });
        btnCancel = dialog.findViewById(R.id.btnCancelSlot);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        dialog.show();
    }



    private void viewCreateDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_create);
        Intent intent = getIntent();
        String mentor1 = intent.getStringExtra("mentor");

        txtSlotName = dialog.findViewById(R.id.txtSlotname);
        txtDay = dialog.findViewById(R.id.txtDay);
        txtTimeStart = dialog.findViewById(R.id.txtTimeStar);
        txtTimeEnd = dialog.findViewById(R.id.txtTimeEnd);




        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDay();
            }
        });
        txtTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTimeStart();
            }
        });
        txtTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTimeEnd();
            }
        });

        btnCreate = dialog.findViewById(R.id.btnCreateSlot);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtSlotName.getText().toString();
                String day = txtDay.getText().toString();
                String timeStart = txtTimeStart.getText().toString();
                String timeEnd = txtTimeEnd.getText().toString();
                if(name.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "Title is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(day.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "Day is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(timeStart.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "Begin time is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if(timeEnd.length()<=0){
                    Toast.makeText(SlotCreateActivity.this, "End time is required", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    createSlots(name,day,timeStart,timeEnd,mentor1);
                    getAllSlots(mentor1);
                    dialog.cancel();
                }

            }
        });
        btnCancel = dialog.findViewById(R.id.btnCancelSlot);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        dialog.show();
    }


    private void chooseTimeStart(){
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        int minute = calendar.get(calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                calendar.set(0,0,0,i,i1);
                txtTimeStart.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },hour, minute,true);
        timePickerDialog.show();
    }

    private void chooseTimeEnd(){
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        int minute = calendar.get(calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                calendar.set(0,0,0,i,i1);
                txtTimeEnd.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },hour, minute,true);
        timePickerDialog.show();
    }

    private void chooseDay(){
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtDay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year,month,date);
        datePickerDialog.show();
    }

    private void createSlots(String name, String day, String timeStart, String timeEnd, String mentor){
        Slots slots = new Slots(name ,day, timeStart, timeEnd, mentor);
        try {
            Call<Slots> call = slotsService.createSlot(slots);
            call.enqueue(new Callback<Slots>() {
                @Override
                public void onResponse(Call<Slots> call, Response<Slots> response) {
                    Slots slots1 = response.body();
                    System.out.println(slots1);
                    if (slots1 != null) {
                        Toast.makeText(SlotCreateActivity.this, "Create successfully", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Slots> call, Throwable t) {
                    System.out.println("Loi roi" + call);
                    Toast.makeText(SlotCreateActivity.this, "Create failed", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllSlots(String mentor) {
        Intent intent = getIntent();
        String mentor1 = intent.getStringExtra("mentor");
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
                    if(mentor.equalsIgnoreCase(mentor1)){
                        slotsList.add(new Slots(id,name,date,timeStart,timeEnd,mentor));
                    }

                }
                RecyclerView rvUser = findViewById(R.id.listViewSlot);
                SlotsAdapter adapter = new SlotsAdapter(slotsList,SlotCreateActivity.this,R.layout.row_slots_mentor_view);
                rvUser.setAdapter(adapter);
                rvUser.setLayoutManager(new LinearLayoutManager(SlotCreateActivity.this));

            }

            @Override
            public void onFailure(Call<Slots[]> call, Throwable t) {

            }


        });
    }

    public void deleteSlots(int slotsId) {
        Intent intent = getIntent();
        String mentor = intent.getStringExtra("mentor");
        Call<Slots> call = slotsService.deleteSlotrById(slotsId);
        call.enqueue(new Callback<Slots>() {
            @Override
            public void onResponse(Call<Slots> call, Response<Slots> response) {
                Slots slots1 = response.body();
                if (slots1 != null) {
                    Toast.makeText(SlotCreateActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                    getAllSlots(mentor);
                }
            }

            @Override
            public void onFailure(Call<Slots> call, Throwable t) {
            }
        });
    }

    private void updateSlots(long slotsId, String name, String date, String timeStart, String timeEnd) {
        Intent intent = getIntent();
        String mentor = intent.getStringExtra("mentor");
        Slots slots = new Slots(name, date, timeStart, timeEnd);
        Call<Slots> call = slotsService.updateSlots(slotsId,slots);
        call.enqueue(new Callback<Slots>() {
            @Override
            public void onResponse(Call<Slots> call, Response<Slots> response) {
                Slots product1 = response.body();
                if (product1 != null) {
                    Toast.makeText(SlotCreateActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    getAllSlots(mentor);
                }
            }

            @Override
            public void onFailure(Call<Slots> call, Throwable t) {
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
                intent = new Intent(SlotCreateActivity.this, SlotCreateActivity.class);
                intent.putExtra("mentor", mentor);

                startActivity(intent);
                break;
            case R.id.itemvView:
                intent = new Intent(SlotCreateActivity.this, ViewActivity.class);
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