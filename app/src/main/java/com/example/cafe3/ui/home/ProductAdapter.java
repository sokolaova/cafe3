package com.example.cafe3.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafe3.R;
import com.example.cafe3.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product, ProductAdapter.ProductViewHolder> {

    private boolean showDeleteButton;

    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options, boolean showDeleteButton) {
        super(options);
        this.showDeleteButton = showDeleteButton;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
        // Загрузка изображения с использованием Picasso
        Picasso.get().load(model.getIcon()).into(holder.productIcon, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                // Изображение успешно загружено
            }

            @Override
            public void onError(Exception e) {
                // Произошла ошибка при загрузке изображения
                Log.e("Picasso", "Error loading image", e);
            }
        });

        holder.productName.setText(model.getName());
        holder.productDescription.setText(model.getDescription());
        holder.productPrice.setText("Price: $" + model.getPrice());

        if (showDeleteButton) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.addToCartButton.setVisibility(View.GONE);
            holder.addToFavoritesButton.setVisibility(View.GONE);
        } else {
            holder.deleteButton.setVisibility(View.GONE);
            holder.addToCartButton.setVisibility(View.VISIBLE);
            holder.addToFavoritesButton.setVisibility(View.VISIBLE);
        }

        holder.addToCartButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("productName", model.getName());
            bundle.putDouble("productPrice", model.getPrice());
            Navigation.findNavController(v).navigate(R.id.navigation_dashboard, bundle);
        });

        holder.addToFavoritesButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("productName", model.getName());
            bundle.putDouble("productPrice", model.getPrice());
            Navigation.findNavController(v).navigate(R.id.navigation_notifications, bundle);
        });

        holder.deleteButton.setOnClickListener(v -> {
            getRef(position).removeValue();
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productIcon;
        TextView productName;
        TextView productDescription;
        TextView productPrice;
        Button addToCartButton;
        Button addToFavoritesButton;
        Button deleteButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productIcon = itemView.findViewById(R.id.productIcon);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
            addToFavoritesButton = itemView.findViewById(R.id.addToFavoritesButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
