package com.example.crowdsourceweather.database;

import android.provider.BaseColumns;

public class User {

    private User() {
    }

    public static final class UserDetails implements BaseColumns{

        public static final String TABLE_NAME = "WeatherReport";
        public static final String COL_ID = "id";
        public static final String COL_LOC = "location";
        public static final String COL_WEATHER = "weather_condition";
        public static final String COL_TIME = "time_date";
    }
}
