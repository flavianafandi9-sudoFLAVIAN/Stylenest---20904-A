package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setupRecyclerView(view.findViewById(R.id.rv_featured));
        setupRecyclerView(view.findViewById(R.id.rv_new_arrivals));

        return view;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        List<Product> products = new ArrayList<>();
        // Using the new high-quality Vector graphics for products
        products.add(new Product("Urban Bomber", "Ksh 3,500", R.drawable.ic_hoodie));
        products.add(new Product("Slim Fit Denim", "Ksh 2,800", R.drawable.ic_trousers));
        products.add(new Product("Nairobi Graphic Tee", "Ksh 1,500", R.drawable.ic_tshirt));
        products.add(new Product("Classic Chinos", "Ksh 3,200", R.drawable.ic_trousers));
        products.add(new Product("StyleNest Hoodie", "Ksh 4,000", R.drawable.ic_hoodie));

        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}