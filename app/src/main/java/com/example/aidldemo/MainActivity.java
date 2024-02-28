package com.example.aidldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    IMyAidlInterface iMyAidlInterface;
    private static final String TAG = "AIDLDemo";

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            Log.d(TAG, "Service is Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "Service is Disconnected");
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("AIDLService");
        intent.setPackage("com.example.aidldemo");
        bindService(intent, connection, BIND_AUTO_CREATE);

        EditText num1 = findViewById(R.id.editTextNumber1);
        EditText num2 = findViewById(R.id.editTextNumber2);
        TextView ans = findViewById(R.id.textViewAnswer);

        Button click = findViewById(R.id.buttonSubmitAdd);
        click.setOnClickListener(view -> {
            int n1 = Integer.parseInt(num1.getText().toString());
            int n2 = Integer.parseInt(num2.getText().toString());
            try {
                int a = iMyAidlInterface.calculateSum(n1, n2);
                ans.setText(Integer.toString(a));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }
}