package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        //returning binder object
        return binder;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Implement binder
    private final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public int calculateSum(int a, int b) throws RemoteException {
            int c= a+b;
            return c;
        }
    };
}