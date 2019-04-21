package com.team3.ms.mystocks.entity;

public class IncomeandLoss {
    public double[] getStock_buy_price() {
        return stock_buy_price;
    }

    private  double[] stock_buy_price={178.32,121.56,195.36,1820.7,273.54};
     private double[] stock_price = {17832,24321,9767.5,54621,3470.8};
     private String[] Stock_date= {"03-24-2019","02-15-2019","03-16-2019","04-01-2019","04-02-2019"};
    private String[] stock_shares ={"100","200","50","30","20"};
    public String[] getStock_date() {
        return Stock_date;
    }

    public String[] getStock_shares() {
        return stock_shares;
    }


     private double total_origin = 120012.3;
     public double[] getStock_price(){
         return  stock_price;
     }
     public double getTotal_origin(){
         return total_origin;
     }

}
