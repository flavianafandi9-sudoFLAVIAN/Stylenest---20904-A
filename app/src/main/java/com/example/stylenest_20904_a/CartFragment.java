package com.example.stylenest_20904_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;

public class CartFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView rvCart = view.findViewById(R.id.rv_cart);
        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        
        CartAdapter realAdapter = new CartAdapter(CartManager.getInstance().getCartItems());
        rvCart.setAdapter(realAdapter);

        TextView tvTotal = view.findViewById(R.id.tv_cart_total);
        double total = CartManager.getInstance().calculateTotal();
        tvTotal.setText(String.format(Locale.getDefault(), "Ksh %.2f", total));

        view.findViewById(R.id.btn_checkout).setOnClickListener(v -> {
            if (!UserManager.getInstance(getContext()).isLoggedIn()) {
                Toast.makeText(getContext(), "Please sign in to checkout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            } else if (CartManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(getContext(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
