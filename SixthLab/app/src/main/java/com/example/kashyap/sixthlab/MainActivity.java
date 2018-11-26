package com.example.kashyap.sixthlab;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Notify(View view) {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

        Notification notification = new Notification.Builder(MainActivity.this)
                                        .setContentTitle("My Notification")
                                        .setContentText("Mah Lyf Mah Rulzz")
                                        .setSmallIcon(R.drawable.ic_launcher_background)
                                        .setContentIntent(pendingIntent)
                                        .getNotification();

        notification.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0,notification);


    }

    public void Contacts(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100)
            if (resultCode==RESULT_OK)
            {
                Uri contacts = data.getData();

                Cursor cursor = managedQuery(contacts,null,null,null,null);

                if(cursor.moveToFirst())
                {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Toast.makeText(this,""+name,Toast.LENGTH_SHORT).show();
                }
            }

    }
}
