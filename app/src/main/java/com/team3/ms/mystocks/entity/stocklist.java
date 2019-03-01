package com.team3.ms.mystocks.entity;

public class stocklist {
    private String name;

    //股票代码
    private String gid;

    public String getOpenpri() {
        return openpri;
    }

    public void setOpenpri(String openpri) {
        this.openpri = openpri;
    }

    //开盘价
    private String openpri;
    //最新价
    private String lastestpri;
    //涨跌额
    private String uppic;
    //涨跌幅
    private String limit;
    //股票当前颜色
    private String color;
    private String ustime;

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }


    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
    public String getLastestpri() {
        return lastestpri;
    }

    public void setLastestpri(String lastestpri) {
        this.lastestpri = lastestpri;
    }

    public String getColor() {
        double colorvalue =  Double.parseDouble(getLimit().toString());

        //涨是绿色
        if(colorvalue>0){
            return "green";
        }
        //跌是红色
        else return "red";
    }


    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }



    public stocklist(String gid,String openpri,String lastestpri, String limit){
        this.gid=gid;
        this.openpri=openpri;

        this.lastestpri=lastestpri;

        this.limit=limit;


    }



}
