package com.team3.ms.mystocks.entity;

public class funds {
    private String fund_symbol;
    private String fund_nav;
    private String fund_1y;
    private String fund_3y;
    private String fund_5y;
    private String[] color;


    public funds(String fund_symbol,String fund_nav,String fund_1y,String fund_3y,String fund_5y,String[] color){
        this.fund_symbol=fund_symbol;
        this.fund_nav=fund_nav;
        this.fund_1y=fund_1y;
        this.fund_3y=fund_3y;
        this.fund_5y=fund_5y;
        this.color=color;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }


    public String getFund_symbol() {
        return fund_symbol;
    }

    public void setFund_symbol(String fund_symbol) {
        this.fund_symbol = fund_symbol;
    }

    public String getFund_nav() {
        return fund_nav;
    }

    public void setFund_nav(String fund_nav) {
        this.fund_nav = fund_nav;
    }

    public String getFund_1y() {
        return fund_1y;
    }

    public void setFund_1y(String fund_1y) {
        this.fund_1y = fund_1y;
    }

    public String getFund_3y() {
        return fund_3y;
    }

    public void setFund_3y(String fund_3y) {
        this.fund_3y = fund_3y;
    }

    public String getFund_5y() {
        return fund_5y;
    }

    public void setFund_5y(String fund_5y) {
        this.fund_5y = fund_5y;
    }



}
