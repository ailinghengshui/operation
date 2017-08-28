package com.hzjytech.operation.module.scan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzjytech.operation.R;
import com.hzjytech.operation.base.BaseActivity;
import com.hzjytech.operation.entity.ScanInfo;

import com.hzjytech.operation.http.JijiaHttpSubscriber;
import com.hzjytech.operation.http.SubscriberOnErrorListener;
import com.hzjytech.operation.http.SubscriberOnNextListener;
import com.hzjytech.operation.http.api.MachinesApi;
import com.hzjytech.operation.module.data.ErrorListActivity;
import com.hzjytech.operation.module.home.DetailMachineActivity;
import com.hzjytech.operation.scan.activity.CaptureActivity;
import com.hzjytech.operation.utils.UserUtils;
import com.hzjytech.operation.widgets.TitleBar;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by hehongcan on 2017/6/5.
 */
public class MenuControlActivity extends BaseActivity {
    private static final int REQUEST_SCAN = 111;
    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_menu_control_login)
    ImageView mIvMenuControlLogin;
    @BindView(R.id.tv_menu_control_login)
    TextView mTvMenuControlLogin;
    @BindView(R.id.ll_menu_control_login)
    LinearLayout mLlMenuControlLogin;
    @BindView(R.id.ll_menu_control_error_histroy)
    LinearLayout mLlMenuControlErrorHistroy;
    @BindView(R.id.ll_menu_control_machine_info)
    LinearLayout mLlMenuControlMachineInfo;
    @BindView(R.id.ll_menu_control_polling)
    LinearLayout mLlMenuControlPolling;
    private String mResultString;
    private Integer mMachineId;
    private boolean isLogin;
    private ScanInfo info;
    private String  token;

    @Override
    protected int getResId() {
        return R.layout.activity_menu_control;
    }

    @Override
    protected void initView() {
        initTitle();
        token = UserUtils.getUserInfo()
                .getToken();
        if (mResultString == null) {
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_SCAN);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        mTitleBar.setLeftImageResource(R.drawable.icon_left);
        mTitleBar.setTitleBold(true);
        mTitleBar.setTitleColor(Color.WHITE);
        mTitleBar.setTitle(R.string.menu_control);
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SCAN && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            mResultString = bundle.getString(CaptureActivity.SCAN_RESULT_KEY);
            getMachineIdForEncodeString(mResultString);
        }else{
            finish();
        }
    }

    /**
     * 网络获取解码后的machineId
     * @param resultString
     * @return
     */
    private void getMachineIdForEncodeString(String resultString) {
        Observable<String> observable = MachinesApi.getMachineIdByQRCode(token,
                resultString);
        JijiaHttpSubscriber subscriber = JijiaHttpSubscriber.buildSubscriber(this)
                .setOnNextListener(new SubscriberOnNextListener<String>() {
                    @Override
                    public void onNext(String  id) {
                        mMachineId=Integer.valueOf(id);
                    }
                }).setOnErrorListener(new SubscriberOnErrorListener() {
                    @Override
                    public void onError(Throwable e) {
                        Intent intent = new Intent(MenuControlActivity.this, CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_SCAN);
                    }
                })
                .setProgressDialog(mProgressDlg)
                .build();
        observable.subscribe(subscriber);

    }

    /**
     * 截取咖啡机编号
     * @return
     */
 /*   private Integer getMachineIdForEncodeString(String resultString) {
        String decodeString = null;
        try {
            decodeString = new String(Base64.decode(resultString, Base64.NO_WRAP), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int length = decodeString.length();
        String s = decodeString.substring(13, length - 6);
        return Integer.valueOf(s);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_menu_control_login, R.id.ll_menu_control_error_histroy, R.id
            .ll_menu_control_machine_info, R.id.ll_menu_control_polling})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_menu_control_login:
                if(!isLogin){
                    loginIn();
                }
                break;
            case R.id.ll_menu_control_error_histroy:
                Intent intent = new Intent(this, ErrorListActivity.class);
                ArrayList<Integer> ids = new ArrayList<>();
                ids.add(mMachineId);
                long startTime=0;
                long endTime=System.currentTimeMillis();
                intent.putExtra("startTime",startTime);
                intent.putExtra("endTime",endTime);
                intent.putIntegerArrayListExtra("machinesId",ids);
                startActivity(intent);
                break;
            case R.id.ll_menu_control_machine_info:
                Intent machineIntent = new Intent(this, DetailMachineActivity.class);
                machineIntent.putExtra("machineId",mMachineId);
                startActivity(machineIntent);
                break;
            case R.id.ll_menu_control_polling:
                Intent pollingIntent = new Intent(this, PollingActivity.class);
                pollingIntent.putExtra("machineId",mMachineId);
                startActivity(pollingIntent);
                break;
        }
    }

    /**
     * 登出
     * 提示是相反的，登出状态下，显示的图片和文字是登录
     */
    private void loginOut() {



        Observable<ScanInfo> observable = MachinesApi.loginMachine(token,
                mMachineId,
                false,
                mResultString,info.getAdmin().getRecordId());
        JijiaHttpSubscriber subscriber = JijiaHttpSubscriber.buildSubscriber(this)
                .setOnNextListener(new SubscriberOnNextListener<ScanInfo>() {
                    @Override
                    public void onNext(ScanInfo info) {
                        isLogin=!isLogin;
                        mIvMenuControlLogin.setImageResource(R.drawable.icon_login_in);
                        mTvMenuControlLogin.setText(R.string.login_in);
                    }
                })
                .setProgressDialog(mProgressDlg)
                .build();
        observable.subscribe(subscriber);
    }


    /**
     * 登录
     */
    private void loginIn() {
        Observable<ScanInfo> observable = MachinesApi.loginMachine(token,
                mMachineId,
                true,
                mResultString,null);
        JijiaHttpSubscriber subscriber = JijiaHttpSubscriber.buildSubscriber(this)
                .setOnNextListener(new SubscriberOnNextListener<ScanInfo>() {
                    @Override
                    public void onNext(ScanInfo info) {
                        MenuControlActivity.this.info=info;
                        isLogin=true;
                        mIvMenuControlLogin.setImageResource(R.drawable.icon_login_out);
                        mTvMenuControlLogin.setText(R.string.login);
                    }
                })
                .setProgressDialog(mProgressDlg)
                .build();
        observable.subscribe(subscriber);
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
