package com.jyzx.helper.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dylanc.loadinghelper.LoadingHelper;

public abstract class BaseFragment extends Fragment {
    //用于保存状态
    private View view;
    protected LoadingHelper loadingHelper;

    public BaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(getContentLayoutId(), container, false);
           // initData();  kotlin中  在oncreateview中拿不到id
        }
        loadingHelper = new LoadingHelper(view);
        ViewGroup parent = (ViewGroup)view.getParent();
      /*  if(parent!=null){
            parent.removeView(view);
        }*/
        loadingHelper.setOnReloadListener(() -> {
            //刷新重新加载
            reload();
        });
        return loadingHelper.getDecorView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(view);
        initListener(view);
        initData();
    }

    //获取布局的id
    public abstract int getContentLayoutId();
    public abstract void initView(View view);
    public abstract void initListener(View view);
    public abstract  void initData();
    public abstract void reload();
}