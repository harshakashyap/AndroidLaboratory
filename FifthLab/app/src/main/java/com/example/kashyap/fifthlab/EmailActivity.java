package com.example.kashyap.fifthlab;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {


    EditText email, subject, msg, phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        email = findViewById(R.id.recipient);
        subject = findViewById(R.id.subject);
        msg = findViewById(R.id.text);
        phonenumber = findViewById(R.id.phonenumber);
    }

    public void email(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,email.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,msg.getText().toString());

        startActivity(Intent.createChooser(intent,"Send Mail..."));
        Log.i("EmailThing","Finished Emailing");

    }

    public void CallIt(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phonenumber.getText().toString()));
        startActivity(intent);
    }
}
