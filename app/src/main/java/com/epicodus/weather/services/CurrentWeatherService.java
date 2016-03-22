package com.epicodus.weather.services;

import android.content.Context;

import com.epicodus.weather.R;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Kassidy on 3/21/2016.
 */
public class CurrentWeatherService {
    private Context mContext;

    public CurrentWeatherService(Context context) {
        this.mContext = context;
    }

    public void findCurrentWeather(String city, Callback callback) {
        String appid = mContext.getString(R.string.weather_API_key);

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.openweathermap.org/data/2.5/weather?q=").newBuilder();
        urlBuilder.addQueryParameter("zip", city);
//        urlBuilder.addQueryParameter("country code", "us");
        urlBuilder.addQueryParameter("appid", appid);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
