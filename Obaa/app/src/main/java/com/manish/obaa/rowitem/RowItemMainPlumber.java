package com.manish.obaa.rowitem;

/**
 * Created by MAANSI on 6/27/2019.
 */

public class RowItemMainPlumber {
    private String id, catName, img, super_id;

    public RowItemMainPlumber(String id, String catName, String img, String super_id) {
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
