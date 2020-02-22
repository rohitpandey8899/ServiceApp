package com.manish.obaa.rowitem;

public class RowItemAddress {

    private String id, name, houseNo,colony,city;

    public RowItemAddress(String id, String name, String houseNo, String colony, String city) {
        this.id = id;
        this.name = name;
        this.houseNo = houseNo;
        this.colony = colony;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getColony() {
        return colony;
    }

    public String getCity() {
        return city;
    }
}
