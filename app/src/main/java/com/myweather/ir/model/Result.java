
package com.myweather.ir.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("astronomy")
    @Expose
    private Astronomy astronomy;
    @SerializedName("atmosphere")
    @Expose
    private Atmosphere atmosphere;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("pubDate")
    @Expose
    private Integer pubDate;
    @SerializedName("forecasts")
    @Expose
    private List<Forecast> forecasts = null;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Integer getPubDate() {
        return pubDate;
    }

    public void setPubDate(Integer pubDate) {
        this.pubDate = pubDate;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

}
