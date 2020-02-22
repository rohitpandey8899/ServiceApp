package com.manish.obaa.rowitem;

public class RowItemAcServicePrice {

    private String service, price;

    public RowItemAcServicePrice(String service, String price) {
        this.service = service;
        this.price = price;
    }

    public String getService() {
        return service;
    }

    public String getPrice() {
        return price;
    }
}
