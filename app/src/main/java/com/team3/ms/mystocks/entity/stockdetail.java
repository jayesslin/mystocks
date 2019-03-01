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
    private  String img;
    private String companyName;
    private String  high;
    private String  low;
    private String  changePercent;
    private String week52High;
    private String week52low;
    private   String pe_ratio;
    private String marketCap;
    private String avl_volume ;
    private String latestVolume;

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    private  String close;

    public String getPe_ratio() {
        return pe_ratio;
    }

    public void setPe_ratio(String pe_ratio) {
        this.pe_ratio = pe_ratio;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getAvl_volume() {
        return avl_volume;
    }

    public void setAvl_volume(String avl_volume) {
        this.avl_volume = avl_volume;
    }

    public String getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(String latestVolume) {
        this.latestVolume = latestVolume;
    }


    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    private String primaryExchange;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    private String open;
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getWeek52High() {
        return week52High;
    }

    public void setWeek52High(String week52High) {
        this.week52High = week52High;
    }

    public String getWeek52low() {
        return week52low;
    }

    public void setWeek52low(String week52low) {
        this.week52low = week52low;
    }




    public String getUppic() {
        return uppic;
    }

    public void setUppic(String uppic) {
        this.uppic = uppic;
    }


    public String getColor() {
        double colorvalue;
        if(getlimit()!=null) {
            colorvalue = Double.parseDouble(getlimit().toString());
        }
        else {
             colorvalue = Double.parseDouble(getChangePercent().toString());
        }

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
    public String getimg() {
        return img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public stockdetail(String name, String gid, String lastestpri, String maxpri, String minpri,
                       String limit, String traAmount, String EPS, String uppic, String afterpic, String afterlimit, String ustime, String img)
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
        this.img=img;

    }
    public stockdetail(){};




    public stockdetail(String companyName, String gid,String openpri, String lastestpri, String high,
                       String low, String uppic, String changePercent,String week52High, String week52Low, String ustime)
    {
        this.companyName=companyName;
        this.gid=gid;
        this.lastestpri=lastestpri;
        this.high=high;
        this.low=low;
        this.uppic=uppic;
        this.changePercent=changePercent;
        this.week52High=week52High;
        this.week52low=week52Low;
        this.open=openpri;
        this.ustime=ustime;


    }




}
