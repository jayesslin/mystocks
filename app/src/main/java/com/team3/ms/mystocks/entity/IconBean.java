package com.team3.ms.mystocks.entity;

public class IconBean {
    public String IconName;
    public String Icon_Image_Id;
    public String new_url;
    public String getNew_url() {
        return new_url;
    }

    public void setNew_url(String new_url) {
        this.new_url = new_url;
    }


    public String getIconName() {
        return IconName;
    }

    public void setIconName(String iconName) {
        IconName = iconName;
    }

    public String getIcon_Image_Id() {
        return Icon_Image_Id;
    }

    public void setIcon_Image_Id(String icon_Image_Id) {
        Icon_Image_Id = icon_Image_Id;
    }

    public IconBean(String s,String image_id){
        IconName=s;
        Icon_Image_Id=image_id;
    }
}
