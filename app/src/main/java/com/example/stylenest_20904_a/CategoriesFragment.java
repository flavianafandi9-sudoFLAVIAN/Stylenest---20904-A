package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private List<Product> allProducts;
    private List<Product> filteredProducts;
    private ProductAdapter productAdapter;
    private String currentCategory = "All";
    private String currentSearchQuery = "";
    private TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        tvTitle = view.findViewById(R.id.tv_collections_title);
        setupData();

        RecyclerView rvTabs = view.findViewById(R.id.rv_categories_tabs);
        List<String> tabs = new ArrayList<>();
        tabs.add("All");
        tabs.add("Dresses");
        tabs.add("Shoes");
        tabs.add("Trousers");
        tabs.add("Accessories");

        CategoryTabAdapter tabAdapter = new CategoryTabAdapter(tabs, this::filterByCategory);
        rvTabs.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvTabs.setAdapter(tabAdapter);

        RecyclerView rvProducts = view.findViewById(R.id.rv_products_list);
        productAdapter = new ProductAdapter(filteredProducts);
        rvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProducts.setAdapter(productAdapter);

        EditText etSearch = view.findViewById(R.id.et_search);
        
        // Handle initial search if any (e.g. from Home screen)
        Bundle args = getArguments();
        if (args != null && args.containsKey("search_query")) {
            currentSearchQuery = args.getString("search_query");
            etSearch.setText(currentSearchQuery);
            applyFilters();
        }

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearchQuery = s.toString();
                applyFilters();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        view.findViewById(R.id.btn_filter).setOnClickListener(v -> 
            Toast.makeText(getContext(), "Filter clicked", Toast.LENGTH_SHORT).show());
        
        view.findViewById(R.id.btn_sort).setOnClickListener(v -> 
            Toast.makeText(getContext(), "Sort clicked", Toast.LENGTH_SHORT).show());

        return view;
    }

    private void setupData() {
        allProducts = new ArrayList<>();
        allProducts.add(new Product("Butterfly Watch Set", "Ksh 3,500", R.drawable.watch_set, "Accessories"));
        allProducts.add(new Product("Evening Silk Gown", "Ksh 8,500", R.drawable.maroon_dress, "Dresses"));
        allProducts.add(new Product("African Print Top", "Ksh 4,200", R.drawable.african_top, "Trousers"));
        allProducts.add(new Product("Adidas Samba", "Ksh 6,800", R.drawable.samba_sneakers, "Shoes"));
        allProducts.add(new Product("Summer Palm Set", "Ksh 3,200", R.drawable.palm_shirt, "Trousers"));
        allProducts.add(new Product("Premium Handbag", "Ksh 5,500", R.drawable.black_handbag, "Accessories"));
        allProducts.add(new Product("Toddler Ruffle Set", "Ksh 2,800", R.drawable.kids_set, "Dresses"));
        allProducts.add(new Product("Blue Flame Hoodie", "Ksh 4,500", R.drawable.flame_hoodie, "Trousers"));
        allProducts.add(new Product("Lavender Ruffle Suit", "Ksh 5,800", R.drawable.lavender_set, "Trousers"));
        
        filteredProducts = new ArrayList<>(allProducts);
    }

    private void filterByCategory(String category) {
        this.currentCategory = category;
        applyFilters();
    }

    private void applyFilters() {
        filteredProducts.clear();
        for (Product p : allProducts) {
            boolean categoryMatch = currentCategory.equals("All") || p.getCategory().equalsIgnoreCase(currentCategory);
            boolean searchMatch = currentSearchQuery.isEmpty() || 
                                p.getName().toLowerCase().contains(currentSearchQuery.toLowerCase()) ||
                                p.getCategory().toLowerCase().contains(currentSearchQuery.toLowerCase());
            
            if (categoryMatch && searchMatch) {
                filteredProducts.add(p);
            }
        }
        
        if (tvTitle != null) {
            if (!currentSearchQuery.isEmpty()) {
                tvTitle.setText("SEARCH: " + currentSearchQuery.toUpperCase());
            } else if (!currentCategory.equals("All")) {
                tvTitle.setText(currentCategory.toUpperCase());
            } else {
                tvTitle.setText("COLLECTIONS");
            }
        }

        productAdapter.notifyDataSetChanged();
    }
}
