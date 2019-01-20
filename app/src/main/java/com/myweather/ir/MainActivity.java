package com.myweather.ir;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myweather.ir.model.Forecast;
import com.myweather.ir.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


import ir.aid.library.Frameworks.helper.ConnectionHelper;
import ir.aid.library.Frameworks.utils.SharedPreferenceUtils;
import ir.aid.library.Interfaces.OnGetResponse;

public class MainActivity extends AppCompatActivity {
    private static final String CITY_KEY="city";
    private SharedPreferenceUtils preference;
    private TextView tvTemperature,tvWeatherState,tvCity,tvDate;
    private EditText enterCity;
    private ImageView weatherState;
    private Button btnSearchCity;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference=new SharedPreferenceUtils(MainActivity.this);
        checkCity();
        initView();

        btnSearchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (enterCity.getText().toString().equals("")){     //check city==null
                    enterCity.setError("Empty");
                }
                else {
                    // fill preference the amount of current city
                    preference.writeString(CITY_KEY,enterCity.getText().toString());
                    connection(enterCity.getText().toString());
                }
            }
        });
    }
    /*
        initial view
     */
    private void initView(){
        tvCity=findViewById(R.id.tvCityId);
        tvDate=findViewById(R.id.tvDateId);
        tvWeatherState=findViewById(R.id.tvWeatherStateId);
        recyclerView=findViewById(R.id.recyclerViewId);
        weatherState=findViewById(R.id.imgStateWeatherId);
        tvTemperature=findViewById(R.id.tvTemperature_id);
        enterCity=findViewById(R.id.edtCity_id);
        btnSearchCity = findViewById(R.id.btnSearchId);
    }
    private void checkCity(){
        if(!preference.readString(CITY_KEY,"").equals("")){
            connection(preference.readString(CITY_KEY,""));
        }
    }
    private void connection(String city){

        String url = "http://phoenix-iran.ir/Files_php_App/WeatherApi/newApiWeather.php";

        new ConnectionHelper(url , 5000) // ساخت یک ارتباط به اینترنت برای دریافت اطلاعات از اینترنت
                .addStringRequest("city" , city)// ارسال اسم شهر به سرور
                .addStringRequest("unit" , "c")// دریافت اطلاعات مبتنی بر سانتی گراد
                .getResponse(new OnGetResponse() {
                    @Override
                    public void notConnection(final String result) {// اگر اتصال برقرار نشد این متد فراخانی میشود
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // نمایش یک پیغام به کاربر

                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void success(final String result) {// اگر مقدار برگشتی وجود داشت این متد صدا زده میشود
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (result.equals("null")){
                                    // نمایش یک پیغام به کاربر

                                    Toast.makeText(MainActivity.this,"Please enter your city again",Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    Gson gson = new Gson();// ساخت یک نمئنه از کلاس Gson برای تجزیه و تحلیل جیسون دریافتی
                                    Weather api = gson.fromJson(result, Weather.class);  // مدل هایی از جیسون دریافتی را میسازیم


                                    initData(api);// مقدار مدل را به متد تجزیه و تحلیل اطلاعات میفرستیم
                                }
                            }
                        });
                    }

                    @Override
                    public void nullable(String result) {

                    }
                });

    }

    /**
     * اطلاعات وارد شده از سمت سرور را تجزیه و تحلیل میکند
     */
    private void initData(Weather api) {

        List<Forecast> forecasts = api.getResult().getForecasts();// ساخت یک نمونه آداپتر برای لیست اطلاعات آب و هوای چند روز آینده
        NameAdapter adapter=new NameAdapter(MainActivity.this , forecasts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this
                ,LinearLayoutManager.HORIZONTAL,false));
        // تمام اطلاهات دریافتی را از Api ورودی گرفته و در مقادیر های مورد نیاز نگهداری میکنیم

        String cityName=api.getResult().getLocation().getCountry()+ " , " + api.getResult().getLocation().getCity() ;
        tvCity.setText(cityName);
        String date= getDate(api.getResult().getPubDate());
        tvDate.setText(date);
        String txtState=api.getResult().getCondition().getText();
        tvWeatherState.setText(txtState);
        String temp=String.valueOf(api.getResult().getCondition().getTemperature() + " °C");
        tvTemperature.setText(temp);
        weatherState.setImageDrawable(getResources().getDrawable(
                WeatherPhoto.getPhotoWeather(api.getResult().getCondition().getText())
        ));

    }

    /**
     * وظیفه تبدیل تاریخ از unixTime به قابل نمایش برای کاربر
     */
    private String getDate(long time){
        Date date = new Date(time * 1000L);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+03:30"));
        return simpleDateFormat.format(date);
    }


}
