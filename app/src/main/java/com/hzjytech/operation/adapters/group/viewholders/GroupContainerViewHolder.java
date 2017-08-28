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
import com.hzjytech.operation.inter.MoreGroupClickListener;
import com.hzjytech.operation.module.home.GroupActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hehongcan on 2017/5/2.
 */
public class GroupContainerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ll_more_groups)
    LinearLayout llMoreGroups;
    @BindView(R.id.ll_groups_container)
    LinearLayout llGroupsContainer;

    public GroupContainerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(final Context context, final List<GroupInfo.SubGroupBean> subGroups, final MoreGroupClickListener moreGroupClickListener) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View head = llGroupsContainer.getChildAt(0);
        View lineView = llGroupsContainer.getChildAt(1);
        View foot = llGroupsContainer.getChildAt(llGroupsContainer.getChildCount() - 1);
        llGroupsContainer.removeAllViews();
        llGroupsContainer.addView(head);
        llGroupsContainer.addView(lineView);
        llGroupsContainer.addView(foot);
        if (subGroups == null || subGroups.size() == 0) {
            llGroupsContainer.removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0);
            llGroupsContainer.setLayoutParams(layoutParams);
            llGroupsContainer.setVisibility(View.GONE);
        }else{
            if(subGroups.size()==1){
                View view = inflater.inflate(R.layout.item_group, null, false);
                llGroupsContainer.addView(view,2);
                View view1 = llGroupsContainer.getChildAt(2);
                TextView tv_group_id = (TextView) view1.findViewById(R.id.tv_group_id);
                TextView tv_group_name = (TextView) view1.findViewById(R.id.tv_group_name);
                TextView tv_group_note= (TextView) view1.findViewById(R.id.tv_group_note);
                tv_group_id.setText(1+"");
                tv_group_name.setText(subGroups.get(0).getName());
                tv_group_note.setText("无备注");
                tv_group_note.setVisibility(View.GONE);
                llGroupsContainer.removeViewAt(llGroupsContainer.getChildCount()-1);
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GroupActivity.class);
                        intent.putExtra("groupId",subGroups.get(0).getId());
                        context.startActivity(intent);
                    }
                });
            }else{
                for(int i=0;i<2;i++){
                    View view = inflater.inflate(R.layout.item_group, null, false);
                    llGroupsContainer.addView(view,2);
                }
                for(int i=0;i<2;i++ ){
                    View view1 = llGroupsContainer.getChildAt(2 + i);
                    TextView tv_group_id = (TextView) view1.findViewById(R.id.tv_group_id);
                    TextView tv_group_name = (TextView) view1.findViewById(R.id.tv_group_name);
                    TextView tv_group_note= (TextView) view1.findViewById(R.id.tv_group_note);
                    tv_group_id.setText(1+i+"");
                    tv_group_name.setText(subGroups.get(i).getName());
                    tv_group_note.setText("无备注");
                    tv_group_note.setVisibility(View.GONE);

                }
                llGroupsContainer.getChildAt(2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GroupActivity.class);
                        intent.putExtra("groupId",subGroups.get(0).getId());
                        context.startActivity(intent);
                    }
                });
                llGroupsContainer.getChildAt(3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GroupActivity.class);
                        intent.putExtra("groupId",subGroups.get(0).getId());
                        context.startActivity(intent);
                    }
                });

            }
            foot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(moreGroupClickListener!=null){
                       moreGroupClickListener.click();
                   }
                }
            });
        }
    }
}
