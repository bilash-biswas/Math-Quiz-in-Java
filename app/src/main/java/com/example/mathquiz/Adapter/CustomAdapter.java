package com.example.mathquiz.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathquiz.Model.QuestionModel;
import com.example.mathquiz.R;
import com.example.mathquiz.databinding.ItemBinding;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<QuestionModel> mList;

    public CustomAdapter(Context context, ArrayList<QuestionModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuestionModel model = mList.get(position);
        holder.binding.question.setText(model.getProblem());
        holder.binding.correctAnswer.setText(String.valueOf(model.getAnswer()));
        if (!model.getSelectedOption().equals(String.valueOf(model.getAnswer()))){
            holder.binding.yourAnswer.setTextColor(context.getResources().getColor(R.color.incorrect_answer));
        }
        holder.binding.yourAnswer.setText(model.getSelectedOption());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemBinding.bind(itemView);
        }
    }
}
