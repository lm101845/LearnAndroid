package com.dongnaoedu.chapter09.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.util.Log;

import com.dongnaoedu.chapter09.util.NetworkUtil;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            NetworkInfo networkInfo = intent.getParcelableExtra("networkInfo");
            // 收到一个网络变更广播，网络大类为MOBILE，网络小类为HSPA，网络制式为3G，网络状态为DISCONNECTED
            // 收到一个网络变更广播，网络大类为WIFI，网络小类为，网络制式为未知，网络状态为CONNECTED
            String text = String.format("收到一个网络变更广播，网络大类为%s，" +
                            "网络小类为%s，网络制式为%s，网络状态为%s",
                    networkInfo.getTypeName(),
                    networkInfo.getSubtypeName(),
                    NetworkUtil.getNetworkClass(networkInfo.getSubtype()),
                    networkInfo.getState().toString());
            Log.d("ning", text);
        }
    }
}
