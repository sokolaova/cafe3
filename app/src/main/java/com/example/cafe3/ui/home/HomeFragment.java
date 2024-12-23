package com.example.cafe3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafe3.R;
import com.example.cafe3.model.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Инициализация Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

        // Настройка FirebaseRecyclerOptions
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(databaseReference, Product.class)
                        .build();

        // Инициализация адаптера
        productAdapter = new ProductAdapter(options, false);
        recyclerView.setAdapter(productAdapter);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        productAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        productAdapter.stopListening();
    }
}
