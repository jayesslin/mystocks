package com.team3.ms.mystocks.entity;

import java.util.ArrayList;
import java.util.List;

public class fundslist {
    public List<funds> fund_list= new ArrayList<funds>();
    public List<funds> fund_list_all= new ArrayList<funds>();
    String[] PDFDX1 ={"green","green","green"};
    funds PDFDX = new funds("PDFDX","54.88","41.16%","27.83%","8.09$",PDFDX1);
    String[]  FAGOX1={"green","green","green"};
    funds FAGOX =new funds("FAGOX","80.29","33.50","24.65","16.13",FAGOX1);
    String[] MPEGX1={"green","green","green"};
    funds MPEGX=new funds("MPEGX","19.15","30.87","23.70","10.28",MPEGX1);
    funds MPEGX0=new funds("CPOBX","29.22","28.97","27.89","16.73",MPEGX1);
    funds MPEGX2=new funds("MSSGX","12.51","27.32","19.53","6.42",MPEGX1);
    funds MPEGX3=new funds("FSMEX","50.49","25.33","22.89","18.12",MPEGX1);
    funds MPEGX4=new funds("LAGWX","23.63","24.55","23.44","9.31",MPEGX1);
    funds MPEGX5=new funds("TEFQX","13.88","23.77","33.16","19.23",MPEGX1);
    funds MPEGX6=new funds("HHCSX","14.87","23.70","10.78","1.28",MPEGX1);







    public List<funds> getFund_list() {
        fund_list.add(PDFDX);
        fund_list.add(FAGOX);
        fund_list.add(MPEGX);
        return fund_list;
    }

    public List<funds> getAllFund_list() {
        fund_list_all.add(PDFDX);
        fund_list_all.add(FAGOX);
        fund_list_all.add(MPEGX);
        fund_list_all.add(MPEGX0);
        fund_list_all.add(MPEGX2);
        fund_list_all.add(MPEGX3);
        fund_list_all.add(MPEGX4);
        fund_list_all.add(MPEGX5);
        fund_list_all.add(MPEGX6);

        return fund_list_all;
    }
}
