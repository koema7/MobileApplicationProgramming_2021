package id.ac.umn.week10_28583;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.umn.week10_28583.AsyncTask.AsyncTaskActivity;
import id.ac.umn.week10_28583.AsyncTaskLoader.AsyncTaskLoaderActivity;
import id.ac.umn.week10_28583.IntentService.IntentServiceActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnat, btnatl, btnis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnat = findViewById(R.id.buttonAsyncTask);
        btnatl = findViewById(R.id.buttonAsyncTaskLoader);
        btnis = findViewById(R.id.buttonIntentService);
        btnat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
            }
        });
        btnatl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncTaskLoaderActivity.class));
            }
        });
        btnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentServiceActivity.class));
            }
        });
    }
}