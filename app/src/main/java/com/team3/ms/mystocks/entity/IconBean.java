package com.team3.ms.mystocks.entity;

public class IconBean {
    public String IconName;
    public int Icon_Image_Id;

    public String getIconName() {
        return IconName;
    }

    public void setIconName(String iconName) {
        IconName = iconName;
    }

    public int getIcon_Image_Id() {
        return Icon_Image_Id;
    }

    public void setIcon_Image_Id(int icon_Image_Id) {
        Icon_Image_Id = icon_Image_Id;
    }

    public IconBean(String s,int image_id){
        IconName=s;
        Icon_Image_Id=image_id;
    }
}
