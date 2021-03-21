package id.ac.umn.projectuts.MusicPlayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import id.ac.umn.projectuts.Login_Activity;
import id.ac.umn.projectuts.MainActivity;
import id.ac.umn.projectuts.ProfileActivity;
import id.ac.umn.projectuts.R;
import id.ac.umn.projectuts.UserLogin;

public class MusicPlayerActivity extends AppCompatActivity {
    private UserLogin user;
    Button buttonPlay;
    SeekBar seekBarPosition, volumeBar;
    TextView elapsedTimeView, remainingTimeView, tvJudul;
    MediaPlayer mediaPlayer;
    ArrayList<MusicListExample> sm;
    ActionBar actionBar;
    int posisi;
    int totalTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonPlay = findViewById(R.id.btnPlay);
        elapsedTimeView = findViewById(R.id.elapsedTimeView);
        remainingTimeView = findViewById(R.id.remainingTimeView);
        tvJudul = findViewById(R.id.tvJudul);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sm = (ArrayList) bundle.getSerializable("DetilMusik");
        posisi = (Integer) bundle.getSerializable("Posisi");
        tvJudul.setText(sm.get(posisi).getJudul());
        if(posisi == sm.size()-1){
            Log.d("id", "Posisi : " + posisi);
            Log.d("id", "Size : " + sm.size());
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(sm.get(posisi).getMusicURI()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5f, 0.5f);
        totalTime = mediaPlayer.getDuration();

        seekBarPosition = findViewById(R.id.seekBarPosition);
        seekBarPosition.setMax(totalTime);
        seekBarPosition.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean user) {
                if(user){
                    mediaPlayer.seekTo(progress);
                    seekBarPosition.setProgress(progress);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        volumeBar = findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean user) {
                float numVolume = progress / 100f;
                mediaPlayer.setVolume(numVolume, numVolume);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mediaPlayer != null){
                    try{
                        Message msg = new Message();
                        msg.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);

                    }catch (InterruptedException e){}
                }
            }
        }).start();
    }
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            int currentPosition = msg.what;
            // Update SeekBar Position
            seekBarPosition.setProgress(currentPosition);

            // Update TimeView
            String elapsedTime = createTimeView(currentPosition);
            elapsedTimeView.setText(elapsedTime);

            String remainingTime = createTimeView(totalTime-currentPosition);
            remainingTimeView.setText("- " + remainingTime);
        }
    };

    public String createTimeView(int time){

        String timeView = "";
        int min =  time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeView = min + ":";
        if(sec < 10) timeView += "0";
        timeView += sec;

        return  timeView;
    }

    public void btnPlayAction(View view){

        if(!mediaPlayer.isPlaying()){
            //Stop Music
            mediaPlayer.start();
            buttonPlay.setBackgroundResource(R.drawable.ic_baseline_pause_24);
        }else{
            // Play Music
            mediaPlayer.pause();
            buttonPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);

        }
    }
    public void btnPrevAction(View view){
        Log.d("id", "Posisi berkurang " + (posisi - 1));
        Bundle bundle = new Bundle();
        Intent detilInten = new Intent(this, MusicPlayerActivity.class);
        if(mediaPlayer.isPlaying()){
            //Stop Music
            mediaPlayer.stop();
        }
        if(posisi == 0 ){
            bundle.putSerializable("DetilMusik", sm);
            bundle.putSerializable("Posisi", sm.size()-1);
            detilInten.putExtras(bundle);
            this.startActivity(detilInten);
            finish();
        } else {
            bundle.putSerializable("DetilMusik", sm);
            bundle.putSerializable("Posisi", posisi-1);
            detilInten.putExtras(bundle);
            this.startActivity(detilInten);
            finish();
        }
    }
    public void btnNextAction(View view){
        Log.d("id", "Posisi bertambah " + (posisi + 1));
        Bundle bundle = new Bundle();
        Intent detilInten = new Intent(this, MusicPlayerActivity.class);
        if(mediaPlayer.isPlaying()){
            //Stop Music
            mediaPlayer.stop();
        }
        if(posisi == sm.size()-1){
            bundle.putSerializable("DetilMusik", sm);
            bundle.putSerializable("Posisi", 0);
            detilInten.putExtras(bundle);
            this.startActivity(detilInten);
            finish();
        } else {
            bundle.putSerializable("DetilMusik", sm);
            bundle.putSerializable("Posisi", posisi+1);
            detilInten.putExtras(bundle);
            this.startActivity(detilInten);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profil:
                Profiles();
                return true;
            case R.id.logout:
                user = UserLogin.getInstance();
                user.userLogOff();
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void Profiles() {
        mediaPlayer.stop();
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent) ;
    }
    private void logOut() {
        mediaPlayer.stop();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent) ;
        finish();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
//        if(!userLogin.getUserLogin()) {
        mediaPlayer.stop();
        super.onBackPressed();
//        }
    }
}