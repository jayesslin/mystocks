package com.team3.ms.mystocks.entity;
/*      "gid":"aapl",				*//*股票编号*//*
        "name":"苹果",				*//*股票名称*//*
        "lastestpri":"437.87",			*//*最新价*//*
        "openpri":"429.70",			*//*开盘价*//*
        "formpri":"431.72",			*//*前收盘价*//*
        "maxpri":"439.01",			*//*最高价*//*
        "minpri":"425.14",			*//*最低价*//*
        "uppic":"6.15",				*//*涨跌额*//*
        "limit":"1.42",				*//*涨跌幅%*//*
        "traAmount":"16884191",			*//*成交量（股）*//*
        "avgTraNumber":"16910781",		*//*平均成交量（股）*//*
        "markValue":"411185326460",		*//*市值*//*
        "max52":"705.07",			*//*52周最高*//*
        "min52":"419.00",			*//*52周最低*//*
        "EPS":"44.16",				*//*美股收益*//*
        "priearn":"9.92",			*//*市盈率*//*
        "beta":"1.10",				*//*贝塔系数*//*
        "divident":"10.60",			*//*股息*//*
        "ROR":"10.24",				*//*收益率*//*
        "capital":"939058000",			*//*股本*//*
        "afterpic":"437.29",			*//*盘后价*//*
        "afterlimit":"-0.13",			*//*盘后涨跌幅%*//*
        "afteruppic":"-0.58",			*//*盘后涨跌额*//*
        "aftertime":"Mar 11 7:59PM EDT",	*//*盘后计算时间*//*
        "ustime":"Mar 11 4:00PM EDT",		*//*美国当前更新时间*//*
        "chtime":"2013-03-12 07:58:04"		*//*中国时间*/


/*列表名称对应*//*
         "cname": "苹果公司", *//*名称*//*
         "category": "计算机", *//*行业版块*//*
         "symbol": "AAPL", *//*代码*//*
         "price": "126.52", *//*最新价*//*
         "diff": "1.09", *//*涨跌额*//*
         "chg": "0.87", *//*涨跌幅*//*
         "preclose": "125.42", *//*昨收*//*
         "open": "126.90", *//*今开*//*
         "high": "126.94", *//*最高价*//*
         "low": "125.99", *//*最低价*//*
         "amplitude": "0.76%", *//*振幅*//*
         "volume": "30215693", *//*成交量*//*
         "mktcap": "741843599071", *//*市值*//*
         "market": "NASDAQ" *//*上市地*/
public class stockdetail {
    private String name;
    private String gid;
    private String lastestpri;
    private String maxpri;
    private String minpri;
    private String limit;
    private String traAmount;
    private String EPS;
    private String divident;
    private String afterpic;
    private String afterlimit;
    private String ustime;
    private  String uppic;
    public String getUppic() {
        return uppic;
    }

    public void setUppic(String uppic) {
        this.uppic = uppic;
    }


    public String getColor() {
        double colorvalue =  Double.parseDouble(getlimit().toString());

        //涨是绿色
        if(colorvalue>0){
            return "green";
        }
        //跌是红色
        else return "red";
    }
    public String getname() {
        return name;
    }
    public String getgid() {
        return gid;
    }
    public String getlastestpri() {
        return lastestpri;
    }
    public String getmaxpri() {
        return maxpri;
    }
    public String getminpri() {
        return minpri;
    }
    public String getlimit() {
        return limit;
    }
    public String gettraAmount() {
        return traAmount;
    }
    public String getEPS() {
        return EPS;
    }
    public String getdivident() {
        return divident;
    }
    public String getafterpic() {
        return afterpic;
    }
    public String getafterlimit() {
        return afterlimit;
    }
    public String getustime() {
        return ustime;
    }

    public stockdetail(String name, String gid,String lastestpri, String maxpri, String minpri,
                       String limit, String traAmount, String EPS,String uppic, String afterpic, String afterlimit, String ustime)
    {
        this.name=name;
        this.gid=gid;
        this.lastestpri=lastestpri;
        this.maxpri=maxpri;
        this.minpri=minpri;
        this.limit=limit;
        this.traAmount=traAmount;
        this.EPS=EPS;
        this.uppic=uppic;
        this.afterpic=afterpic;
        this.afterlimit=afterlimit;
        this.ustime=ustime;

    }
    public stockdetail(){};





}
