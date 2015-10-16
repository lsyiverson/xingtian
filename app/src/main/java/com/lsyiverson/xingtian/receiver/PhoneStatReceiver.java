package com.lsyiverson.xingtian.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.lsyiverson.xingtian.service.MobileInfoService;

public class PhoneStatReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "broadcast");
        if (intent.getAction() == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.listen(new Listener(context), PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    private static class Listener extends PhoneStateListener {
        private final Context context;

        public Listener(Context context) {
            this.context = context;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Intent intent = new Intent(context, MobileInfoService.class);
                    intent.putExtra(MobileInfoService.MOBILE, incomingNumber);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startService(intent);
                    break;
            }
        }
    }
}
