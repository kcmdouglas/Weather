package com.epicodus.weather.models;

/**
 * Created by Guest on 3/22/16.
 */
public class CurrentWeather {
    private Integer mWeatherId;
    private String mMainDescription;
    private String mLongDescription;
    private Integer mTemperatureMin;
    private Integer mTemperatureMax;
    private Integer mSunrise;
    private Integer mSunset;

    public CurrentWeather (Integer weatherId, String mainDescription, String longDescription, Integer temperatureMin, Integer temperatureMax, Integer sunrise, Integer sunset) {
        this.mWeatherId = weatherId;
        this.mMainDescription = mainDescription;
        this.mLongDescription = longDescription;
        this.mTemperatureMin = temperatureMin;
        this.mTemperatureMax = temperatureMax;
        this.mSunrise = sunrise;
        this.mSunset = sunset;
    }

    public Integer getWeatherId() {
        return mWeatherId;
    }

    public String getMainDescription() {
        return mMainDescription;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public Integer getTemperatureMin() {
        return mTemperatureMin;
    }

    public Integer getTemperatureMax() {
        return mTemperatureMax;
    }

    public Integer getSunrise() {
        return mSunrise;
    }

    public Integer getSunset() {
        return mSunset;
    }
}
