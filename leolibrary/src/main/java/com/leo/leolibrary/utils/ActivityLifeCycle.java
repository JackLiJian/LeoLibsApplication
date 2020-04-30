package com.leo.leolibrary.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class ActivityLifeCycle implements Application.ActivityLifecycleCallbacks {
    /**
     * 上次检查时间，用于在运行时作为基准获取用户时间
     */
    public static long lastCheckTime = 0;
    /** 前台Activity数量 **/
    private int foregroundActivityCount = 0;
    /**
     * Activity是否在修改配置，
     */
    private boolean isChangingConfigActivity = false;
    /**
     *  应用将要切换到前台
     */
    private boolean willSwitchToForeground = false;
    /**
     * 当前是否在前台
     */
    private boolean isForegroundNow = false;
    /**
     * 上次暂停的Activity信息
     */
    private String lastPausedActivityName;
    private int lastPausedActivityHashCode;
    private long lastPausedTime;
    private long appUseReduceTime = 0;
    /**
     *  每次有Activity启动时的开始时间点
     */
    private long appStartTime = 0L;

    /**
     * 本次统计时，运行的时间
     */
    private long runTimeThisDay = 0L;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Log.d("TAG","onActivityCreated" + getActivityName(activity));
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d("TAG","onActivityStarted " + getActivityName(activity) + " " + foregroundActivityCount);
        //前台没有Activity，说明新启动或者将从从后台恢复
        if (foregroundActivityCount == 0 || !isForegroundNow) {
            willSwitchToForeground = true;
        }else {
            //应用已经在前台，此时保存今日运行的时间。
            runTimeThisDay  = System.currentTimeMillis() - appStartTime;
            lastCheckTime = System.currentTimeMillis();
//            saveTodayPlayTime(activity, runTimeThisDay);
        }
        appStartTime = System.currentTimeMillis();
        if (isChangingConfigActivity) {
            isChangingConfigActivity = false;
            return;
        }
        foregroundActivityCount += 1;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d("TAG","onActivityResumed" + getActivityName(activity));
        //在这里更新检查时间点，是为了保证从后台恢复到前台，持续计时的准确性。
        lastCheckTime = System.currentTimeMillis();
        addAppUseReduceTimeIfNeeded(activity);
        if (willSwitchToForeground && isInteractive(activity)) {
            isForegroundNow = true;
            Log.d("TAG", "switch to foreground");
        }
        if (isForegroundNow) {
            willSwitchToForeground = false;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d("TAG","onActivityPaused" + getActivityName(activity));
        lastPausedActivityName = getActivityName(activity);
        lastPausedActivityHashCode = activity.hashCode();
        lastPausedTime = System.currentTimeMillis();
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d("TAG","onActivityStopped" + getActivityName(activity));
        addAppUseReduceTimeIfNeeded(activity);
        //如果这个Activity实在修改配置，如旋转等，则不保存时间直接返回
        if (activity.isChangingConfigurations()) {
            isChangingConfigActivity = true;
            return;
        }
        //该Activity要进入后台，前台Activity数量-1。
        foregroundActivityCount -= 1;
        //当前已经是最后的一个Activity，代表此时应用退出了，保存时间。
        // 如果跨天了，则从新一天的0点开始计时
        if (foregroundActivityCount == 0) {
            isForegroundNow = false;
            Log.d("TAG","switch to background (reduce time[" + appUseReduceTime + "])");
            if (getTodayStartTime() > appStartTime){
                runTimeThisDay = System.currentTimeMillis() - getTodayStartTime();
            }else {
                runTimeThisDay = System.currentTimeMillis() - appStartTime;
            }
//            saveTodayPlayTime(activity, runTimeThisDay);
            lastCheckTime = System.currentTimeMillis();
            Log.d("TAG","run time  :"  + runTimeThisDay);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Log.d("TAG","onActivitySaveInstanceState" + getActivityName(activity));
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d("TAG","onActivityDestroyed" + getActivityName(activity));
    }

    private void addAppUseReduceTimeIfNeeded(Activity activity) {
        if (getActivityName(activity).equals(lastPausedActivityName) && activity.hashCode() == lastPausedActivityHashCode) {
            long now = System.currentTimeMillis();
            if (now - lastPausedTime > 1000) {
                appUseReduceTime += now - lastPausedTime;
            }
        }
        lastPausedActivityHashCode = -1;
        lastPausedActivityName = null;
        lastPausedTime = 0;
    }

    private boolean isInteractive(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            return pm.isInteractive();
        } else {
            return pm.isScreenOn();
        }
    }

    private String getActivityName(final Activity activity) {
        return activity.getClass().getCanonicalName();
    }

    /**
     * 获取今日0点的时间点，/1000*1000保证每次取值相同。
     * @return
     */
    private long getTodayStartTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long time = calendar.getTimeInMillis()/1000*1000;
        return time;
    }

//    /**
//     * 保存今日运行时间，以今日0点的时间作为Key值。
//     * @param context
//     * @param time
//     */
//    private void saveTodayPlayTime(Context context, long time){
//        long todayTime = PreferenceUtil.getLongValue(context,PreferenceUtil.SP_NAME,String.valueOf(getTodayStartTime()) , 0);
//        Log.d("today :" + String.valueOf(getTodayStartTime()) + " : time : " + todayTime);
//        Log.d("today totalTime:" +time +" :" + (todayTime + time));
//        PreferenceUtil.putLongValue(context,PreferenceUtil.SP_NAME,String.valueOf(getTodayStartTime()), todayTime+time);
//        long yesterdayTime = PreferenceUtil.getLongValue(context,PreferenceUtil.SP_NAME_INJOY, String.valueOf(getTodayStartTime() - Constants.ONE_DAY) , 0);
//        //清除前一天的值
//        if ( yesterdayTime > 0){
//            PreferenceUtil.removeData(context,PreferenceUtil.SP_NAME_INJOY,
//                    String.valueOf(getTodayStartTime() - Constants.ONE_DAY));
//        }
//    }
}
