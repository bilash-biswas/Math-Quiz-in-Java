package com.example.mathquiz.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mathquiz.Adapter.TableAdapter;
import com.example.mathquiz.R;
import com.example.mathquiz.databinding.ActivityTableBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableActivity extends AppCompatActivity {
    private ActivityTableBinding binding;
    private TableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numberList.add(i);
        }

        adapter = new TableAdapter(this, numberList);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

    }
}