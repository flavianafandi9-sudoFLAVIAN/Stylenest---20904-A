package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TextView tvTotal = findViewById(R.id.checkout_total);
        EditText etName = findViewById(R.id.et_name);
        EditText etPhone = findViewById(R.id.et_phone);
        EditText etAddress = findViewById(R.id.et_address);
        Button btnPlaceOrder = findViewById(R.id.btn_place_order);

        double total = CartManager.getInstance().calculateTotal();
        tvTotal.setText("Total: Ksh " + String.format("%.2f", total));

        btnPlaceOrder.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_LONG).show();
                CartManager.getInstance().clearCart();
                finish();
            }
        });
    }
}
