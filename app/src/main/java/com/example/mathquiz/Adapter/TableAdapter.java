package com.example.mathquiz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathquiz.Activity.MainActivity;
import com.example.mathquiz.Activity.TableDetails;
import com.example.mathquiz.R;
import com.example.mathquiz.databinding.ItemBinding;
import com.example.mathquiz.databinding.TableItemBinding;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {
    private Context context;
    private List<Integer> list;
    public TableAdapter(Context context, List<Integer> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(TableItemBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.item.setText("x" + list.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TableDetails.class);
                intent.putExtra("number",list.get(holder.getAdapterPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TableItemBinding binding;
        public MyViewHolder(@NonNull TableItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
