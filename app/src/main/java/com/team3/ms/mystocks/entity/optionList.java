package com.team3.ms.mystocks.entity;

import java.util.ArrayList;
import java.util.List;

public class optionList {
    public List<option> option_list= new ArrayList<option>();
    String[] PDFDX1 ={"green","green","green"};
    option PDFDX = new option("PDFDX","54.88","41.16%","27.83%","8.09$",PDFDX1);
    String[]  FAGOX1={"green","green","green"};
    option FAGOX =new option("FAGOX","80.29","33.50","24.65","16.13",FAGOX1);
    String[] MPEGX1={"green","green","green"};
    option MPEGX=new option("MPEGX","19.15","30.87","23.70","10.28",MPEGX1);






    public List<option> getOption_list() {
        option_list.add(PDFDX);
        option_list.add(FAGOX);
        option_list.add(MPEGX);
        return option_list;
    }
}


