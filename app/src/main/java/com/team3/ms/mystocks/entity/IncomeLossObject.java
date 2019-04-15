package com.team3.ms.mystocks.entity;

public class IncomeLossObject {
    private String Symbol;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShares() {
        return Shares;
    }

    public void setShares(String shares) {
        Shares = shares;
    }

    private String date;
    private String Shares;

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    private String profit;
}
