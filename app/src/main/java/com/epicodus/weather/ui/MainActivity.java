package com.epicodus.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.epicodus.weather.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
        mLocationButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mLocationButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
