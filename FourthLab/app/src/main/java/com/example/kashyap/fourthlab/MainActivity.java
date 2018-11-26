package com.example.kashyap.fourthlab;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Uri filename;
    GridView gridView;
    ImageAdapter adapter;
    private File[] listFiles;
    private String[] FilePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);

        File file = new File("/storage/emulated/0/Pictures/LabProgram");
        file.mkdirs();

        if(file.isDirectory())
        {
            listFiles = file.listFiles();
            FilePaths = new String[listFiles.length];
            for (int i = 0; i < listFiles.length; i++)
                FilePaths[i] = listFiles[i].getAbsolutePath();
        }

        adapter = new ImageAdapter(FilePaths,this);
        gridView.setAdapter(adapter);
    }

    public void capture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File directory = myDirectory();
        Log.d("Image thing:","Directory name - "+directory);
        File file = new File(directory,new SimpleDateFormat("ddMMYYYY_HHmmss").format(new Date())+".jpg");
        Log.d("Image thing:","file name - "+file);
        filename = FileProvider.getUriForFile(getApplicationContext(),getPackageName()+".fileprovider",file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,filename);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
            if(resultCode==RESULT_OK)
                Toast.makeText(this,"Capture Successful",Toast.LENGTH_SHORT).show();
    }

    private File myDirectory()
    {
        File mediaStorageArea = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"LabProgram");

        if(!mediaStorageArea.exists())
            if(!mediaStorageArea.mkdirs())
                return null;

        return mediaStorageArea;
    }
}
