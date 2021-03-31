package com.example.crowdsourceweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    EditText editText;
    ImageView weatherImage, iconTemp1, iconTemp2, iconTemp3, iconTemp4, iconTemp5, iconTemp6, iconTemp7, iconTemp8,
            iconHum1, iconHum2, iconHum3, iconHum4, iconHum5, iconHum6, iconHum7, iconHum8;
    ImageButton search;
    Button button2, button3, mainPage, reportWeather5;
    TextView location, temperature, weather, feelslike, time1, time2, time3, time4, time5, time6, time7, time8,
            temperature1, temperature2, temperature3, temperature4, temperature5, temperature6, temperature7, temperature8,
            humid1, humid2, humid3, humid4, humid5, humid6, humid7, humid8,
            weatherText1, weatherText2, weatherText3, weatherText4, weatherText5, weatherText6, weatherText7, weatherText8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        search = findViewById(R.id.searchButton3);
        editText = findViewById(R.id.enterCity3);
        location = findViewById(R.id.location2);
        button2 = findViewById(R.id.goMainPage);
        button3 = findViewById(R.id.reportWeather4);
        weatherImage = findViewById(R.id.weatherImage);
        temperature = findViewById(R.id.temperature);
        weather = findViewById(R.id.weather);
        feelslike = findViewById(R.id.feels);
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);
        time5 = findViewById(R.id.time5);
        time6 = findViewById(R.id.time6);
        time7 = findViewById(R.id.time7);
        time8 = findViewById(R.id.time8);
        temperature1 = findViewById(R.id.temp1);
        temperature2 = findViewById(R.id.temp2);
        temperature3 = findViewById(R.id.temp3);
        temperature4 = findViewById(R.id.temp4);
        temperature5 = findViewById(R.id.temp5);
        temperature6 = findViewById(R.id.temp6);
        temperature7 = findViewById(R.id.temp7);
        temperature8 = findViewById(R.id.temp8);
        humid1 = findViewById(R.id.humidity1);
        humid2 = findViewById(R.id.humidity2);
        humid3 = findViewById(R.id.humidity3);
        humid4 = findViewById(R.id.humidity4);
        humid5 = findViewById(R.id.humidity5);
        humid6 = findViewById(R.id.humidity6);
        humid7 = findViewById(R.id.humidity7);
        humid8 = findViewById(R.id.humidity8);
        weatherText1 = findViewById(R.id.weather1);
        weatherText2 = findViewById(R.id.weather2);
        weatherText3 = findViewById(R.id.weather3);
        weatherText4 = findViewById(R.id.weather4);
        weatherText5 = findViewById(R.id.weather5);
        weatherText6 = findViewById(R.id.weather6);
        weatherText7 = findViewById(R.id.weather7);
        weatherText8 = findViewById(R.id.weather8);
        mainPage = findViewById(R.id.goMainPage);
        reportWeather5 = findViewById(R.id.reportWeather4);
        iconTemp1 = findViewById(R.id.tempIcon1);
        iconTemp2 = findViewById(R.id.tempIcon2);
        iconTemp3 = findViewById(R.id.tempIcon3);
        iconTemp4 = findViewById(R.id.tempIcon4);
        iconTemp5 = findViewById(R.id.tempIcon5);
        iconTemp6 = findViewById(R.id.tempIcon6);
        iconTemp7 = findViewById(R.id.tempIcon7);
        iconTemp8 = findViewById(R.id.tempIcon8);
        iconHum1 = findViewById(R.id.humidityIcon1);
        iconHum2 = findViewById(R.id.humidityIcon2);
        iconHum3 = findViewById(R.id.humidityIcon3);
        iconHum4 = findViewById(R.id.humidityIcon4);
        iconHum5 = findViewById(R.id.humidityIcon5);
        iconHum6 = findViewById(R.id.humidityIcon6);
        iconHum7 = findViewById(R.id.humidityIcon7);
        iconHum8 = findViewById(R.id.humidityIcon8);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findWeatherData();
                findWeatherHour();
            }
        });

        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainWeather();
            }
        });

        reportWeather5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubmitWeather();
            }
        });

    }

    public void openMainWeather(){
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }

    public void openSubmitWeather() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void findWeatherData(){
        final String enterCityRaw = editText.getText().toString();
        String enterCity = enterCityRaw.replace(" ", "+");
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + enterCity + "&appid=d3ae12991f8cf1c408f29231fd2a4562";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    //temperature
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject object = jsonObject.getJSONObject("main");
                    double temp1 = object.getDouble("temp");
                    double temp = Math.round(temp1 - 273.15);
                    temperature.setText("Temperature: "+temp+"\u2103");

                    //location
                    JSONObject object8 = jsonObject.getJSONObject("sys");
                    String country = object8.getString("country");
                    country = country.replace("MY", "Malaysia");
                    country = country.replace("ID", "Indonesia");
                    country = country.replace("SG", "Singapore");
                    country = country.replace("TH", "Thailand");
                    country = country.replace("BN", "Brunei");
                    country = country.replace("MM", "Myanmar");
                    country = country.replace("VN", "Vietnam");
                    country = country.replace("TT", "Phillipines");
                    country = country.replace("LA", "Laos");
                    country = country.replace("KH", "Cambodia");
                    String city = jsonObject.getString("name");
                    location.setText("Location: " + city + ", " + country);

                    //icon
                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject object1 = jsonArray.getJSONObject(0);
                    String icon = object1.getString("icon");
                    Picasso.get().load("http://openweathermap.org/img/wn/"+icon+"@2x.png").into(weatherImage);
                    weatherImage.setVisibility(View.VISIBLE);;

                    //weather
                    JSONObject object2 = jsonArray.getJSONObject(0);
                    String weatherResult = object2.getString("main");
                    String weatherResult2 = object2.getString("description");
                    weather.setText("Weather: " + weatherResult +", chances of " + weatherResult2);

                    //feels
                    JSONObject object9 = jsonObject.getJSONObject("main");
                    double feels_finder2 = object9.getDouble("feels_like");
                    double feels_finder = Math.round(feels_finder2 - 273.15);
                    feelslike.setText("Feels like: " + feels_finder+"\u2103");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity3.this);
        requestQueue.add(stringRequest);

    }

    public void findWeatherHour(){
        final String enterCityRaw = editText.getText().toString();
        String enterCity = enterCityRaw.replace(" ", "+");
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + enterCity + "&appid=56ab896b8d25168d2902ad867bf00167";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    //temperature1
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray2 = jsonObject.getJSONArray("list");
                    JSONObject object = jsonArray2.getJSONObject(0);
                    JSONObject object3 = object.getJSONObject("main");
                    double rawtemp1 = object3.getDouble("temp");
                    double temp1 = Math.round(rawtemp1 - 273.15);
                    String temp11 = temp1 + "\u2103";
                    temperature1.setText(temp11);
                    iconTemp1.setVisibility(View.VISIBLE);

                    //temperature2
                    JSONArray jsonArray3 = jsonObject.getJSONArray("list");
                    JSONObject object4 = jsonArray3.getJSONObject(1);
                    JSONObject object5 = object4.getJSONObject("main");
                    double rawtemp2 = object5.getDouble("temp");
                    double temp2 = Math.round(rawtemp2 - 273.15);
                    String temp22 = temp2 + "\u2103";
                    temperature2.setText(temp22);
                    iconTemp2.setVisibility(View.VISIBLE);

                    //temperature3
                    JSONObject object6 = jsonArray3.getJSONObject(2);
                    JSONObject object7 = object6.getJSONObject("main");
                    double rawtemp3 = object7.getDouble("temp");
                    double temp3 = Math.round(rawtemp3 - 273.15);
                    String temp33 = temp3 + "\u2103";
                    temperature3.setText(temp33);
                    iconTemp3.setVisibility(View.VISIBLE);

                    //temperature4
                    JSONObject object8 = jsonArray3.getJSONObject(3);
                    JSONObject object9 = object8.getJSONObject("main");
                    double rawtemp4 = object9.getDouble("temp");
                    double temp4 = Math.round(rawtemp4 - 273.15);
                    String temp44 = temp4 + "\u2103";
                    temperature4.setText(temp44);
                    iconTemp4.setVisibility(View.VISIBLE);

                    //temperature5
                    JSONObject object10 = jsonArray3.getJSONObject(4);
                    JSONObject object11 = object10.getJSONObject("main");
                    double rawtemp5 = object11.getDouble("temp");
                    double temp5 = Math.round(rawtemp5 - 273.15);
                    String temp55 = temp5 + "\u2103";
                    temperature5.setText(temp55);
                    iconTemp5.setVisibility(View.VISIBLE);

                    //temperature6
                    JSONObject object12 = jsonArray3.getJSONObject(5);
                    JSONObject object13 = object12.getJSONObject("main");
                    double rawtemp6 = object13.getDouble("temp");
                    double temp6 = Math.round(rawtemp6 - 273.15);
                    String temp66 = temp6 + "\u2103";
                    temperature6.setText(temp66);
                    iconTemp6.setVisibility(View.VISIBLE);

                    //temperature7
                    JSONObject object14 = jsonArray3.getJSONObject(6);
                    JSONObject object15 = object14.getJSONObject("main");
                    double rawtemp7 = object15.getDouble("temp");
                    double temp7 = Math.round(rawtemp7 - 273.15);
                    String temp77 = temp7 + "\u2103";
                    temperature7.setText(temp77);
                    iconTemp7.setVisibility(View.VISIBLE);

                    //temperature8
                    JSONObject object16 = jsonArray3.getJSONObject(7);
                    JSONObject object17 = object16.getJSONObject("main");
                    double rawtemp8 = object17.getDouble("temp");
                    double temp8 = Math.round(rawtemp8 - 273.15);
                    String temp88 = temp8 + "\u2103";
                    temperature8.setText(temp88);
                    iconTemp8.setVisibility(View.VISIBLE);

                    //humidity1
                    String humidity1 = object3.getString("humidity");
                    humid1.setText(humidity1);
                    iconHum1.setVisibility(View.VISIBLE);

                    //humidity2
                    String humidity2 = object5.getString("humidity");
                    humid2.setText(humidity2);
                    iconHum2.setVisibility(View.VISIBLE);

                    //humidity3
                    String humidity3 = object7.getString("humidity");
                    humid3.setText(humidity3);
                    iconHum3.setVisibility(View.VISIBLE);

                    //humidity4
                    String humidity4 = object9.getString("humidity");
                    humid4.setText(humidity4);
                    iconHum4.setVisibility(View.VISIBLE);

                    //humidity5
                    String humidity5 = object11.getString("humidity");
                    humid5.setText(humidity5);
                    iconHum5.setVisibility(View.VISIBLE);

                    //humidity6
                    String humidity6 = object13.getString("humidity");
                    humid6.setText(humidity6);
                    iconHum6.setVisibility(View.VISIBLE);

                    //humidity7
                    String humidity7 = object15.getString("humidity");
                    humid7.setText(humidity7);
                    iconHum7.setVisibility(View.VISIBLE);

                    //humidity8
                    String humidity8 = object17.getString("humidity");
                    humid8.setText(humidity8);
                    iconHum8.setVisibility(View.VISIBLE);

                    //weather1
                    JSONObject object18 = jsonArray3.getJSONObject(0);
                    JSONArray jsonArray5 = object18.getJSONArray("weather");
                    JSONObject object19 = jsonArray5.getJSONObject(0);
                    String weather1 = object19.getString("description");
                    weatherText1.setText(weather1);

                    //weather2
                    JSONObject object20 = jsonArray3.getJSONObject(1);
                    JSONArray jsonArray6 = object20.getJSONArray("weather");
                    JSONObject object21 = jsonArray6.getJSONObject(0);
                    String weather2 = object21.getString("description");
                    weatherText2.setText(weather2);

                    //weather3
                    JSONObject object22 = jsonArray3.getJSONObject(2);
                    JSONArray jsonArray7 = object22.getJSONArray("weather");
                    JSONObject object23 = jsonArray7.getJSONObject(0);
                    String weather3 = object23.getString("description");
                    weatherText3.setText(weather3);

                    //weather4
                    JSONObject object24 = jsonArray3.getJSONObject(3);
                    JSONArray jsonArray8 = object24.getJSONArray("weather");
                    JSONObject object25 = jsonArray8.getJSONObject(0);
                    String weather4 = object25.getString("description");
                    weatherText4.setText(weather4);

                    //weather5
                    JSONObject object26 = jsonArray3.getJSONObject(4);
                    JSONArray jsonArray9 = object26.getJSONArray("weather");
                    JSONObject object27 = jsonArray9.getJSONObject(0);
                    String weather5 = object27.getString("description");
                    weatherText5.setText(weather5);

                    //weather6
                    JSONObject object28 = jsonArray3.getJSONObject(5);
                    JSONArray jsonArray10 = object28.getJSONArray("weather");
                    JSONObject object29 = jsonArray10.getJSONObject(0);
                    String weather6 = object29.getString("description");
                    weatherText6.setText(weather6);

                    //weather7
                    JSONObject object30 = jsonArray3.getJSONObject(6);
                    JSONArray jsonArray11 = object30.getJSONArray("weather");
                    JSONObject object31 = jsonArray11.getJSONObject(0);
                    String weather7 = object31.getString("description");
                    weatherText7.setText(weather7);

                    //weather8
                    JSONObject object32 = jsonArray3.getJSONObject(7);
                    JSONArray jsonArray12 = object32.getJSONArray("weather");
                    JSONObject object33 = jsonArray12.getJSONObject(0);
                    String weather8 = object33.getString("description");
                    weatherText8.setText(weather8);

                    //time1
                    long weather_time11 = object18.getLong("dt");
                    String weather_time1 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time11*1000));
                    time1.setText(weather_time1 + "");

                    //time2
                    long weather_time22 = object20.getLong("dt");
                    String weather_time2 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time22*1000));
                    time2.setText(weather_time2 + "");

                    //time3
                    long weather_time33 = object22.getLong("dt");
                    String weather_time3 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time33*1000));
                    time3.setText(weather_time3 + "");

                    //time4
                    long weather_time44 = object24.getLong("dt");
                    String weather_time4 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time44*1000));
                    time4.setText(weather_time4 + "");

                    //time5
                    long weather_time55 = object26.getLong("dt");
                    String weather_time5 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time55*1000));
                    time5.setText(weather_time5 + "");

                    //time6
                    long weather_time66 = object28.getLong("dt");
                    String weather_time6 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time66*1000));
                    time6.setText(weather_time6 + "");

                    //time7
                    long weather_time77 = object30.getLong("dt");
                    String weather_time7 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time77*1000));
                    time7.setText(weather_time7 + "");

                    //time8
                    long weather_time88 = object32.getLong("dt");
                    String weather_time8 = new SimpleDateFormat("dd/MM HH:mm").format(new Date (weather_time88*1000));
                    time8.setText(weather_time8 + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity3.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity3.this);
        requestQueue.add(stringRequest);

    }
}
