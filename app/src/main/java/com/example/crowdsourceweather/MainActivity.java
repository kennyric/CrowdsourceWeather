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

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ImageView weatherImage;
    ImageButton button;
    Button button2, button3, button4;
    TextView location, temperature, weather, coordinate, humidity, sunrise, sunset, feelslike, cloud, wind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.enterCity);
        button = findViewById(R.id.searchButton);
        button2 = findViewById(R.id.reportWeather);
        button4 = findViewById(R.id.checkHour);
        location = findViewById(R.id.location2);
        temperature = findViewById(R.id.temperature);
        weather = findViewById(R.id.weather);
        weatherImage = findViewById(R.id.weatherImage);
        coordinate = findViewById(R.id.time1Data);
        humidity = findViewById(R.id.time2Data);
        sunrise = findViewById(R.id.time4Data);
        sunset = findViewById(R.id.time5Data);
        feelslike = findViewById(R.id.feels);
        cloud = findViewById(R.id.time6Data);
        wind = findViewById(R.id.time3Data);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            findWeatherData();
            }
        }

        );

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubmitWeather();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHourlyWeather();
            }
        });

//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openWeatherForecast();
//            }
//        });

    }

    public void openSubmitWeather() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    public void openHourlyWeather(){
        Intent intent3 = new Intent(this, MainActivity3.class);
        startActivity(intent3);

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
                                String city = jsonObject.getString("name");
                                location.setText("Location: " + city + ", " + country);

                                //icon
                                JSONArray jsonArray = jsonObject.getJSONArray("weather");
                                JSONObject object1 = jsonArray.getJSONObject(0);
                                String icon = object1.getString("icon");
                                Picasso.get().load("http://openweathermap.org/img/wn/"+icon+"@2x.png").into(weatherImage);
                                weatherImage.setVisibility(View.VISIBLE);

                                //weather
                                JSONObject object2 = jsonArray.getJSONObject(0);
                                String weatherResult = object2.getString("main");
                                String weatherResult2 = object2.getString("description");
                                weather.setText("Weather: " + weatherResult +", chances of " + weatherResult2);

                                //coordinate
                                JSONObject object3 = jsonObject.getJSONObject("coord");
                                double latitude = object3.getDouble("lat");
                                double longitude = object3.getDouble("lon");
                                coordinate.setText(latitude + ", " + longitude);

                                //find humidity
                                JSONObject object4 = jsonObject.getJSONObject("main");
                                int humidity_finder = object4.getInt("humidity");
                                humidity.setText(humidity_finder + "  %");

                                //find sunrise sunset
                                JSONObject object5 = jsonObject.getJSONObject("sys");
                                long sunrise_finder2 = object5.getLong("sunrise");
                                long sunset_finder2 = object5.getLong("sunset");
                                String sunrise_finder = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (sunrise_finder2*1000));
                                String sunset_finder = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (sunset_finder2*1000));
                                sunrise.setText(sunrise_finder.substring(12) + " AM");
                                sunset.setText(sunset_finder.substring(12) + " PM");

                                //wind speed
                                JSONObject object6 = jsonObject.getJSONObject("wind");
                                long wind_finder3 = object6.getLong("speed");
                                long wind_finder2 = (long) (wind_finder3 * 3.6);
                                wind.setText(wind_finder2 + "km/h");

                                //feels
                                JSONObject object9 = jsonObject.getJSONObject("main");
                                double feels_finder2 = object9.getDouble("feels_like");
                                double feels_finder = Math.round(feels_finder2 - 273.15);
                                feelslike.setText("Feels like: " + feels_finder+"\u2103");

                                //cloud
                                JSONObject object7 = jsonObject.getJSONObject("clouds");
                                int cloud_finder = object7.getInt("all");
                                cloud.setText(cloud_finder + "%");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);

        }

        public void findWeatherHourly(){
        Intent intent2 = new Intent(this, MainActivity3.class);
        startActivity(intent2);
        }
//            final String enterCityRaw = editText.getText().toString();
//            String enterCity = enterCityRaw.replace(" ", "+");
//            String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + enterCity + "&appid=d3ae12991f8cf1c408f29231fd2a4562";
//            StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        //temperature
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONObject object = jsonObject.getJSONObject("main");
//                        double temp1 = object.getDouble("temp");
//                        double temp = Math.round(temp1 - 273.15);
//                        temperature.setText("Temperature: " + temp + "\u2103");
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//            requestQueue.add(stringRequest2);
//        }
}