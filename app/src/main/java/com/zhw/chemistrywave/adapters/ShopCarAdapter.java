package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.FenleiDetailActivity;
import com.zhw.chemistrywave.activity.GoodsDetailActivity;
import com.zhw.chemistrywave.activity.ShopDetailActivity;
import com.zhw.chemistrywave.bean.ShopCar;
import com.zhw.chemistrywave.utils.NetConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/23.
 */

public class ShopCarAdapter extends BaseExpandableListAdapter {

    private static final int MODE_NORMAL = 0;
    private static final int MODE_EDIT = 1;
    private List<ShopCar.DataBean.ListBean> mList;
    private Map<String, List<ShopCar.DataBean.ListBean.CartBeansBean>> childrens;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private int currentMode = MODE_NORMAL;

    public ShopCarAdapter(List<ShopCar.DataBean.ListBean> mList, Map<String, List<ShopCar.DataBean.ListBean.CartBeansBean>> childrens, Context context) {
        this.mList = mList;
        this.childrens = childrens;
        this.context = context;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = mList.get(groupPosition).getShop_id() + "";
        return childrens.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<ShopCar.DataBean.ListBean.CartBeansBean> childs = childrens.get(mList.get(groupPosition).getShop_id() + "");

        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupHolder gholder = null;
        if (convertView == null) {
            gholder = new GroupHolder();
            convertView = View.inflate(context, R.layout.item_shop_car_lv, null);
            gholder.cb_check = (CheckBox) convertView.findViewById(R.id.cb_select_all);
            gholder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_listname);
            gholder.ivShopLogo = (ImageView) convertView.findViewById(R.id.iv_shop_logo);
            gholder.llClass = (LinearLayout) convertView.findViewById(R.id.ll_class);

            convertView.setTag(gholder);
        } else {
            gholder = (GroupHolder) convertView.getTag();
        }

        final ShopCar.DataBean.ListBean group = (ShopCar.DataBean.ListBean) getGroup(groupPosition);
        if (group != null) {
            gholder.tv_group_name.setText(group.getCom_name());
            Glide.with(context).load(NetConfig.baseurl + group.getCom_logo()).apply(MyApplication.options).into(gholder.ivShopLogo);

            gholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)

                {
                    group.setChoosed(((CheckBox) v).isChecked());
                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
                }
            });
            gholder.cb_check.setChecked(group.isChoosed());

            //组选框的点击  用于跳转
            gholder.llClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int user_id = group.getUser_id();
                    context.startActivity(new Intent(context, ShopDetailActivity.class).putExtra("user_id", String.valueOf(user_id)));
                }
            });
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ChildHolder cholder;
        if (convertView == null) {
            cholder = new ChildHolder();
            convertView = View.inflate(context, R.layout.item_listitem_lv, null);
            cholder.cb_check = (CheckBox) convertView.findViewById(R.id.iv_select);
            cholder.tv_product_desc = (TextView) convertView.findViewById(R.id.tv_name);//课程名称
            cholder.tv_product_params = (TextView) convertView.findViewById(R.id.tv_parameter);//课程名称
            cholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);//课程单价
            cholder.iv_increase = (TextView) convertView.findViewById(R.id.tv_add);//课程数量增加
            cholder.iv_decrease = (TextView) convertView.findViewById(R.id.tv_sub);//课程数量减少
            cholder.tv_count = (TextView) convertView.findViewById(R.id.tv_num);//课程数量
            cholder.ll_num = (LinearLayout) convertView.findViewById(R.id.ll_number);
            cholder.iv_imag = (ImageView) convertView.findViewById(R.id.iv_imag);
            cholder.tv_editnum = (TextView) convertView.findViewById(R.id.tv_editnum);
            cholder.ll_edit_num = (LinearLayout) convertView.findViewById(R.id.ll_edit_num);
            cholder.ll_course = (LinearLayout) convertView.findViewById(R.id.ll_course);

            // childrenMap.put(groupPosition, convertView);
            convertView.setTag(cholder);
        } else {
            // convertView = childrenMap.get(groupPosition);
            cholder = (ChildHolder) convertView.getTag();
        }
        final ShopCar.DataBean.ListBean.CartBeansBean product = (ShopCar.DataBean.ListBean.CartBeansBean) getChild(groupPosition, childPosition);

        if (product != null) {

            if (currentMode == MODE_EDIT) {
                cholder.ll_edit_num.setVisibility(View.VISIBLE);
                cholder.ll_num.setVisibility(View.GONE);
            }
            if (currentMode == MODE_NORMAL) {
                cholder.ll_num.setVisibility(View.VISIBLE);
                cholder.ll_edit_num.setVisibility(View.GONE);
            }

            cholder.tv_editnum.setText((product.getGoods_num() != 0 ? product.getGoods_num() : 0) + "");
            cholder.tv_product_desc.setText(product.getGoods_name());
            cholder.tv_price.setText("Price：$" + product.getGoods_price());
            cholder.tv_product_params.setText("Selected: “" + product.getColor_power() + "”，“" + product.getColor_light() + "”，“" + product.getPackage_opt() + "”");
            cholder.tv_count.setText((product.getGoods_num() != 0 ? product.getGoods_num() : 0) + "");
            cholder.cb_check.setChecked(product.isChecked());
            Glide.with(context).load(NetConfig.baseurl + product.getProduct_picture()).apply(MyApplication.options).into(cholder.iv_imag);
            cholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    product.setChecked(((CheckBox) v).isChecked());
                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            cholder.iv_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露增加接口
                    cholder.tv_editnum.setText(cholder.tv_count.getText().toString());
                }
            });
            cholder.iv_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露删减接口
                    cholder.tv_editnum.setText(cholder.tv_count.getText().toString());
                }
            });

            //子选框的点击  跳转使用
            cholder.ll_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, GoodsDetailActivity.class)
                            .putExtra("mer_id", product.getGoods_id()));
                    //点击商品进入商品详情
//                    Intent intent = new Intent(context, CourseHomePagerActivity.class);
//                    intent.putExtra("school_id", product.getSchool_id());
//                    intent.putExtra("course_id", product.getCourse_id());
//                    intent.putExtra("schooluid", product.getUser_id());
//                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void switchMode() {

        currentMode = MODE_EDIT;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void switchNormalMode() {

        currentMode = MODE_NORMAL;

        notifyDataSetChanged();
    }


    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);
    }

    public interface schoolClick {

    }

    /**
     * 组元素绑定器
     */
    private class GroupHolder {
        CheckBox cb_check;
        TextView tv_group_name;
        ImageView ivShopLogo;
        LinearLayout llClass;
    }

    /**
     * 子元素绑定器
     */
    private class ChildHolder {
        CheckBox cb_check;

        TextView tv_editnum;
        TextView tv_product_params;
        TextView tv_product_desc;
        TextView tv_price;
        TextView iv_increase;
        TextView tv_count;
        TextView iv_decrease;
        ImageView iv_imag;
        LinearLayout ll_num;
        LinearLayout ll_edit_num;
        LinearLayout ll_course;

    }
}
