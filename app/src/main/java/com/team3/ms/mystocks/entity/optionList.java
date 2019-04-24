package com.team3.ms.mystocks.entity;

import java.util.ArrayList;
import java.util.List;

public class optionList {
    public List<option> option_list= new ArrayList<option>();

    option o1 = new option("10001811","Call","0.0001","87932","06/24/2019");
    option o2 = new option("10001819","Call","0.0001","20437","09/24/2019");
    option o3 = new option("10001677","Call","0.8154","6032","09/24/2019");
    option o4 = new option("10001637","Call","0.8306","564","06/24/2019");
    option o5 = new option("10001682","Call","0.5832","5656","09/24/2019");
    option o6 = new option("10001503","Call","0.5832","667","06/24/2019");
    option o7 = new option("10001671","Call","0.9141","3168","06/24/2019");
    option o8 = new option("10001597","Call","0.7298","665","09/24/2019");
    option o9 = new option("10001629","Call","0.7755","439","06/24/2019");
    option o10 = new option("10001621","Call","0.7719","517","06/24/2019");
    option o11 = new option("10001826","Put","0.4693","5542","06/24/2019");
    option o12 = new option("10001824","Put","0.4452","885","06/24/2019");
    option o13 = new option("10001818","Put","0.3906","2618","09/24/2019");
    option o14 = new option("10001816","Put","0.3575","2350","06/24/2019");
    option o15 = new option("10001814","Put","0.3437","3806","05/24/2019");
    option o16 = new option("10001810","Put","0.3208","2539","09/24/2019");
    option o17 = new option("10001796","Put","0.1414","3389","05/24/2019");
    option o18 = new option("10001798","Put","0.1719","4414","06/24/2019");
    option o19 = new option("10001808","Put","0.2765","3261","06/24/2019");
    option o20 = new option("10001802","Put","0.2544","2071","09/24/2019");










    public List<option> getOption_list() {
        option_list.add(o1);
        option_list.add(o2);
        option_list.add(o3);
        option_list.add(o4);
        option_list.add(o5);
        option_list.add(o6);
        option_list.add(o7);
        option_list.add(o8);
        option_list.add(o9);
        option_list.add(o10);
        option_list.add(o11);
        option_list.add(o12);
        option_list.add(o13);
        option_list.add(o14);
        option_list.add(o15);
        option_list.add(o16);
        option_list.add(o17);
        option_list.add(o18);
        option_list.add(o19);
        option_list.add(o20);

        return option_list;
    }
}


