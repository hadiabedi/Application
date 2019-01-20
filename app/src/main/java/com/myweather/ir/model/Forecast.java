
package com.myweather.ir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("low")
    @Expose
    private Integer low;
    @SerializedName("high")
    @Expose
    private Integer high;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("code")
    @Expose
    private Integer code;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
