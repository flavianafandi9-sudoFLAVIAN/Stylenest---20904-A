package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setupRecyclerView(view.findViewById(R.id.rv_featured));
        setupRecyclerView(view.findViewById(R.id.rv_new_arrivals));

        EditText etHomeSearch = view.findViewById(R.id.et_home_search);
        etHomeSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                String query = etHomeSearch.getText().toString().trim();
                if (!query.isEmpty()) {
                    navigateToSearch(query);
                }
                return true;
            }
            return false;
        });

        return view;
    }

    private void navigateToSearch(String query) {
        Fragment searchFragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString("search_query", query);
        searchFragment.setArguments(args);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).loadFragment(searchFragment);
            // Also update bottom nav selection to categories
            BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_navigation);
            if (bottomNav != null) {
                bottomNav.setSelectedItemId(R.id.navigation_categories);
            }
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        List<Product> products = new ArrayList<>();
        // 8 sample products categorized correctly
        products.add(new Product("Silk Maxi Dress", "Ksh 4,500", R.drawable.ic_tshirt, "Dresses"));
        products.add(new Product("Urban Sneakers", "Ksh 3,800", R.drawable.ic_trousers, "Shoes"));
        products.add(new Product("Slim Fit Cargo", "Ksh 2,800", R.drawable.ic_trousers, "Trousers"));
        products.add(new Product("Gold Pendant", "Ksh 1,500", R.drawable.ic_tshirt, "Accessories"));
        products.add(new Product("Evening Gown", "Ksh 7,000", R.drawable.ic_tshirt, "Dresses"));
        products.add(new Product("Leather Loafers", "Ksh 5,200", R.drawable.ic_trousers, "Shoes"));
        products.add(new Product("Classic Chinos", "Ksh 3,200", R.drawable.ic_trousers, "Trousers"));
        products.add(new Product("Urban Snapback", "Ksh 1,200", R.drawable.ic_hoodie, "Accessories"));

        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}