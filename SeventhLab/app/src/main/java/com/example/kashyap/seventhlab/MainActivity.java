package com.example.kashyap.seventhlab;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
    }

    public void fetchData(View view) {
        new WeirdThing().execute("https://api.androidhive.info/contacts/");
    }

    public class WeirdThing extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ListAdapter adapter = new SimpleAdapter(MainActivity.this,arrayList,R.layout.list_item,new String[]{"name","email","id"}, new  int[]{R.id.nameview,R.id.emailview,R.id.idview});
            listView.setAdapter(adapter);
        }

        @Override
        protected String doInBackground(String... strings) {

            String response = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                response = convertStreamToString(inputStream);

                if(response!=null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("name",object.getString("name"));
                            hashMap.put("email",object.getString("email"));
                            hashMap.put("id",object.getString("id"));

                            arrayList.add(hashMap);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String convertStreamToString(InputStream inputStream) throws IOException {

            String line;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            while ((line=bufferedReader.readLine())!=null)
                stringBuilder.append(line).append('\n');


            return stringBuilder.toString();
        }
    }
}
