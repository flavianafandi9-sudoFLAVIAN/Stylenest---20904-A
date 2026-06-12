package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView rvCart = view.findViewById(R.id.rv_cart);
        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        
        CartAdapter adapter = new CartAdapter(CartManager.getInstance().getCartItems());
        rvCart.setAdapter(adapter);

        view.findViewById(R.id.btn_checkout).setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(getContext(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Proceeding to Checkout...", Toast.LENGTH_SHORT).show();
                // Logic for checkout can be added here
            }
        });

        return view;
    }
}