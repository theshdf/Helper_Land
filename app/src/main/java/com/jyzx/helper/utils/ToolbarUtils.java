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

package com.jyzx.helper.utils;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dylanc.loadinghelper.LoadingHelper;
import com.dylanc.loadinghelper.ViewType;
import com.jyzx.helper.ui.adapter.loading.CommonHeadAdapter;
import com.jyzx.helper.ui.adapter.loading.CustomHeaderAdapter;
import com.jyzx.helper.ui.adapter.loading.NavIconType;
import com.jyzx.helper.ui.adapter.loading.ScrollingDecorAdapter;
import com.jyzx.helper.ui.adapter.loading.ToolbarAdapter;

import kotlin.jvm.functions.Function1;

/**
 * @author Dylan Cai
 */
@SuppressWarnings("UnusedReturnValue")
public class ToolbarUtils {
  public static LoadingHelper setToolbar(Activity activity, String title, NavIconType type) {
    return setToolbar(activity, title, type, 0, null);
  }

  public static LoadingHelper setToolbar(Activity activity, String title, NavIconType type, int menuId,
                                         Function1<? super MenuItem, Boolean> onMenuItemClick) {
    LoadingHelper loadingHelper = new LoadingHelper(activity);
    loadingHelper.register(ViewType.TITLE, new ToolbarAdapter(title, type, menuId, onMenuItemClick));
    loadingHelper.setDecorHeader(ViewType.TITLE);
    return loadingHelper;
  }

  public static LoadingHelper setCustomToolbar(Activity activity, View.OnClickListener onMessageClick,
                                               int firstDrawableId, View.OnClickListener onFirstBtnClick,
                                               int secondDrawableId, View.OnClickListener onSecondBtnClick) {
    LoadingHelper loadingHelper = new LoadingHelper(activity);
    loadingHelper.register(ViewType.TITLE, new CustomHeaderAdapter(onMessageClick,
        firstDrawableId, onFirstBtnClick, secondDrawableId, onSecondBtnClick));
    loadingHelper.setDecorHeader(ViewType.TITLE);
    return loadingHelper;
  }

  public static LoadingHelper setScrollingToolbar(Activity activity, String title) {
    LoadingHelper loadingHelper = new LoadingHelper(activity);
    loadingHelper.setDecorAdapter(new ScrollingDecorAdapter(title));
    return loadingHelper;
  }

  /**
   * 只含有标题和返回按钮
   * @param activity
   * @return
   */
  public static LoadingHelper setCommonToolbar(Activity activity, String title) {
    return setCommonToolbar(activity,title,null,null);
  }

  public static LoadingHelper setCommonToolbar(Activity activity, String title,View.OnClickListener onClickListener,String rightText) {
    LoadingHelper loadingHelper = new LoadingHelper(activity);
    loadingHelper.register(ViewType.TITLE, new CommonHeadAdapter(activity,title,onClickListener,rightText));
    loadingHelper.setDecorHeader(ViewType.TITLE);
    return loadingHelper;
  }
}
