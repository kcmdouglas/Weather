package com.epicodus.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.weather.R;
import com.epicodus.weather.models.ThreeHourBlock;
import com.epicodus.weather.services.CurrentWeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity {
    public ArrayList<ThreeHourBlock> mThreeHourBlocks = new ArrayList<>();
    @Bind(R.id.introResultText) TextView mIntroText;
    @Bind(R.id.test_list_view) ListView mTestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mIntroText.setText("Here's how the weather is looking in " + location);

        getCurrentWeather(location);
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
}
