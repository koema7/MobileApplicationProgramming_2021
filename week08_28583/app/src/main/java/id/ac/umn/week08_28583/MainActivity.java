package id.ac.umn.week08_28583;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.umn.week08_28583.SavedInstanceandSharedPreference.BActivity;
import id.ac.umn.week08_28583.TextEitorandStorage.AActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn8a, btn8b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn8a = findViewById(R.id.button8a);
        btn8b = findViewById(R.id.button8b);
        btn8a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AActivity.class));
            }
        });
        btn8b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BActivity.class));
            }
        });
    }
}