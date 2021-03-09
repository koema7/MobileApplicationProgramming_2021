package id.ac.umn.week06_28583;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.umn.week06_28583.DrawableAnim.DrawableAnimActivity;
import id.ac.umn.week06_28583.PhysicAnim.PyshicAnimActivity;
import id.ac.umn.week06_28583.PropAnimasi.PropAnimasiActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnPropAnim, btnDrawAnim, btnPhysicAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPropAnim = findViewById(R.id.buttonPropAnim);
        btnDrawAnim = findViewById(R.id.buttonDrawAnim);
        btnPhysicAnim = findViewById(R.id.buttonPhysicAnim);

        btnPropAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PropAnimasiActivity.class));
            }
        });
        btnDrawAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawableAnimActivity.class));
            }
        });
        btnPhysicAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PyshicAnimActivity.class));
            }
        });
    }

}