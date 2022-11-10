package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentor.R;
import com.example.mentor.SlotCreateActivity;

import java.util.ArrayList;


import model.Slots;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.ViewHolder> {

    private ArrayList<Slots> slotsList;
    private SlotCreateActivity context;
    private int layout;


    public SlotsAdapter(ArrayList<Slots> slotsList, SlotCreateActivity context, int layout) {
        this.slotsList = slotsList;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Slots slots = slotsList.get(position);


        holder.tvSlotsName.setText("Title: "+slots.getName() );
        holder.tvDate.setText("Date:  "+slots.getDate() );
        holder.tvTimeStart.setText("Begin Time: "+slots.getTimeStart() );
        holder.tvTimeEnd.setText("End Time:    "+slots.getTimeEnd());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.deleteSlots((int)slots.getId());
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.editDialog((int)slots.getId(),slots.getName(),slots.getDate(),slots.getTimeStart(),slots.getTimeEnd());
            }
        });
    }

    @Override
    public int getItemCount() {
        return slotsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView tvSlotsName, tvDate, tvTimeStart, tvTimeEnd, tvID;
        Button btnDelete, btnEdit;

        public ViewHolder(@NonNull View itemview){
            super(itemview);

            tvID = itemview.findViewById(R.id.tvID);
            tvSlotsName = itemview.findViewById(R.id.tvSlotname);
            tvDate = itemview.findViewById(R.id.tvDate);
            tvTimeStart = itemview.findViewById(R.id.tvTimeStart);
            tvTimeEnd = itemview.findViewById(R.id.tvTimeEnd);
            btnDelete = itemview.findViewById(R.id.btnDeleteSlot);
            btnEdit = itemview.findViewById(R.id.btnEditSlot);


        }

    }






}
