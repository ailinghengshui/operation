package com.hzjytech.operation.adapters.group.viewholders;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hzjytech.operation.R;
import com.hzjytech.operation.adapters.PicAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hehongcan on 2017/7/18.
 */
public class GroupPicViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rv_group_pic)
    RecyclerView mRvGroupPic;
    @BindView(R.id.ll_groups_container)
    LinearLayout ll_groups;
    private DisplayMetrics mDm;
    private int mImageSize;
    private PicAdapter mPicAdapter;

    public GroupPicViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void setData(final Context context, List<String> pics) {
        if(pics==null||pics.size()==0){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height=0;
            params.setMargins(0,0,0,0);
            ll_groups.setLayoutParams(params);
            return;
        }
        final List<String> adPics = new ArrayList<>();
        //最多显示9张照片
        Observable.from(pics).take(9).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mDm = context.getResources().getDisplayMetrics();
                mImageSize = (int) ((mDm.widthPixels - 62 * mDm.density) / 3);
                mPicAdapter = new PicAdapter(context, mImageSize, adPics);
                GridLayoutManager layoutManager = new GridLayoutManager(context,
                        3,
                        GridLayoutManager.VERTICAL,
                        false);
                mRvGroupPic.setLayoutManager(layoutManager);
                mRvGroupPic.setAdapter(mPicAdapter);
                addNewButtonAndRefresh();
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
               adPics.add(s);
            }
        });

    }

    private void addNewButtonAndRefresh() {
        int size = mPicAdapter.getItemCount();
        int rows = 1;
        if (size > 6) {
            rows = 3;
        } else if (size > 3) {
            rows = 2;
        }
        mRvGroupPic.getLayoutParams().height = Math.round(mImageSize * rows + rows * 10 *
                mDm.density);
        mPicAdapter.notifyDataSetChanged();
    }
}
