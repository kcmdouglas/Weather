package com.epicodus.weather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.background_main) ImageView mBackground;
    @Bind(R.id.locationButton) Button mLocationButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Picasso.with(MainActivity.this)
                .load(R.drawable.clouds_resize)
                .fit()
                .centerCrop()
                .into(mBackground);
    }
}
