package com.example.stylenest_20904_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileFragment extends Fragment {

    private LinearLayout layoutLoggedIn, layoutLoggedOut;
    private TextView tvName, tvEmail;
    private Button btnLogout, btnGoToLogin;
    private RecyclerView rvOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        layoutLoggedIn = view.findViewById(R.id.layout_logged_in);
        layoutLoggedOut = view.findViewById(R.id.layout_logged_out);
        tvName = view.findViewById(R.id.profile_name);
        tvEmail = view.findViewById(R.id.profile_email);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnGoToLogin = view.findViewById(R.id.btn_go_to_login);
        rvOrders = view.findViewById(R.id.rv_order_history);

        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        updateUI();

        btnGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            UserManager.getInstance(getContext()).logout();
            updateUI();
        });

        return view;
    }

    private void updateUI() {
        UserManager userManager = UserManager.getInstance(getContext());
        if (userManager.isLoggedIn()) {
            layoutLoggedIn.setVisibility(View.VISIBLE);
            layoutLoggedOut.setVisibility(View.GONE);
            tvName.setText(userManager.getUserName());
            tvEmail.setText(userManager.getUserEmail());
            
            OrderAdapter adapter = new OrderAdapter(userManager.getUserOrders());
            rvOrders.setAdapter(adapter);
        } else {
            layoutLoggedIn.setVisibility(View.GONE);
            layoutLoggedOut.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
