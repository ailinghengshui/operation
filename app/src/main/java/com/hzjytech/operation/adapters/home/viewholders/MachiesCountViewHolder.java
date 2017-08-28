package com.hzjytech.operation.adapters.home.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hzjytech.operation.R;
import com.hzjytech.operation.constants.Constants;
import com.hzjytech.operation.entity.Machies;
import com.hzjytech.operation.inter.MachineStateClickListener;
import com.hzjytech.operation.utils.DensityUtil;
import com.hzjytech.operation.widgets.BadgeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hehongcan on 2017/4/24.
 */
public class MachiesCountViewHolder extends RecyclerView.ViewHolder {
    private MachineStateClickListener machineStateChangeLister;
    @BindView(R.id.item_machines_error)
    LinearLayout itemMachinesError;
    @BindView(R.id.item_machines_shortage)
    LinearLayout itemMachinesShortage;
    @BindView(R.id.item_machines_offline)
    LinearLayout itemMachinesOffline;
    @BindView(R.id.item_machines_lock)
    LinearLayout itemMachinesLock;
    @BindView(R.id.item_machines_unoperation)
    LinearLayout itemMachinesUnoperation;
    @BindView(R.id.iv_error)
    ImageView ivError;
    @BindView(R.id.iv_shortage)
    ImageView ivShortage;
    @BindView(R.id.iv_offline)
    ImageView ivOffline;
    @BindView(R.id.iv_lock)
    ImageView ivLock;
    @BindView(R.id.iv_unoperation)
    ImageView ivUnoperation;

    public MachiesCountViewHolder(View machiesCountView, MachineStateClickListener machineStateChangeLister) {
        super(machiesCountView);
        ButterKnife.bind(this, machiesCountView);
        this.machineStateChangeLister = machineStateChangeLister;
    }

    public void setViewData(Context mContext, Object item, int position, int viewType) {
        BadgeView errorBadgeView = new BadgeView(mContext, ivError);
        errorBadgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        errorBadgeView.setBadgeBackgroundColor(mContext.getResources().getColor(R.color.light_red));
        BadgeView lockBadgeView = new BadgeView(mContext, ivLock);
        lockBadgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        lockBadgeView.setBadgeBackgroundColor(mContext.getResources().getColor(R.color.heavy_green));
        BadgeView offlineBadgeView = new BadgeView(mContext, ivOffline);
        offlineBadgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        offlineBadgeView.setBadgeBackgroundColor(mContext.getResources().getColor(R.color.light_green));
        BadgeView shortageBadgeView = new BadgeView(mContext, ivShortage);
        shortageBadgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        shortageBadgeView.setBadgeBackgroundColor(mContext.getResources().getColor(R.color.light_orange));
        BadgeView unoperationBadageView = new BadgeView(mContext, ivUnoperation);
        unoperationBadageView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        unoperationBadageView.setBadgeBackgroundColor(mContext.getResources().getColor(R.color.badage_blue));
        Machies machies = (Machies) item;
        int sick = machies.getSick();
        int x = DensityUtil.dp2px(mContext, -7);
        int y = DensityUtil.dp2px(mContext, 8);
        if (sick > 0) {
            errorBadgeView.setText(sick + "");
            errorBadgeView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13); //22SP
            errorBadgeView.setTranslationX(x);
            errorBadgeView.setTranslationY(y);
            errorBadgeView.show();
        } else {
            errorBadgeView.hide();
        }
        int locked = machies.getLocked();
        if (locked > 0) {
            lockBadgeView.setText(locked + "");
            lockBadgeView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            lockBadgeView.setTranslationX(x);
            lockBadgeView.setTranslationY(y);
            lockBadgeView.show();
        } else {
            lockBadgeView.hide();
        }
        int offline = machies.getOffline();
        if (offline > 0) {
            offlineBadgeView.setText(offline + "");
            offlineBadgeView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            offlineBadgeView.setTranslationX(x);
            offlineBadgeView.setTranslationY(y);
            offlineBadgeView.show();
        } else {
            offlineBadgeView.hide();
        }
        int lack = machies.getLack();
        if (lack > 0) {
            shortageBadgeView.setText(lack + "");
            shortageBadgeView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            shortageBadgeView.setTranslationX(x);
            shortageBadgeView.setTranslationY(y);
            shortageBadgeView.show();
        } else {
            shortageBadgeView.hide();
        }
        int offOperation = machies.getOffOperation();
        if (offOperation > 0) {
            unoperationBadageView.setText(offOperation + "");
            unoperationBadageView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            unoperationBadageView.setTranslationX(x);
            unoperationBadageView.setTranslationY(y);
            unoperationBadageView.show();
        } else {
            unoperationBadageView.hide();
        }
    }

    @OnClick({R.id.item_machines_error, R.id.item_machines_shortage, R.id.item_machines_offline, R.id.item_machines_lock, R.id.item_machines_unoperation})
    public void onClick(View view) {
        if(machineStateChangeLister==null){
            return;
        }
        switch (view.getId()) {
            case R.id.item_machines_error:
                machineStateChangeLister.state(Constants.state_error);
                break;
            case R.id.item_machines_shortage:
                machineStateChangeLister.state(Constants.state_lack);
                break;
            case R.id.item_machines_offline:
                machineStateChangeLister.state(Constants.state_offline);
                break;
            case R.id.item_machines_lock:
                machineStateChangeLister.state(Constants.state_lock);
                break;
            case R.id.item_machines_unoperation:
                machineStateChangeLister.state(Constants.state_unoperation);
                break;
        }
    }
}
