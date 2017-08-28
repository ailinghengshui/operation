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
import com.hzjytech.operation.adapters.home.DetailMachineAdapter;
import com.hzjytech.operation.base.BaseActivity;
import com.hzjytech.operation.entity.ErrorHistory;
import com.hzjytech.operation.entity.MachineInfo;
import com.hzjytech.operation.http.JijiaHttpResultZip;
import com.hzjytech.operation.http.JijiaHttpSubscriber;
import com.hzjytech.operation.http.JijiaRZField;
import com.hzjytech.operation.http.SubscriberOnNextListener;
import com.hzjytech.operation.http.api.MachinesApi;
import com.hzjytech.operation.utils.CommonUtil;
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
 * Created by hehongcan on 2017/4/26.
 */
public class DetailMachineActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.rv_single_machine_detail)
    RecyclerView rvSingleMachineDetail;
    @BindView(R.id.pcfl_detail)
    PtrClassicFrameLayout pcflDetail;
    private MachineInfo machieInfo;
    private DetailMachineAdapter singleMachineAdapter;
    private PtrHandler ptrDefaultHandler=new PtrHandler() {
        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            return false;
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            pageNumber++;
            loadMoreData();

        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void loadMore() {
            pageNumber++;
            loadMoreData();

        }
    };



    private RecyclerAdapterWithHF mAdapter;
    private JijiaHttpSubscriber subscriber;
    private ErrorHistory errorList;
    private int pageNumber=0;
    private List<ErrorHistory.VendingMachinesBean.VmErrorsBean> vmErrors=new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.activity_single_detail_machine;
    }

    @Override
    protected void initView() {
        initTitle();
        initReyclerView();
        initPtr();
        initData();

    }
/**
 *     初始化上拉加载更多
 */

    private void initPtr() {
        pcflDetail.setPtrHandler(ptrDefaultHandler);//设置下拉监听
        pcflDetail.setOnLoadMoreListener(onLoadMoreListener);//设置上拉监听
        pcflDetail.setLoadMoreEnable(true);//设置可以加载更多
    }

    /**
     * 初始化recyclerview
     */
    private void initReyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSingleMachineDetail.setLayoutManager(linearLayoutManager);
        singleMachineAdapter = new DetailMachineAdapter(this, null);
        mAdapter = new RecyclerAdapterWithHF(singleMachineAdapter);
        rvSingleMachineDetail.setAdapter(mAdapter);

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

    private void initData() {
        String token = UserUtils.getUserInfo().getToken();
        Intent intent = getIntent();
        int machineId = intent.getIntExtra("machineId", -1);
        if(subscriber==null||subscriber.isUnsubscribed()){
       /* Observable<MachineInfo> observable = MachinesApi.getSingleMachieDetail(token, machineId);
        Observable<ErrorHistory> mObservable = MachinesApi.getErrorHistory(0, System.currentTimeMillis(),machineId,0,token,pageNumber,5);
        Observable<ResultZip> zipObservable = Observable.zip(observable, mObservable, new Func2<MachineInfo,ErrorHistory, ResultZip>() {
            @Override
            public ResultZip call(MachineInfo machineInfo, ErrorHistory errorList) {
                return new ResultZip(machineInfo,errorList );
            }
        });
        subscriber = JijiaHttpSubscriber.buildSubscriber(this).setOnNextListener(new SubscriberOnNextListener<ResultZip>() {
            @Override
            public void onNext(ResultZip resultZip) {
                if (resultZip != null) {
                    machieInfo = resultZip.machineInfo;
                    errorList = resultZip.errorList;
                    titleBar.setTitle(machieInfo.getBasicInfo().getName());
                    vmErrors = errorList.getVendingMachines().get(0).getVmErrors();
                    singleMachineAdapter.setData(machieInfo,vmErrors);

                }
            }
        }).setProgressDialog(mProgressDlg).build();
        zipObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);*/
            Observable<MachineInfo> observable = MachinesApi.getSingleMachieDetail(token, machineId);
            JijiaHttpSubscriber subscriber = JijiaHttpSubscriber.buildSubscriber(this).setOnNextListener(new SubscriberOnNextListener<MachineInfo>() {
                @Override
                public void onNext(MachineInfo machineInfo) {
                    titleBar.setTitle(machineInfo.getBasicInfo().getName());
                    DetailMachineActivity.this.machieInfo=machineInfo;
                    singleMachineAdapter.setData(machineInfo, null);
                }
            }).setProgressDialog(mProgressDlg).build();
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);

        }}

    /**
     * 上拉加载更多
     */
    private void loadMoreData() {
        Intent intent = getIntent();
        int machineId = intent.getIntExtra("machineId", -1);
        String token = UserUtils.getUserInfo().getToken();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(machineId);
        Observable<ErrorHistory> mObservable = MachinesApi.getErrorHistory(0, System.currentTimeMillis(),list,0,token,pageNumber,5);
        JijiaHttpSubscriber subscriber = JijiaHttpSubscriber.buildSubscriber(this).setOnNextListener(new SubscriberOnNextListener<ErrorHistory>() {
            @Override
            public void onNext(ErrorHistory errorHistory) {
                if(errorHistory==null||errorHistory.getVendingMachines()==null||errorHistory.getVendingMachines().size()==0||errorHistory.getVendingMachines().get(0).getVmErrors()==null||errorHistory.getVendingMachines().get(0).getVmErrors().size()==0){
                    pcflDetail.loadMoreComplete(false);
                    return;
                }
                List<ErrorHistory.VendingMachinesBean.VmErrorsBean> moreErrors = errorHistory.getVendingMachines().get(0).getVmErrors();
                vmErrors.addAll(moreErrors);
                singleMachineAdapter.setData(machieInfo, vmErrors);
            }
        }).setPtcf(pcflDetail).build();
        mObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonUtil.unSubscribeSubs(subscriber);
    }

    private class ResultZip extends JijiaHttpResultZip {
        @JijiaRZField
        MachineInfo machineInfo;
        @JijiaRZField
       ErrorHistory errorList;

        public ResultZip(MachineInfo machineInfo,ErrorHistory errorList) {
            this.machineInfo=machineInfo;
            this.errorList=errorList;
        }
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
