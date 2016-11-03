package com.bwie.test.listview_checkbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import bean.Data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    List<Data> datas=new ArrayList<Data>();
    private ListView lv;
    MyAdapter myAdapter;
    private boolean ishide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadData();
        myAdapter = new MyAdapter(MainActivity.this, datas,false);
        lv.setAdapter(myAdapter);
    }

    public void init() {
        lv = (ListView) findViewById(R.id.lv);
    }

    public void loadData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Data data = new Data();
            data.index = i;
            data.text = "item " + i;
            datas.add(data);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_all){
            check(true);
        }else if(v.getId()==R.id.btn_no){
            check(false);
        }else if(v.getId()==R.id.btn_hide){
            ishide=ishide==true?false:true;
            hide(ishide);
        }
    }
    public void hide(boolean ishide){
        Button btn = (Button)findViewById(R.id.btn_hide);
        if(ishide)
            btn.setText("取消隐藏");
        else
            btn.setText("隐藏已选");

        myAdapter.setHide(ishide);
        myAdapter.check();
    }
    public void check(boolean flag){
        for(Data d:datas){
            d.isCheck=flag;
            myAdapter.check();
        }
    }
}
