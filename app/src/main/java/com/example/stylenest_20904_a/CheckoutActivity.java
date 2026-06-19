package com.example.stylenest_20904_a;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        String totalStr = String.format(Locale.getDefault(), "Ksh %.2f", total);
        tvTotal.setText("Total: " + totalStr);

        btnPlaceOrder.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save Order to History
                String date = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(new Date());
                String orderId = "#SN" + (int)(Math.random() * 9000 + 1000);
                
                Order newOrder = new Order(orderId, date, totalStr, new ArrayList<>(CartManager.getInstance().getCartItems()));
                UserManager.getInstance(this).addOrder(newOrder);

                Toast.makeText(this, "Order " + orderId + " placed successfully!", Toast.LENGTH_LONG).show();
                CartManager.getInstance().clearCart();
                finish();
            }
        });
    }
}
