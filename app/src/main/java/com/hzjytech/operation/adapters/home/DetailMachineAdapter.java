package com.hzjytech.operation.adapters.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzjytech.operation.R;
import com.hzjytech.operation.base.BaseActivity;
import com.hzjytech.operation.entity.ErrorHistory;
import com.hzjytech.operation.entity.MachineInfo;
import com.hzjytech.operation.inter.OnSelectClickListener;
import com.hzjytech.operation.module.home.DetailMachineActivity;
import com.hzjytech.operation.utils.GPSUtil;
import com.hzjytech.operation.utils.MyMath;
import com.hzjytech.operation.utils.TimeUtil;
import com.hzjytech.operation.widgets.dialogs.SelectDialog;
import com.hzjytech.operation.widgets.dialogs.TipDingDingDialog;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hehongcan on 2017/4/26.
 */
public class DetailMachineAdapter extends RecyclerView.Adapter {
    private static final int TYPE_DETAIL_MESSAGE = 0;
    private static final int TYPE_ROLE = 1;
    private static final int TYPE_ERROR_HISTORY = 2;
    private MachineInfo machineInfo;
    private Context mContext;
    private final LayoutInflater inflater;
    private List<ErrorHistory.VendingMachinesBean.VmErrorsBean> errorList;

    public DetailMachineAdapter(Context context, MachineInfo machineInfo) {
        this.mContext = context;
        this.machineInfo = machineInfo;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(
            MachineInfo machineInfo,
            List<ErrorHistory.VendingMachinesBean.VmErrorsBean> errorList) {
        this.machineInfo = machineInfo;
        this.errorList = errorList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_DETAIL_MESSAGE) {
            View view = inflater.inflate(R.layout.item_detail_message, parent, false);
            return new DetailMessageViewHolder(view);
        } else if (viewType == TYPE_ROLE) {
            View view = inflater.inflate(R.layout.item_role, parent, false);
            return new RoleViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_error_history, parent, false);
            return new ErrorHistoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DetailMessageViewHolder) {
            ((DetailMessageViewHolder) holder).setMessageData(mContext,machineInfo);
        } else if (holder instanceof RoleViewHolder) {
            ((RoleViewHolder) holder).setRoleData(machineInfo.getStaffInfo());
        } else if (holder instanceof ErrorHistoryViewHolder) {
            ((ErrorHistoryViewHolder) holder).setErrorData(mContext, errorList, position);
        }
    }

    @Override
    public int getItemCount() {
        return machineInfo == null ? 0 : 2 + (errorList == null ? 0 : errorList.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_DETAIL_MESSAGE;
        } else if (position == 1) {
            return TYPE_ROLE;
        } else {
            return TYPE_ERROR_HISTORY;
        }
    }

    public class RoleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_role_container)
        LinearLayout llRoleContainer;

        public RoleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setRoleData(List<MachineInfo.StaffInfoBean> roleList) {
            View head = llRoleContainer.getChildAt(0);
            llRoleContainer.removeAllViews();
            if (roleList.size() == 0) {
                llRoleContainer.setVisibility(View.GONE);
                return;
            }
            if (roleList.size() > 0) {
                llRoleContainer.addView(head);
                for (int i = 0; i < roleList.size() - 1; i++) {
                    View view = inflater.inflate(R.layout.item_other_role, null, false);
                    llRoleContainer.addView(view);
                }
                for (int i = 1; i < roleList.size(); i++) {
                    View view = llRoleContainer.getChildAt(i);
                    TextView tv_order = (TextView) view.findViewById(R.id.tv_order);
                    TextView tv_role = (TextView) view.findViewById(R.id.tv_role);
                    TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                    tv_order.setText(i + "");
                    tv_role.setText(roleList.get(i - 1)
                            .getRoleName());
                    tv_name.setText(roleList.get(i - 1)
                            .getUserName());
                }
            }
            View lastView = inflater.inflate(R.layout.item_last_role, null, false);
            llRoleContainer.addView(lastView);
            View view = llRoleContainer.getChildAt(llRoleContainer.getChildCount() - 1);
            TextView tvLastName = (TextView) view.findViewById(R.id.tv_last_name);
            TextView tvLastRole = (TextView) view.findViewById(R.id.tv_last_role);
            TextView tvLastOrder = (TextView) view.findViewById(R.id.tv_last_order);
            MachineInfo.StaffInfoBean lastRole = roleList.get(roleList.size() - 1);
            tvLastOrder.setText(roleList.size() + "");
            tvLastRole.setText(lastRole.getRoleName());
            tvLastName.setText(lastRole.getUserName());


        }
    }

    public class DetailMessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.business)
        TextView tvBusiness;
        @BindView(R.id.version)
        TextView tvVersion;
        @BindView(R.id.tv_group)
        TextView tvGroup;
        @BindView(R.id.tv_menu)
        TextView tvMenu;
        @BindView(R.id.tv_operation)
        TextView tvOperation;
        @BindView(R.id.tv_lock)
        TextView tvLock;
        @BindView(R.id.tv_box_number)
        TextView tvBoxNumber;
        @BindView(R.id.tv_cup_radius)
        TextView tvCupRadius;
        @BindView(R.id.tv_bean_count)
        TextView tvBeanCount;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_long)
        TextView tvLong;
        @BindView(R.id.tv_latitude)
        TextView tvLatitude;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_note)
        TextView tvNote;
        @BindView(R.id.tv_can_coffeeme)
        TextView mTvCanCoffeeme;
        @BindView(R.id.tv_operation_time)
        TextView mTvOperationTime;
        @BindView(R.id.iv_location)
        ImageView mIvLocation;

        public DetailMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setMessageData(final Context context, MachineInfo machineInfo) {
            DecimalFormat df = new DecimalFormat("0.000000");
            final MachineInfo.BasicInfoBean basicInfo = machineInfo.getBasicInfo();
            tvBrand.setText("咖啡机品牌： " + basicInfo.getBrand());
            tvType.setText("型号: " + (basicInfo.getTypeName() == null ? "" : basicInfo.getTypeName
                    ()));
            tvBusiness.setText("加盟商: " + (basicInfo.getFranchiseeName() == null ? "" : basicInfo
                    .getFranchiseeName()));
            tvVersion.setText("当前版本: " + (basicInfo.getVersion() == null ? "" : basicInfo
                    .getVersion()));
            tvGroup.setText("促销分组： " + basicInfo.getGroupName());
            tvMenu.setText("菜单: " + (basicInfo.getMenuName() == null ? "" : basicInfo.getMenuName
                    ()));
            tvOperation.setText("运营状态: " + (basicInfo.isOperationStatus() ? "是" : "否"));
            tvLock.setText("锁定状态: " + (basicInfo.isIsLocked() ? "是" : "否"));
            tvBoxNumber.setText("粉料盒数量: " + basicInfo.getMagazineNum());
            tvCupRadius.setText("纸杯口径: " +MyMath.getIntOrDouble(basicInfo.getCapCaliber()));
            tvBeanCount.setText("磨豆: " + MyMath.getIntOrDouble(basicInfo.getBeansWeight()));
            tvStartTime.setText("创建时间: " + TimeUtil.getShortTimeFromLong(basicInfo.getBeginTime()));
            tvLong.setText("经度: " + df.format(Double.valueOf(basicInfo.getLongitude())));
            tvLatitude.setText("纬度: " + df.format(Double.valueOf(basicInfo.getLatitude())));
            tvLocation.setText(basicInfo.getAddress());
            tvNote.setText(basicInfo.getNote());
            mTvCanCoffeeme.setText(basicInfo.isSupportCoffeeMe()?"是":"否");
            if(basicInfo.getOpeningTime()==null||basicInfo.getClosingTime()==null){
                mTvOperationTime.setText("");
            }else{
                mTvOperationTime.setText(basicInfo.getOpeningTime()+"-"+basicInfo.getClosingTime());
            }
            mIvLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final SelectDialog selectDialog = SelectDialog.newInstance();
                    selectDialog.setOnSelectClickListener(new OnSelectClickListener() {
                        @Override
                        public void select(int positon) {
                            if(positon==2){
                                selectDialog.dismiss();
                            }else{
                                selectMap(context,positon,basicInfo);
                            }
                        }
                    });
                    selectDialog.show(((BaseActivity)mContext).getSupportFragmentManager(),"select");
                }
            });

        }

        /**
         * 0、高德地图
         * 1、百度地图
         * @param context
         * @param positon
         * @param basicInfo
         */
        private void selectMap(Context context, int positon, MachineInfo.BasicInfoBean basicInfo) {
            if(positon==0){
                double[] gcjs = GPSUtil.bd09_To_Gcj02(Double.valueOf(basicInfo.getLatitude()),
                        Double.valueOf(basicInfo.getLongitude()));
                try {
                    Intent i1 = new Intent();
                    // 步行路线规划

                    i1.setAction(Intent.ACTION_VIEW);
                    i1.addCategory(Intent.CATEGORY_DEFAULT);
                    i1.setData(Uri.parse("androidamap://viewMap?sourceApplication=appname&poiname="+basicInfo.getName()+"&lat="+gcjs[0]+"&lon="+gcjs[1]+"&dev=0"));
                    context.startActivity(i1);
                } catch (Exception e) {
          /*  String url = "http://api.map.baidu.com/direction?origin=name:我的位置&destination="+"latlng:"+latitude+","+
                    longitude+"&mode=driving&region=杭州&output=html"+"&src=operation";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent .setData(Uri.parse(url));
            context.startActivity(intent);*/
                    TipDingDingDialog tipDingDingDialog = new TipDingDingDialog();
                    tipDingDingDialog.setText(context.getResources().getString(R.string.please_install_gaodemap));
                    tipDingDingDialog.show(((BaseActivity)context).getSupportFragmentManager(),"dingding");

                }
                //调起百度地图导航
            }else if(positon==1){
                try {
                    Intent i1 = new Intent();
                    // 步行路线规划


                    i1.setData(Uri.parse("baidumap://map/marker?location="+basicInfo.getLatitude()+","+basicInfo.getLongitude()+"&title="+basicInfo.getName()+"&traffic=off"));

                    context.startActivity(i1);
                } catch (Exception e) {
          /*  String url = "http://api.map.baidu.com/direction?origin=name:我的位置&destination="+"latlng:"+latitude+","+
                    longitude+"&mode=driving&region=杭州&output=html"+"&src=operation";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent .setData(Uri.parse(url));
            context.startActivity(intent);*/
                    TipDingDingDialog tipDingDingDialog = new TipDingDingDialog();
                    tipDingDingDialog.setText(context.getResources().getString(R.string.please_install_baidumap));
                    tipDingDingDialog.show(((BaseActivity)context).getSupportFragmentManager(),"dingding");

                }
                //调起百度地图导航
            }
        }
    }

    public class ErrorHistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_error_head)
        LinearLayout llErrorHead;
        @BindView(R.id.tv_error_id)
        TextView tvErrorId;
        @BindView(R.id.tv_error_occure_time)
        TextView tvErrorOccureTime;
        @BindView(R.id.tv_error_content)
        TextView tvErrorContent;
        @BindView(R.id.tv_error_reset_time)
        TextView tvErrorResetTime;
        @BindView(R.id.tv_error_solve)
        TextView tvErrorSolve;
        @BindView(R.id.tv_check_task)
        TextView tvCheckTask;
        @BindView(R.id.ll_error_container)
        LinearLayout ll_error_container;
        @BindView(R.id.tv_error_machine_code)
        TextView tv_error_machine_code;
        @BindView(R.id.tv_error_machine_location)
        TextView tv_error_machine_location;


        public ErrorHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setErrorData(
                final Context context,
                List<ErrorHistory.VendingMachinesBean.VmErrorsBean> errorList,
                int position) {
            int realPostion = position - 2;
            ErrorHistory.VendingMachinesBean.VmErrorsBean errorsBean = errorList.get(realPostion);
            if (realPostion == 0) {
                llErrorHead.setVisibility(View.VISIBLE);
            } else {
                llErrorHead.setVisibility(View.GONE);
            }
            tvErrorId.setText(errorsBean.getErrorCode() + "错误");
            tvErrorOccureTime.setText(TimeUtil.getCorrectTimeString(errorsBean.getOccurTime()));
            tvErrorContent.setText(errorsBean.getErrorDescription());
            tvErrorResetTime.setText(TimeUtil.getLong2TimeFromLong(errorsBean
                    .getRecoverTime()));
            tv_error_machine_code.setText(errorsBean.getName());
            tv_error_machine_location.setText(errorsBean.getLocation());
            tvErrorSolve.setText(errorsBean.getNote()==null?"无":errorsBean.getNote());
            ll_error_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailMachineActivity activity = (DetailMachineActivity) context;
                    activity.showTip(R.string.check_task_isnot_usable);
                }
            });
        }
    }
}
