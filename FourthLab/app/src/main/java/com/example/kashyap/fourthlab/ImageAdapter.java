package com.example.kashyap.fourthlab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private String[] filepaths;
    private LayoutInflater inflater;


    public ImageAdapter(String[] filepaths, Activity activity) {
        this.filepaths = filepaths;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        if(filepaths!=null)
            return filepaths.length;
        else return 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View view1 = view;
        if(view==null)
            view1= inflater.inflate(R.layout.griditem,null);

        ImageView imageView = view1.findViewById(R.id.imageView);
        Bitmap bitmap = BitmapFactory.decodeFile(filepaths[position]);
        imageView.setImageBitmap(bitmap);

        return view1;
    }
}
