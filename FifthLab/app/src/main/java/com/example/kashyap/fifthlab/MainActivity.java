package com.example.kashyap.fifthlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Number, SMSmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number = findViewById(R.id.number);
        SMSmessage = findViewById(R.id.smsmessage);
    }

    public void SendSMS(View view) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(Number.getText().toString(),null,SMSmessage.getText().toString(),null,null);
        Toast.makeText(this, "SMS sent", Toast.LENGTH_SHORT).show();
    }

    public void EmailAct(View view) {
        Intent intent = new Intent(MainActivity.this,EmailActivity.class);
        startActivity(intent);
    }
}
