package com.example.board.dto;

public class Order {
    private Long orderId;
    private String status;
    private String date;
    private String customerName;

    public Order() {}
    // Constructors, Getters, Setters
    public Order(Long orderId, String status, String date, String customerName) {
        this.orderId = orderId;
        this.status = status;
        this.date = date;
        this.customerName = customerName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
