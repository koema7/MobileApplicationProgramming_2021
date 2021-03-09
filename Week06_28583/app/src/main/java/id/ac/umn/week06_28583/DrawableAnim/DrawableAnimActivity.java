package id.ac.umn.week06_28583.DrawableAnim;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.umn.week06_28583.R;

public class DrawableAnimActivity extends AppCompatActivity {
    AnimationDrawable animasiKuda;
    ImageView gambarKuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_anim);
        gambarKuda = (ImageView) findViewById(R.id.gambarKuda);
        gambarKuda.setBackgroundResource(R.drawable.kuda_lari);
        animasiKuda = (AnimationDrawable) gambarKuda.getBackground();
        gambarKuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animasiKuda.start();
            }
        });
    }
}