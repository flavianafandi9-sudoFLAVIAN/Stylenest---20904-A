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
        // Updated with your new products
        products.add(new Product("Butterfly Watch Set", "Ksh 3,500", R.drawable.watch_set, "Accessories"));
        products.add(new Product("Evening Silk Gown", "Ksh 8,500", R.drawable.maroon_dress, "Dresses"));
        products.add(new Product("African Print Top", "Ksh 4,200", R.drawable.african_top, "Trousers"));
        products.add(new Product("Adidas Samba", "Ksh 6,800", R.drawable.samba_sneakers, "Shoes"));
        products.add(new Product("Summer Palm Set", "Ksh 3,200", R.drawable.palm_shirt, "Trousers"));
        products.add(new Product("Premium Handbag", "Ksh 5,500", R.drawable.black_handbag, "Accessories"));
        products.add(new Product("Toddler Ruffle Set", "Ksh 2,800", R.drawable.kids_set, "Dresses"));
        products.add(new Product("Blue Flame Hoodie", "Ksh 4,500", R.drawable.flame_hoodie, "Trousers"));

        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}