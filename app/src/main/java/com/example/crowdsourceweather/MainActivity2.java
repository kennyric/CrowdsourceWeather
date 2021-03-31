package com.example.crowdsourceweather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.crowdsourceweather.database.DBHandler;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    Button reportWeather, viewWeather, mainPage;
    RadioGroup radioGroup;
    String weather = "";
    EditText editText;
    DBHandler dbHandler;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        reportWeather = findViewById(R.id.reportWeather2);
        viewWeather = findViewById(R.id.viewReport2);
        mainPage = findViewById(R.id.mainPage);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        goReport();


        dbHandler = new DBHandler(this);

        reportWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                editText = findViewById(R.id.locationData);
                String location = editText.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                weather = radioButton.getText().toString();

                String weather_condition = weather;
                String time = date.toString();
                int randNum = (int) (Math.random() * 10000);
                String randNum2 = "" + randNum;
                int id = Integer.parseInt(randNum2);

                boolean status = dbHandler.addUser(location, weather_condition, time);

                if (status) {
                    toastMessage("Inserted Successfully" + " " + location + " " + weather_condition + " " + time);
                } else {
                    toastMessage("Insertion fail " + " " + location + " " + weather_condition + " " + time);
                }


//                dbHandler.addUser(location, weather_condition, time).addOnSuccessListener(OnSuccessListener) {
//                    dbHandler.addUser(location, weather_condition, time);}
//                        boolean isInserted = false;
//                        isInserted = dbHandler.addUser(location, weather_condition, time);
//                        if (isInserted == true) {
//                            toastMessage("Report submitted successfully" + location + weather_condition + time);
//                        }
//                    } else {
//                        toastMessage("error");
//                    }

            }
        });

        viewWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goView();
                viewReport();
            }
        });

        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }


            public void viewReport(){
                Cursor res = dbHandler.getData();
                if(res.getCount()==0){
                    toastMessage("No Entry");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Location: " + res.getString(1)+"\n");
                    buffer.append("Weather: " + res.getString(2)+"\n");
                    buffer.append("Time: " + res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setCancelable(true);
                builder.setTitle("Weather Report");
                builder.setMessage(buffer.toString());
                builder.show();
            }

            public void goHome () {
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
            }

            private void toastMessage (String message){
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
