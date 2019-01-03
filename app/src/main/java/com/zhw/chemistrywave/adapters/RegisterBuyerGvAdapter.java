package com.zhw.chemistrywave.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.PingShiGouMai;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by axehome on 2017/4/28.
 */

public class RegisterBuyerGvAdapter extends BaseAdapter{
    private Context mContext;
    private List<PingShiGouMai.DataBean> mList;
    private int lastPosition = -1;                                //记录上一次选中的图片位置，-1表示未选中
    public int now=1;
    private boolean isFlag=true;
    private Vector<Boolean> vector = new Vector<Boolean>();       // 定义一个向量作为选中与否容器
    /**
     * 用了存放点击的gridview的item的pos和这个pos的选中状态
     */
    private Map<Integer,Boolean> gvChooseMap=new HashMap<Integer, Boolean>();
    private cbClick cbClick;
    public RegisterBuyerGvAdapter(Context mContext, List<PingShiGouMai.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.cbClick=cbClick;
//            for (int i = 0; i < mList.size(); i++) {
//            vector.add(false);
//        }
        for (int i = 0; i < mList.size(); i++) {
            gvChooseMap.put(i,false);
        }
    }
    public void setCbClick(cbClick cbClick) {
        this.cbClick = cbClick;
    }
    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        DialogViewHolder holder=null;
        if (view == null) {
            view= LayoutInflater.from(mContext).inflate(R.layout.item_gv_registerbuyer,null);
            holder=new DialogViewHolder();
            holder.tvName= (CheckBox) view.findViewById(R.id.tv_itemgvregisterbuyer_name);

            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else{
            holder= (DialogViewHolder) view.getTag();
        }


//        if(vector.elementAt(i) == true){
//          // holder.rbGift.setImageResource(R.drawable.rb_true);
//            holder.tvName.setBackground(mContext.getResources().getDrawable(R.drawable.shape_gvregister_selected));
//
//        }else{
//            //holder.rbGift.setImageResource(R.drawable.rb_false);
//            holder.tvName.setBackground(mContext.getResources().getDrawable(R.drawable.shape_gvregister_unselected));
//
//        }
        final DialogViewHolder finalHolder = holder;
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gvChooseMap.put(i, finalHolder.tvName.isChecked());
                notifyDataSetChanged();
            }
        });
        boolean res=false;
//        if (gvChooseMap.get(i)){
//            res=true;
//        }else{
//            res=false;
//        }
        holder.tvName.setText(mList.get(i).getProduct_name());
        return view;
    }
    class DialogViewHolder{
        CheckBox tvName;

    }
    public Map<Integer, Boolean> getDeleteMap() {
        return gvChooseMap;
    }
    /**
     * 修改选中时的状态
     * @param position
     */
    public String changeState(int position){
        if(lastPosition != -1)
            vector.setElementAt(false, lastPosition);                   //取消上一次的选中状态
        vector.setElementAt(!vector.elementAt(position), position);     //直接取反即可
        lastPosition = position;                                        //记录本次选中的位置
        notifyDataSetChanged();                                         //通知适配器进行更新
        String integralAmount = mList.get(position).getProduct_name();
        return integralAmount;
    }
    public interface cbClick{
        void onCbClick(int position);
    }
}
