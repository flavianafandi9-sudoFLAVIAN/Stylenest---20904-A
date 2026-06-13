package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String name = getIntent().getStringExtra("product_name");
        String price = getIntent().getStringExtra("product_price");
        int image = getIntent().getIntExtra("product_image", 0);

        ImageView ivProduct = findViewById(R.id.detail_image);
        TextView tvName = findViewById(R.id.detail_name);
        TextView tvPrice = findViewById(R.id.detail_price);
        Button btnAddToCart = findViewById(R.id.btn_add_to_cart_detail);

        tvName.setText(name);
        tvPrice.setText(price);
        ivProduct.setImageResource(image);

        btnAddToCart.setOnClickListener(v -> {
            CartManager.getInstance().addProduct(new Product(name, price, image));
            Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
        });
    }
}
