package com.manish.obaa.rowitem;

public class RowItemMainCat {

    private String id, catName, img, super_id;

    public RowItemMainCat(String id, String catName, String img, String super_id) {
        this.id = id;
        this.catName = catName;
        this.img = img;
        this.super_id = super_id;
    }

    public String getId() {
        return id;
    }

    public String getCatName() {
        return catName;
    }

    public String getImg() {
        return img;
    }

    public String getSuper_id() {
        return super_id;
    }
}