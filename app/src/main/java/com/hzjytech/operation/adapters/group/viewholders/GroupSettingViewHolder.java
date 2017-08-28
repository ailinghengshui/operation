package com.hzjytech.operation.adapters.group.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzjytech.operation.R;
import com.hzjytech.operation.entity.GroupInfo;
import com.hzjytech.operation.utils.MyMath;
import com.hzjytech.operation.utils.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hehongcan on 2017/7/18.
 */
public class GroupSettingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_money_off)
    public TextView mTvMoneyOff;
    @BindView(R.id.tv_off_time)
    public TextView mTvOffTime;
    @BindView(R.id.tv_discount)
    public TextView mTvDiscount;
    @BindView(R.id.tv_discount_time)
    public TextView mTvDiscountTime;
    @BindView(R.id.ll_group_settion_container)
    public LinearLayout container;

    public GroupSettingViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setData(Context context, GroupInfo groupInfo) {
        GroupInfo.BasicInfoBean info = groupInfo.getBasicInfo();
        if(groupInfo.getDiscount()==0&&groupInfo.getDiscountM()==0){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height=0;
            params.setMargins(0,0,0,0);
            container.setLayoutParams(params);
        }
        mTvDiscount.setText(context.getString(R.string.discount,(MyMath.getIntOrDouble(groupInfo.getDiscount()*10)+"")));
        mTvMoneyOff.setText(context.getString(R.string.money_off,
                MyMath.getIntOrDouble(groupInfo.getDiscountM())+"",MyMath.getIntOrDouble(groupInfo.getDiscountJ())+""));
        if(info.getDiscountStartTime()==null||info.getDiscountStartTime().equals("")){
            mTvDiscountTime.setText("");
        }else{
            mTvDiscountTime.setText(TimeUtil.getLong2FromLong(info.getDiscountStartTime())+"/"+TimeUtil.getLong2FromLong(info.getDiscountEndTime()));
        }
       if(info.getMoneyOfStartTime()==null||info.getMoneyOfStartTime().equals("")){
           mTvOffTime.setText("");
       }else{
           mTvOffTime.setText(TimeUtil.getLong2FromLong(info.getMoneyOfStartTime())+"/"+TimeUtil.getLong2FromLong(info.getMoneyOfEndTime()));
       }

    }
}
