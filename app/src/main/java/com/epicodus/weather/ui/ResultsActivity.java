package com.epicodus.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.weather.R;
import com.epicodus.weather.models.CurrentWeather;
import com.epicodus.weather.models.ThreeHourBlock;
import com.epicodus.weather.services.CurrentWeatherService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity {
    public ArrayList<ThreeHourBlock> mThreeHourBlocks = new ArrayList<>();
    public CurrentWeather mCurrentWeather;
    @Bind(R.id.introResultText) TextView mIntroText;
    @Bind(R.id.test_list_view) ListView mTestList;
    @Bind(R.id.currentWeatherImageView) ImageView mCurrentWeatherImageView;
    @Bind(R.id.currentDescription) TextView mCurrentDescription;
    @Bind(R.id.currentTemp) TextView mCurrentTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mIntroText.setText("Here's the forecast for " + location);
        getCurrentWeather(location);
        getForecast(location);
    }

    private void getForecast(String location) {
        final CurrentWeatherService currentWeatherService = new CurrentWeatherService(this);

        currentWeatherService.findForecast(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mThreeHourBlocks = currentWeatherService.processResults(response);

                ResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] mainDescriptions = new String[mThreeHourBlocks.size()];
                        for(int i = 0; i < mainDescriptions.length; i++) {
                            mainDescriptions[i] = mThreeHourBlocks.get(i).getMainDescription();
                        }
                       ArrayAdapter adapter = new ArrayAdapter(ResultsActivity.this, android.R.layout.simple_list_item_1, mainDescriptions);
                       mTestList.setAdapter(adapter);
                    }
                });
            }
        });
    }

    private void getCurrentWeather(String location) {
        final CurrentWeatherService currentWeatherService = new CurrentWeatherService(this);

        currentWeatherService.findCurrentWeather(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mCurrentWeather = currentWeatherService.processCurrentResults(response);

                ResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(ResultsActivity.this)
                                .load(mCurrentWeather.getImageUrl())
                                .into(mCurrentWeatherImageView);
                        mCurrentDescription.setText(mCurrentWeather.getLongDescription());
                        mCurrentTemp.setText(mCurrentWeather.getCurrentTemp() + "°");
                    }
                });
            }
        });
    }
}

