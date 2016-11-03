package adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwie.test.listview_checkbox.R;

import java.util.ArrayList;
import java.util.List;

import bean.Data;

/**
 * Created by qianhaohong on 2016/11/2.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    List<Data> dataAll;
    List<Data> datas=new ArrayList<Data>();
    boolean hide;
    public MyAdapter(Context context, List<Data> datas,boolean hide) {
        dataAll = datas;
        this.datas.addAll(dataAll);
        this.context = context;
        this.hide=hide;
    }
    public void check() {
        datas.clear();
        datas.addAll(dataAll);
        if(hide){
            List<Data> ds=new ArrayList<Data>();
            for(int i=0;i<datas.size();i++){
                Data d=datas.get(i);
                if(d.isCheck) {
                    ds.add(d);
                }
            }
            for(Data d:ds){
                datas.remove(d);
            }
        }
        super.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setHide(boolean ishide){
        this.hide=ishide;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        Data data=datas.get(position);
        if(convertView==null){
            convertView=View.inflate(context,R.layout.tem,null);
            vh=new ViewHolder();
            vh.checkbox= (CheckBox) convertView.findViewById(R.id.checkbox);
            vh.content=(TextView)convertView.findViewById(R.id.content);
            vh.create=(TextView)convertView.findViewById(R.id.create);
            vh.index=(TextView)convertView.findViewById(R.id.index);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        vh.content.setText(data.text);
        vh.index.setText(data.index+"");


        vh.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("check","check--->"+position+"/"+isChecked);
                datas.get(position).isCheck=isChecked;
               check();
            }
        });
        vh.checkbox.setChecked(data.isCheck);
        return convertView;
    }
    class ViewHolder{
        CheckBox checkbox;
        TextView content;
        TextView create;
        TextView index;
    }
}
