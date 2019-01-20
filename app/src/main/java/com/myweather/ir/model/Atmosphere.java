
package com.myweather.ir.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atmosphere {

    @SerializedName("humidity")
    @Expose
    private double humidity;
    @SerializedName("visibility")
    @Expose
    private double visibility;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("rising")
    @Expose
    private double rising;

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public double getRising() {
        return rising;
    }

    public void setRising(double rising) {
        this.rising = rising;
    }

}
