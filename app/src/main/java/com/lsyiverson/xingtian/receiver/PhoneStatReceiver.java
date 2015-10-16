package com.lsyiverson.xingtian.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.lsyiverson.xingtian.service.MobileInfoService;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class PhoneStatReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        Observable.just(intent.getAction())
            .filter(TelephonyManager.ACTION_PHONE_STATE_CHANGED::equals)
            .filter(action -> telephonyManager.getCallState() == TelephonyManager.CALL_STATE_RINGING)
            .flatMap(action -> Observable.create(new PhoneStateOnSubscribe(context, telephonyManager)))
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribe(serviceIntent -> context.startService(serviceIntent));
    }

    private final class PhoneStateOnSubscribe implements Observable.OnSubscribe<Intent> {
        private final Context context;
        private final TelephonyManager telephonyManager;

        PhoneStateOnSubscribe(Context context, TelephonyManager telephonyManager) {
            this.context = context;
            this.telephonyManager = telephonyManager;
        }

        @Override
        public void call(Subscriber<? super Intent> subscriber) {
            PhoneStateListener listener = new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);

                    switch (state) {
                        case TelephonyManager.CALL_STATE_RINGING:
                            Intent intent = new Intent(context, MobileInfoService.class);
                            intent.putExtra(MobileInfoService.MOBILE, incomingNumber);
                            subscriber.onNext(intent);
                            break;
                    }
                }
            };

            telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
            telephonyManager.listen(listener, PhoneStateListener.LISTEN_NONE);
        }
    }
}
