package com.epicodus.weather.models;
import java.util.Date;
import java.text.DateFormat;

public class ThreeHourBlock {
    private Integer mWeatherId;
    private String mMainDescription;
    private String mLongDescription;
    private Double mTemperatureMin;
    private Double mTemperatureMax;
    private Double mHumidity;
    private Integer mUnconvertedDate;
    private Date mConvertedDateFull;
    private String mDateWithoutTime;
    private String mDateWithTime;


    public ThreeHourBlock(Integer weatherId, String mainDescription, String longDescription, Double temperatureMin, Double temperatureMax, Double humidity, Integer unconvertedDate) {
        this.mWeatherId = weatherId;
        this.mMainDescription = mainDescription;
        this.mLongDescription = longDescription;
        this.mTemperatureMin = temperatureMin;
        this.mTemperatureMax = temperatureMax;
        this.mHumidity = humidity;
        this.mUnconvertedDate = (unconvertedDate * 1000);
        this.mConvertedDateFull = new Date(mUnconvertedDate);
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

    public Double getTemperatureMin() {
        return mTemperatureMin;
    }

    public Double getTemperatureMax() {
        return mTemperatureMax;
    }

    public Double getHumidity() {
        return mHumidity;
    }

    public Integer getUnconvertedDate() {
        return mUnconvertedDate;
    }
    public Date getConvertedDateFull() {
        return mConvertedDateFull;
    }




}