package com.dongnaoedu.chapter09.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

public class ShockReceiver extends BroadcastReceiver {

    public static final String SHOCK_ACTION = "com.dongnaoedu.chapter09.shock";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(SHOCK_ACTION)){
            Log.d("ning","震动");
            // 从系统服务中获取震动管理器
            Vibrator vb = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            // 命令震动器吱吱个若干秒，这里的500表示500毫秒
            vb.vibrate(500);
        }
    }
}