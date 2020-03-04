package edu.neu.my12306.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityController{

    private static  List<Activity> activityList = new ArrayList<>();

    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public void finishAll(){
        for(Activity activity:activityList){
            activity.finish();
        }
        activityList.clear();
    }

}
