package com.example.kashyap.secondlab;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button contextbutton;
    ListView listView;
    String names[];
    ArrayList<String> AL;
    ProgressDialog progressDialog;
    MyCustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextbutton = findViewById(R.id.contextbutton);
        listView = findViewById(R.id.listxml);
        customView = findViewById(R.id.customThing);
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customView.SwapColor();
            }
        });
        names = new String[]{"Harsha", "Buddi", "Kashyap", "Hello", "World", "Run", "Router"};
        AL = new ArrayList<>();

        for(int i=0;i<names.length; i++)
            AL.add(names[i]);


        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.listitems, R.id.textView,AL);

        listView.setAdapter(adapter);
        registerForContextMenu(contextbutton);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.op1: Toast.makeText(this,"Nice Button",Toast.LENGTH_SHORT).show();
            break;

            case R.id.op2:
                Toast.makeText(this, "Alright Whatever", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op3:
                Toast.makeText(this, "All Buttons... Nice!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu,menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.optionsmenu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId())
        {
            case R.id.parliament:
                Toast.makeText(this,"First option: Parliament",Toast.LENGTH_SHORT).show();
                break;

            case R.id.tajmahal: Toast.makeText(this,"Second Option: TajMahal",Toast.LENGTH_SHORT).show();
            break;

            case R.id.rbhavan: Toast.makeText(this,"Third Option: Rashtrapati Bhavan",Toast.LENGTH_SHORT).show();
            break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickAlert(View view) {
        AlertDialog.Builder alertbuilder = new AlertDialog.Builder(this);
        alertbuilder.setTitle("Alert Dialog");
        alertbuilder.setMessage("This is an alert dialog box");

        alertbuilder.create().show();
    }

    public void onClickPD(View view) {

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(100);
        progressDialog.setTitle("ProgressDialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog
                            .getMax()) {
                        Thread.sleep(200);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDialog.getProgress() == progressDialog
                                .getMax()) {
                            progressDialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.incrementProgressBy(1);
        }
    };
}
