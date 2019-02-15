package com.team3.ms.mystocks.entity;
/*
"gid":"aapl",				*//*股票编号*//*
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
public class stock {

    //股票代码
    private String gid;
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



    //getter setter
    public String getLastestpri() {
        return lastestpri;
    }

    public void setLastestpri(String lastestpri) {
        this.lastestpri = lastestpri;
    }

    public String getUppic() {
        return uppic;
    }

    public void setUppic(String uppic) {
        this.uppic = uppic;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
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

    public String getOpenpri() {
        return openpri;
    }

    public void setOpenpri(String openpri) {
        this.openpri = openpri;
    }
}
