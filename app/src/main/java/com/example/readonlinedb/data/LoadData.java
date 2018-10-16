package com.example.readonlinedb.data;



import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadData extends AsyncTaskLoader<String> {

    private String result;
    private String mUrl;
    LoadData(@NonNull Context context) {
        super(context);
    }
    public LoadData(@NonNull Context context, String url) {
        super(context);
        this.mUrl =url;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        try {
            URL url = new URL(mUrl);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder sb=new StringBuilder();
            while ((result=bufferedReader.readLine())!=null){
                sb.append(result);
            }
            result=sb.toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAGG",e.getMessage());
            return "";
        }
    }

    @Override
    protected void onStartLoading() {
        if (result==null){
            forceLoad();
        }else {
            deliverResult(result);
        }

    }

    @Override
    public void deliverResult(@Nullable String data) {
        result=data;
        super.deliverResult(data);
    }
}

