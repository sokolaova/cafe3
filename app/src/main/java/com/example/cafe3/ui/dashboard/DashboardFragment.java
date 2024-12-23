package com.example.cafe3.ui.dashboard;

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
import com.example.cafe3.ui.home.ProductAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Инициализация Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("cart");

        // Настройка FirebaseRecyclerOptions
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(databaseReference, Product.class)
                        .build();

        // Инициализация адаптера
        productAdapter = new ProductAdapter(options, true);
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
