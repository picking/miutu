package com.andrew.miutu.fragment;

import android.content.Intent;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.andrew.miutu.Adapter.StaggeredAdapter;
import com.andrew.miutu.R;
import com.andrew.miutu.activity.ImageActivity;
import com.andrew.miutu.entity.ImageDetial;
import com.andrew.miutu.entity.ImgParam;
import com.andrew.miutu.entity.PicData;
import com.andrew.miutu.setting.CacheSetting;
import com.andrew.miutu.setting.HttpSetting;
import com.andrew.miutu.setting.MConstants;
import com.andrew.miutu.utils.AnimationUtil;
import com.andrew.miutu.view.XListView;
import com.andrew.miutu.view.pla.internal.PLA_AdapterView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 2015/4/21.
 */
public class TagFragment extends BaseFragment {

    @ViewInject(R.id.tag_list)
    private XListView tag_list;

    private ImgParam param;
    private RequestParams httpparam;
    private int pageCount = 0;
    private StaggeredAdapter adapter;
    private List<ImageDetial> list;
    private HttpHandler<String> rhttp;


    public TagFragment() {
    }

    public static TagFragment newInstance() {
        return new TagFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tag;
    }

    @Override
    public void initAfter() {
        ViewUtils.inject(this, getContentView());
        initTag();
        list = (List<ImageDetial>) CacheSetting.instance().getAsObject("Plist");
        if (list == null){
            list = new ArrayList<>();
            dataExecute(true);
        }
        adapter = new StaggeredAdapter(list);
        tag_list.setAdapter(adapter);
        tag_list.setPullRefreshEnable(true);
        tag_list.setPullLoadEnable(true);
        tag_list.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        tag_list.setOnItemClickListener(new PLA_AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(PLA_AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                intent.putExtra("DataList",(Serializable)list);
                intent.putExtra("Position",--position);
                AnimationUtil.startActivity(getActivity(),intent);
            }
        });
    }

    private void initTag() {
        param = new ImgParam();
        param.setCol("美女");
        param.setTag("小清新");
        param.setRn(MConstants.PERPAGE);
    }

    private void dataExecute(final boolean isRefresh) {
        httpUnBind(rhttp);
        param.setPn(MConstants.PERPAGE * pageCount + 1);
        HttpSetting.instance(getApplicationContext());
        if (httpparam == null) {
            httpparam = new RequestParams("utf-8");
            httpparam.addHeader("User-agent", "Mozilla/5.0");
            httpparam.addHeader("Content-Type",
                    "application/x-www-form-urlencoded");
        }
        rhttp = HttpSetting.http.send(HttpRequest.HttpMethod.GET, param.toString(), httpparam, new RequestCallBack<String>() {

            @Override
            public void onStart() {
                super.onStart();
                LogUtils.e(param.toString());
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                PicData data = JSON.parseObject(responseInfo.result,
                        PicData.class);
                if (isRefresh) {
                    list.clear();
                }
                data.getImgs().remove(data.getImgs().size() - 1);
                list.addAll(data.getImgs());
                if (isRefresh){
                    CacheSetting.instance().put("Plist",(Serializable)list);
                }
                adapter.notifyDataSetChanged();
                executeDone(isRefresh);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtils.d(s, e);
                backPage(isRefresh);
                executeDone(isRefresh);
            }

            @Override
            public void onCancelled() {
                super.onCancelled();
//                backPage(isRefresh);
                executeDone(isRefresh);
            }
        });
    }

    public void backPage(boolean isRefresh) {
        if (!isRefresh) {
            pageCount--;
        }
    }

    public void executeDone(boolean isRefresh) {
        if (isRefresh) {
            tag_list.stopRefresh();
        } else {
            tag_list.stopLoadMore();
        }
        httpUnBind(rhttp);
    }

    public void refresh() {
        pageCount = 0;
        dataExecute(true);
    }


    public void loadMore() {
        pageCount++;
        dataExecute(false);
    }

    public void httpUnBind(HttpHandler<String> http) {
        if (http != null && !http.isCancelled()) {
            http.cancel();
            http = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        httpUnBind(rhttp);
    }
}
