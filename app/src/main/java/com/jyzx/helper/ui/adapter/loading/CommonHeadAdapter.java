/*
 * Copyright (c) 2019. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jyzx.helper.ui.adapter.loading;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.dylanc.loadinghelper.LoadingHelper;
import com.jyzx.helper.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dylan Cai
 */
public class CommonHeadAdapter extends LoadingHelper.Adapter<CommonHeadAdapter.ViewHolder> {

  private View.OnClickListener onRightClickListener;
  private String title,rightText;
  private Activity activity;

  public CommonHeadAdapter(Activity activity,String title) {
    this.title = title;
    this.activity = activity;
  }

  /**
   * 如果
   * @param activity
   * @param title
   * @param onRightClickListener
   */
  public CommonHeadAdapter(Activity activity,String title,View.OnClickListener onRightClickListener,String rightText) {
    this.onRightClickListener = onRightClickListener;
    this.title = title;
    this.activity = activity;
    this.rightText = rightText;
  }

  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NotNull LayoutInflater inflater, @NotNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.common_head_black, parent, false));
  }

  @Override
  public void onBindViewHolder(@NotNull ViewHolder holder) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      holder.getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    if(onRightClickListener !=null)
    {
      holder.tvRight.setVisibility(View.VISIBLE);
      holder.tvRight.setText(rightText);
      holder.tvRight.setOnClickListener(onRightClickListener);
    }
    holder.title.setText(title);
    holder.ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        activity.finish();
      }
    });

  }

  static class ViewHolder extends LoadingHelper.ViewHolder {
    private final ImageView ivBack;
    private final TextView title;
    private final TextView tvRight;

    ViewHolder(@NonNull View rootView) {
      super(rootView);
      ivBack = rootView.findViewById(R.id.ivBack);
      title = rootView.findViewById(R.id.title);
      tvRight = rootView.findViewById(R.id.tvRight);
    }
    private Activity getActivity() {
      return (Activity) getRootView().getContext();
    }
  }
}
