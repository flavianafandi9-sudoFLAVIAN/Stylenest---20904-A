package com.example.stylenest_20904_a;

import java.util.List;

public class Order {
    private String orderId;
    private String date;
    private String total;
    private List<Product> items;

    public Order(String orderId, String date, String total, List<Product> items) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.items = items;
    }

    public String getOrderId() { return orderId; }
    public String getDate() { return date; }
    public String getTotal() { return total; }
    public List<Product> getItems() { return items; }
}
