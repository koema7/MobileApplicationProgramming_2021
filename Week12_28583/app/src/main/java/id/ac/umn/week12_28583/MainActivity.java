package id.ac.umn.week12_28583;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.umn.week12_28583.SensorDataReading.SensorDataReadingActivity;
import id.ac.umn.week12_28583.SensorDetector.SensorDetectorActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnsd, btnsdr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnsd = findViewById(R.id.buttonSensorDetector);
        btnsdr = findViewById(R.id.buttonSensorDataRead);
        btnsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SensorDetectorActivity.class));
            }
        });
        btnsdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SensorDataReadingActivity.class));
            }
        });
    }

}