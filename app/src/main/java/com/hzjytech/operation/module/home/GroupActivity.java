package com.hzjytech.operation.module.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.PtrHandler;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.hzjytech.operation.R;
import com.hzjytech.operation.adapters.group.GroupAdapter;
import com.hzjytech.operation.base.BaseActivity;
import com.hzjytech.operation.entity.GroupInfo;
import com.hzjytech.operation.entity.User;

import com.hzjytech.operation.http.JijiaHttpSubscriber;
import com.hzjytech.operation.http.SubscriberOnNextListener;
import com.hzjytech.operation.http.api.MachinesApi;
import com.hzjytech.operation.inter.MoreGroupClickListener;
import com.hzjytech.operation.inter.MoreMachineClickListener;
import com.hzjytech.operation.utils.UserUtils;
import com.hzjytech.operation.widgets.TitleBar;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hehongcan on 2017/5/2.
 */
public class GroupActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.rv_group)
    RecyclerView rvGroup;
    @BindView(R.id.pcfl_group)
    PtrClassicFrameLayout pcflGroup;
    private GroupAdapter groupAdapter;
    private RecyclerAdapterWithHF mAdapter;
    private GroupInfo groupInfo;
    private PtrHandler ptrDefaultHandler=new PtrHandler() {
        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            return false;
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {

        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void loadMore() {
            initData();
        }
    };
    private int groupId=-1;

    @Override
    protected int getResId() {
        return R.layout.activity_group;
    }

    @Override
    protected void initView() {
        initTitle();
        initRecyclerView();
        initPtrc();
        initData();
        initListeners();
    }

    /**
     * 初始化各种监听
     */
    private void initListeners() {
        groupAdapter.setMoreGroupClickListener(new MoreGroupClickListener() {
            @Override
            public void click() {
                Intent intent = new Intent(GroupActivity.this, MoreGroupActivity.class);
                intent.putExtra("groupId",groupId);
                startActivity(intent);
            }
        });
        groupAdapter.setMoreMachineClickListener(new MoreMachineClickListener() {
            @Override
            public void click() {
                Intent intent = new Intent(GroupActivity.this, MoreMachineActivity.class);
                intent.putExtra("groupId",groupId);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化下拉刷新、上拉加载更多
     */
    private void initPtrc() {
        pcflGroup.setPtrHandler(ptrDefaultHandler);//设置下拉监听
        pcflGroup.setOnLoadMoreListener(onLoadMoreListener);//设置上拉监听
        pcflGroup.setLoadMoreEnable(false);//设置可以加载更多
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvGroup.setLayoutManager(linearLayoutManager);
        groupAdapter = new GroupAdapter(this, null);
        mAdapter = new RecyclerAdapterWithHF(groupAdapter);
        rvGroup.setAdapter(mAdapter);

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
              titleBar.setTitle(groupInfo.getBasicInfo().getName());
                GroupActivity.this.groupInfo=groupInfo;
                groupAdapter.setGroupData(groupInfo);
            }
        }).setProgressDialog(pcflGroup.isRefreshing()||pcflGroup.isLoadingMore() ? null : mProgressDlg).setPtcf(pcflGroup).build();
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
