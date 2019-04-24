package com.team3.ms.mystocks.entity;

public class option {
    private String option_symbol;
    private String option_type;
    private String option_price;
    private String option_position;
    private String option_date;


    public option(String option_symbol,String option_type,String option_price,String option_position,String option_date){
        this.option_symbol=option_symbol;
        this.option_type=option_type;
        this.option_price=option_price;
        this.option_position=option_position;
        this.option_date=option_date;
    }



    public String getOption_symbol() {
        return option_symbol;
    }

    public void setOption_symbol(String option_symbol) {
        this.option_symbol = option_symbol;
    }

    public String getOption_type() {
        return option_type;
    }

    public void setOption_type(String option_type) {
        this.option_type = option_type;
    }

    public String getOption_price() {
        return option_price;
    }

    public void setOption_price(String option_price) {
        this.option_price = option_price;
    }

    public String getOption_position() {
        return option_position;
    }

    public void setOption_position(String option_position) {
        this.option_position = option_position;
    }

    public String getOption_date() {
        return option_date;
    }

    public void setOption_date(String option_date) {
        this.option_date = option_date;
    }



}

