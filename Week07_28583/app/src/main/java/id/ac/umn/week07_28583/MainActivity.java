package id.ac.umn.week07_28583;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.umn.week07_28583.TujuhA.TujuhAActivity;
import id.ac.umn.week07_28583.TujuhB.TujuhBActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn7a, btn7b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn7a = findViewById(R.id.button7a);
        btn7b = findViewById(R.id.button7b);

        btn7a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TujuhAActivity.class));
            }
        });
        btn7b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TujuhBActivity.class));
            }
        });
    }
}