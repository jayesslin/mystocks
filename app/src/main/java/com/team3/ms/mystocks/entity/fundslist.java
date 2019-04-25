package com.team3.ms.mystocks.entity;

import java.util.ArrayList;
import java.util.List;

public class fundslist {
    public List<funds> fund_list= new ArrayList<funds>();
    String[] PDFDX1 ={"green","green","green"};
    funds PDFDX = new funds("PDFDX","54.88","41.16%","27.83%","8.09%",PDFDX1);
    String[]  FAGOX1={"green","green","green"};
    funds FAGOX =new funds("FAGOX","80.29","33.50","24.65","16.13",FAGOX1);
   String[] MPEGX1={"green","green","green"};
   funds MPEGX=new funds("MPEGX","19.15","30.87","23.70","10.28",MPEGX1);






    public List<funds> getFund_list() {
        fund_list.add(PDFDX);
        fund_list.add(FAGOX);
        fund_list.add(MPEGX);
        return fund_list;
    }
}
