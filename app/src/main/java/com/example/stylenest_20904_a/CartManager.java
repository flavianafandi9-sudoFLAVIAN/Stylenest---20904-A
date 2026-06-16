package com.example.stylenest_20904_a;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void addProduct(Product product) {
        cartItems.add(product);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cartItems) {
            try {
                String priceStr = product.getPrice().replace("Ksh ", "").replace(",", "");
                double unitPrice = Double.parseDouble(priceStr);
                total += unitPrice * product.getQuantity();
            } catch (Exception e) {
                // Ignore if price format is wrong
            }
        }
        return total;
    }
}
