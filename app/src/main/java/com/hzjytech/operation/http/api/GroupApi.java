package com.hzjytech.operation.http.api;

import com.hzjytech.operation.entity.GroupList;
import com.hzjytech.operation.http.HttpResultFunc;
import com.hzjytech.operation.http.RetrofitSingleton;


import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hehongcan on 2017/5/11.
 * 咖啡机组相关api
 */
public class GroupApi {
    //获取单台管理列表
    public static Observable<List<GroupList>> getSingleGroupList(String token, String queryCondition){
        return RetrofitSingleton.getApiService().getSingleGroupList(token,queryCondition)
                .map(new HttpResultFunc<List<GroupList>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
