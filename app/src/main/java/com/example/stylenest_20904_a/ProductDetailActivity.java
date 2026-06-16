package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private int quantity = 1;
    private String selectedSize = "M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String name = getIntent().getStringExtra("product_name");
        String price = getIntent().getStringExtra("product_price");
        int image = getIntent().getIntExtra("product_image", 0);
        String category = getIntent().getStringExtra("product_category");

        ImageView ivProduct = findViewById(R.id.detail_image);
        TextView tvName = findViewById(R.id.detail_name);
        TextView tvPrice = findViewById(R.id.detail_price);
        Button btnAddToCart = findViewById(R.id.btn_add_to_cart_detail);
        
        TextView tvQuantity = findViewById(R.id.tv_quantity);
        TextView btnMinus = findViewById(R.id.tv_minus);
        TextView btnPlus = findViewById(R.id.tv_plus);
        RadioGroup rgSizes = findViewById(R.id.rg_sizes);

        tvName.setText(name);
        tvPrice.setText(price);
        ivProduct.setImageResource(image);

        // Quantity Logic
        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        // Size Selection Logic
        rgSizes.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) {
                selectedSize = rb.getText().toString();
            }
        });

        btnAddToCart.setOnClickListener(v -> {
            Product product = new Product(name, price, image, category, selectedSize, quantity);
            CartManager.getInstance().addProduct(product);
            Toast.makeText(this, name + " (" + selectedSize + " x" + quantity + ") added to cart", Toast.LENGTH_SHORT).show();
        });
    }
}
