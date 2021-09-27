package com.jyzx.helper.utils;

import android.app.Activity;

import com.jyzx.helper.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcm on 2016/4/1.
 * qq:656025633
 */
public class ActivityControlUtil {
    private static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        if(activity == null){}
        activities.remove(activity);
    }
    public static void removeAll(){
        for(Activity activity:activities){
           activity.finish();
        }
    }

    public static Activity getTopActivity(){
        if(activities.size()>0){
           return activities.get(activities.size()-1);
        }
        return null;
    }

    public static void removeActivityexcepeMain(){
        for (int i=0;i<activities.size();i++){
            if(activities.get(i) instanceof MainActivity){
                continue;
            }
            else{
                //删除
                activities.get(i).finish();
                activities.remove(activities.get(i));
            }
        }
    }
}
