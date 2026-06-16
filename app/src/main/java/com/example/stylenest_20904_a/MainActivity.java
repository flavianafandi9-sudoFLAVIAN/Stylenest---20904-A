package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View splashScreen = findViewById(R.id.splash_screen);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        updateCartBadge(bottomNav);

        // Splash logic: Show splash screen for 2.5 seconds, then load HomeFragment
        new Handler().postDelayed(() -> {
            splashScreen.animate().alpha(0f).setDuration(500).withEndAction(() -> {
                splashScreen.setVisibility(View.GONE);
                loadFragment(new HomeFragment());
            });
        }, 2500);

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.navigation_categories) {
                selectedFragment = new CategoriesFragment();
            } else if (itemId == R.id.navigation_cart) {
                selectedFragment = new CartFragment();
            } else if (itemId == R.id.navigation_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        if (bottomNav != null) {
            updateCartBadge(bottomNav);
        }
    }

    private void updateCartBadge(BottomNavigationView bottomNav) {
        int count = CartManager.getInstance().getCartItems().size();
        BadgeDrawable badge = bottomNav.getOrCreateBadge(R.id.navigation_cart);
        if (count > 0) {
            badge.setVisible(true);
            badge.setNumber(count);
            badge.setBackgroundColor(getResources().getColor(R.color.gold, getTheme()));
            badge.setBadgeTextColor(getResources().getColor(R.color.black, getTheme()));
        } else {
            badge.setVisible(false);
        }
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}