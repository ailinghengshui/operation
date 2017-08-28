package com.hzjytech.operation.module.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.PtrHandler;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.hzjytech.operation.R;
import com.hzjytech.operation.adapters.group.MoreGroupAdapter;
import com.hzjytech.operation.base.BaseActivity;
import com.hzjytech.operation.entity.GroupInfo;
import com.hzjytech.operation.entity.GroupList;
import com.hzjytech.operation.entity.User;
import com.hzjytech.operation.http.JijiaHttpSubscriber;
import com.hzjytech.operation.http.SubscriberOnNextListener;
import com.hzjytech.operation.http.api.MachinesApi;
import com.hzjytech.operation.utils.UserUtils;
import com.hzjytech.operation.widgets.TitleBar;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hehongcan on 2017/5/2.
 */
public class MoreGroupActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.rv_more_group)
    RecyclerView rvMoreGroup;
    @BindView(R.id.pcfl_more_group)
    PtrClassicFrameLayout pcflMoreGroup;
    private MoreGroupAdapter groupAdapter;
    private RecyclerAdapterWithHF mAdapter;
    private int groupId;
    private PtrHandler ptrDefaultHandler=new PtrDefaultHandler() {
        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            initData();
        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void loadMore() {
            initData();
        }
    };
    private List<GroupList> subMachines=new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.activity_more_group;
    }

    @Override
    protected void initView() {
        initTitle();
        initRecyclerView();
        initPtrc();
        initData();
    }

    /**
     * 初始化下拉刷新、上拉加载更多
     */
    private void initPtrc() {
        pcflMoreGroup.setPtrHandler(ptrDefaultHandler);//设置下拉监听
        pcflMoreGroup.setOnLoadMoreListener(onLoadMoreListener);//设置上拉监听
        pcflMoreGroup.setLoadMoreEnable(false);//设置可以加载更多
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMoreGroup.setLayoutManager(linearLayoutManager);
        groupAdapter = new MoreGroupAdapter(this, null);
        mAdapter = new RecyclerAdapterWithHF(groupAdapter);
        rvMoreGroup.setAdapter(mAdapter);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        User userInfo = UserUtils.getUserInfo();
        Intent intent = getIntent();
        groupId = intent.getIntExtra("groupId", -1);
        Observable<GroupInfo> observable = MachinesApi.getGroupInfo(userInfo.getToken(), groupId);
        JijiaHttpSubscriber subscriber = JijiaHttpSubscriber.buildSubscriber(this).setOnNextListener(new SubscriberOnNextListener<GroupInfo>() {
            @Override
            public void onNext(GroupInfo groupInfo) {
                if(groupInfo==null){
                    return;
                }

                for (GroupInfo.SubGroupBean subGroupBean : groupInfo.getSubGroups()) {
                    subMachines.add(new GroupList(subGroupBean.getId(),subGroupBean.getName(),false));
                }
                groupAdapter.setGroupData(subMachines);
            }
        }).setProgressDialog(pcflMoreGroup.isRefreshing() || pcflMoreGroup.isLoadingMore() ? null : mProgressDlg).setPtcf(pcflMoreGroup).build();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    /**
     * 初始化标题栏
     */
    private void initTitle() {
        titleBar.setLeftImageResource(R.drawable.icon_left);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.setTitleColor(Color.WHITE);
        titleBar.setTitleBold(true);
        titleBar.setTitle("包含分组");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
