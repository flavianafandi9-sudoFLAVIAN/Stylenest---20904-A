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
        allProducts.add(new Product("Silk Maxi Dress", "Ksh 4,500", R.drawable.ic_tshirt, "Dresses"));
        allProducts.add(new Product("Urban Sneakers", "Ksh 3,800", R.drawable.ic_trousers, "Shoes"));
        allProducts.add(new Product("Slim Fit Cargo", "Ksh 2,800", R.drawable.ic_trousers, "Trousers"));
        allProducts.add(new Product("Gold Pendant", "Ksh 1,500", R.drawable.ic_tshirt, "Accessories"));
        allProducts.add(new Product("Evening Gown", "Ksh 7,000", R.drawable.ic_tshirt, "Dresses"));
        allProducts.add(new Product("Leather Loafers", "Ksh 5,200", R.drawable.ic_trousers, "Shoes"));
        allProducts.add(new Product("Classic Chinos", "Ksh 3,200", R.drawable.ic_trousers, "Trousers"));
        allProducts.add(new Product("Urban Snapback", "Ksh 1,200", R.drawable.ic_hoodie, "Accessories"));
        
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
