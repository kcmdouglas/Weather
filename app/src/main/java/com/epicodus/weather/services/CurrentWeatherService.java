package com.epicodus.weather.services;

import android.content.Context;

import com.epicodus.weather.R;
import com.epicodus.weather.models.ThreeHourBlock;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.openweathermap.org/data/2.5/forecast?&units=imperial&APPID=" + appid).newBuilder();
        urlBuilder.addQueryParameter("q", city);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<ThreeHourBlock> processResults(Response response) {

        ArrayList<ThreeHourBlock> threeHourBlocks = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject weatherJSON = new JSONObject(jsonData);
                JSONArray weatherResponse = weatherJSON.getJSONArray("list");
                for (int i = 0; i < weatherResponse.length(); i++) {
                    JSONObject weatherBlockJSON = weatherResponse.getJSONObject(i);
                    Integer weatherId = weatherBlockJSON.getJSONArray("weather").getJSONObject(0).getInt("id");
                    String mainDescription = weatherBlockJSON.getJSONArray("weather").getJSONObject(0).getString("main");
                    String longDescription = weatherBlockJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                    Double tempMin = weatherBlockJSON.getJSONObject("main").getDouble("temp_min");
                    Double tempMax = weatherBlockJSON.getJSONObject("main").getDouble("temp_max");
                    Double humidity = weatherBlockJSON.getJSONObject("main").getDouble("humidity");
                    Integer unconvertedDate = weatherBlockJSON.getInt("dt");

                    ThreeHourBlock threeHourBlock = new ThreeHourBlock(weatherId, mainDescription, longDescription, tempMin, tempMax, humidity, unconvertedDate);
                    threeHourBlocks.add(threeHourBlock);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return threeHourBlocks;
    }


}
