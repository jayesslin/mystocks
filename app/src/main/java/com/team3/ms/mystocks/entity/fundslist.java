package com.team3.ms.mystocks.entity;

import java.util.ArrayList;
import java.util.List;

public class fundslist {
    public List<funds> fund_list= new ArrayList<funds>();
    public List<funds> myfund_list= new ArrayList<funds>();
    String[] PDFDX1 ={"green","green","green"};
    funds PDFDX = new funds("PDFDX","54.88","41.16%","27.83%","8.09%",PDFDX1);
    String[]  FAGOX1={"green","green","green"};
    funds FAGOX =new funds("FAGOX","80.29","33.50","24.65","16.13",FAGOX1);
    String[] MPEGX1={"green","green","green"};
    funds MPEGX=new funds("MPEGX","19.15","30.87","23.70","10.28",MPEGX1);


    String[]  IMSIX1={"green","green","red"};
    funds IMSIX=new funds("IMSIX","2.61","11.35%","0.50%","-7.88%",IMSIX1);
    String[]  MLPPX1={"green","green","red"};
    funds MLPPX =new funds("MLPPX","7.57","10.32%","8.82%","-3.08%",MLPPX1);
    String[]  DXRLX1={"green","red","green"};
    funds DXRLX=new funds("DXRLX","71.07","28.12%","-5.57%","20.22%",DXRLX1);
    String[]  INFRX1={"green","green","red"};
    funds  INFRX=new funds(" INFRX","7.86","9.34%","7.98%","-2.75%",IMSIX1);
    String[]  SWOBX1={"green","green","green"};
    funds SWOBX=new funds("SWOBX","15.48","5.18%","8.78%","6.76%",SWOBX1);

    String[]  MLPFX1={"green","green","red"};
    funds MLPFX=new funds("MLPFX","7.62","12.17%","4.85%","-1.98%",MLPFX1);
    String[]  OPGSX1={"green","green","green"};
    funds  OPGSX=new funds("OPGSX","14.73","3.37%","6.69%","0.13%",OPGSX1);
    String[]  FICDX1={"green","green","green"};
    funds FICDX=new funds("FICDX","51.42","3.88%","6.82%","1.84%",FICDX1);


    funds MPEGX0=new funds("CPOBX","29.22","28.97%","27.89%","16.73%",MPEGX1);
    funds MPEGX2=new funds("MSSGX","12.51","27.32%","19.53%","6.42%",MPEGX1);
    funds MPEGX3=new funds("FSMEX","50.49","25.33%","22.89%","18.12%",MPEGX1);
    String[] WAHYX1 ={"green","green","green"};
    funds WAHYX = new funds("PDFDX","$7.91","5.31%","8.37%","2.93%",WAHYX1);
    String[]  PWTAX1={"green","green","green"};
    funds PWTAX =new funds("FAGOX","$46.34","4.16%","8.39%","6.54%",PWTAX1);
    String[] MVCAX1={"green","green","green"};
    funds MVCAX=new funds("MPEGX","$23.12","3.18%","8.46%","6.54%",MVCAX1);

    public List<funds> getFund_list() {
        fund_list.add(MPEGX);
        fund_list.add(IMSIX);
        fund_list.add(MLPPX);
        fund_list.add(DXRLX);
        fund_list.add(PDFDX);
        fund_list.add(FAGOX);
        fund_list.add(INFRX);
        fund_list.add(SWOBX);
        fund_list.add(MLPFX);
        fund_list.add( OPGSX);
        fund_list.add(MPEGX0);
        fund_list.add(MPEGX2);
        fund_list.add(MPEGX3);
        fund_list.add(WAHYX);
        fund_list.add(PWTAX);
        fund_list.add(MVCAX);
        return fund_list;
    }
    public List<funds> getMy_list() {
        myfund_list.add(PDFDX);
        myfund_list.add(FAGOX);
        myfund_list.add(INFRX);
        myfund_list.add(SWOBX);
        myfund_list.add(MLPFX);
        return myfund_list;
    }
}
