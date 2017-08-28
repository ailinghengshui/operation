package com.hzjytech.operation.adapters.group.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzjytech.operation.R;
import com.hzjytech.operation.entity.GroupInfo;
import com.hzjytech.operation.inter.MoreMachineClickListener;
import com.hzjytech.operation.module.home.DetailMachineActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hehongcan on 2017/5/2.
 */
public class MachiesContainerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ll_more_machines)
    LinearLayout llMoreMachines;
    @BindView(R.id.ll_machines_container)
    LinearLayout llMachinesContainer;

    public MachiesContainerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(final Context context, final List<GroupInfo.SubMachinesBean> subMachines, final MoreMachineClickListener moreMachineClickListener) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View head = llMachinesContainer.getChildAt(0);
        View lineView = llMachinesContainer.getChildAt(1);
        View foot = llMachinesContainer.getChildAt(llMachinesContainer.getChildCount() - 1);
        llMachinesContainer.removeAllViews();
        llMachinesContainer.addView(head);
        llMachinesContainer.addView(lineView);
        llMachinesContainer.addView(foot);
        if (subMachines == null || subMachines.size() == 0) {
            llMachinesContainer.removeAllViews();
           llMachinesContainer.setVisibility(View.GONE);
        }else{
            if(subMachines.size()==1){
                llMachinesContainer.removeViewAt(2);
                View view = inflater.inflate(R.layout.item_machines, null, false);
                llMachinesContainer.addView(view,2);
                View view1 = llMachinesContainer.getChildAt(2);
                TextView tv_machine_order = (TextView) view1.findViewById(R.id.tv_machine_order);
                TextView tv_machine_name= (TextView) view1.findViewById(R.id.tv_machine_name);
                TextView tv_machine_location =(TextView) view1.findViewById(R.id.tv_machine_location);
                TextView tv_machine_id = (TextView) view1.findViewById(R.id.tv_machine_item_id);
                tv_machine_order.setText(1+"");
                tv_machine_id.setText(subMachines.get(0).getId()+"");
                tv_machine_name.setText(subMachines.get(0).getName());
                tv_machine_location.setText(subMachines.get(0).getAddress());
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailMachineActivity.class);
                        intent.putExtra("machineId",subMachines.get(0).getId());
                        context.startActivity(intent);
                    }
                });
            }else{
                if(subMachines.size()==2){
                    llMachinesContainer.removeViewAt(2);
                }
                for (int i=0;i<2;i++){
                    View view = inflater.inflate(R.layout.item_machines, null, false);
                    llMachinesContainer.addView(view,2);
                }
                for(int i=0;i<2;i++ ){
                    View view1 = llMachinesContainer.getChildAt(2 + i);
                    TextView tv_machine_order = (TextView) view1.findViewById(R.id.tv_machine_order);
                    TextView tv_machine_name= (TextView) view1.findViewById(R.id.tv_machine_name);
                    TextView tv_machine_location =(TextView) view1.findViewById(R.id.tv_machine_location);
                    TextView tv_machine_id = (TextView) view1.findViewById(R.id.tv_machine_item_id);
                    tv_machine_order.setText(1+i+"");
                    tv_machine_id.setText(subMachines.get(i).getId()+"");
                    tv_machine_name.setText(subMachines.get(i).getName());
                    tv_machine_location.setText(subMachines.get(i).getAddress());

                }
                llMachinesContainer.getChildAt(2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,DetailMachineActivity.class);
                        intent.putExtra("machineId",subMachines.get(0).getId());
                        context.startActivity(intent);
                    }
                });
                llMachinesContainer.getChildAt(3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,DetailMachineActivity.class);
                        intent.putExtra("machineId",subMachines.get(1).getId());
                        context.startActivity(intent);
                    }
                });

            }
            //设置查看更多的点击事件
            foot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moreMachineClickListener.click();
                }
            });
        }
    }
}
