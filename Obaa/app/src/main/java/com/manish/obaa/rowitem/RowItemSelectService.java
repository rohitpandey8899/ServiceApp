package com.manish.obaa.rowitem;

public class RowItemSelectService {

    private String serviceName;
    private int price, quantity;

    public RowItemSelectService(String serviceName, int price, int quantity) {
        this.serviceName = serviceName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
