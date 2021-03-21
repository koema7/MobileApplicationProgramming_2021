package id.ac.umn.projectuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.umn.projectuts.MusicPlayer.MusicPlayerActivity;
import id.ac.umn.projectuts.MusicPlayer.MusicPlayerListActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.buttonLogin);
        btnProfile = findViewById(R.id.buttonProfile);

        setResult(107);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, Login_Activity.class), 12);
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==123 && requestCode==12){
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}