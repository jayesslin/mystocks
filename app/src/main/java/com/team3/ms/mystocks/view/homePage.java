package com.team3.ms.mystocks.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.team3.ms.mystocks.R;
<<<<<<< HEAD
import com.team3.ms.mystocks.entity.IconAdapter;
import com.team3.ms.mystocks.entity.IconBean;

import java.util.ArrayList;
import java.util.List;

=======
import com.team3.ms.mystocks.entity.stock;
import com.team3.ms.mystocks.tools.Stocks_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

>>>>>>> 21e5eca1ad6bb278c6b1df1de63efeceb75f95e6
public class homePage extends AppCompatActivity {
    private List<IconBean> mIconBeenList = new ArrayList<>();
    private ListView lv;
    private TextView main_home,Stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        lv=(ListView)findViewById(R.id.listView);
        initData();
        lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));



<<<<<<< HEAD
        main_home = (TextView)findViewById(R.id.main_home);
        Stock = (TextView)findViewById(R.id.Stock);
=======
        @Override
        protected stock doInBackground(String... params) {
            Stocks_provider sp = new Stocks_provider();
            stock stock = new stock();
            try {
                String res = sp.getStock(params[0]);
                String result = "";
                JSONObject object = new JSONObject(res);
                // JSONObject object =  new JSONObject(result);
                //System.out.println(object.get("status"));
                //Log.i("*******", "res ：" + object.getString("status").toString());
                JSONArray stock_result = object.optJSONArray("result");
                JSONObject jsonObject = (JSONObject) stock_result.get(0);
                JSONObject data = jsonObject.getJSONObject("data");
                String gid =data.getString("gid");
                String openpri =data.getString("openpri");
                String lastestpri =data.getString("lastestpri");
                String uppic =data.getString("uppic");
                String limit =data.getString("limit");
                String ustime =data.getString("ustime");
                return new stock(gid,openpri, lastestpri,uppic,limit,ustime);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stock;
        }
        @Override
        protected void onPostExecute(stock st) {

                Log.i("***********","stock_gid:"+ st.getGid());
                Log.i("***********","开盘价:"+st.getOpenpri());
                Log.i("***********","更新价格:"+st.getLastestpri());
                Log.i("***********","颜色："+st.getColor());
                Log.i("***********","时间："+st.getUstime());

        }
>>>>>>> 21e5eca1ad6bb278c6b1df1de63efeceb75f95e6

    }
    private void initData(){
        IconBean Add = new IconBean("ADD",R.drawable.regi_background);
        mIconBeenList.add(Add);
        IconBean Close = new IconBean("Jayess",R.drawable.youxiang);
        mIconBeenList.add(Close);
        IconBean ZYZ = new IconBean("ZYZ",R.drawable.youxiang);
        mIconBeenList.add(ZYZ);
        IconBean ZX = new IconBean("ZX",R.drawable.youxiang);
        mIconBeenList.add(ZX);
        IconBean XBY = new IconBean("XBY",R.drawable.youxiang);
        mIconBeenList.add(XBY);
        IconBean JLXN = new IconBean("JLXN",R.drawable.youxiang);
        mIconBeenList.add(JLXN);
        IconBean WZDJ = new IconBean("WZDJ",R.drawable.youxiang);
        mIconBeenList.add(WZDJ);
    }
}
