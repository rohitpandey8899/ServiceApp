package com.manish.obaa.rowitem;

public class RowItemHome {

    private String id,title,subTitle,imgUrl;

    public RowItemHome(String id, String title, String subTitle, String imgUrl) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
