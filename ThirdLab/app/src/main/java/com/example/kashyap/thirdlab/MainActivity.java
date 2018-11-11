package com.example.kashyap.thirdlab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText name;
    TextView retrieve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editName);
        retrieve = findViewById(R.id.displayname);

        if(savedInstanceState!=null) {

            name.setText(savedInstanceState.getString("Name"));
            Toast.makeText(MainActivity.this, "Instance Saved", Toast.LENGTH_SHORT).show();
        }

        sharedPreferences = getApplicationContext().getSharedPreferences("God",0);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString("Name",name.getText().toString());
        outPersistentState.putString("Name",name.getText().toString());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void saveData(View view) {

        String string = this.name.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name",string);
        editor.commit();

    }


    public void retrieveData(View view) {

        String string = sharedPreferences.getString("Name",null);
        retrieve.setText(string);
    }

    public void nextScreen(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
