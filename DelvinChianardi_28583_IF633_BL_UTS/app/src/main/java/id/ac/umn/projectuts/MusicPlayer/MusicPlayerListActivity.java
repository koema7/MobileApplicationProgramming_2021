package id.ac.umn.projectuts.MusicPlayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;

import id.ac.umn.projectuts.Login_Activity;
import id.ac.umn.projectuts.MainActivity;
import id.ac.umn.projectuts.ProfileActivity;
import id.ac.umn.projectuts.R;
import id.ac.umn.projectuts.UserLogin;

public class MusicPlayerListActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST = 1;
    private UserLogin user;
    private RecyclerView rvDaftarMusik;
    private MusicPlayerAdapter mAdapter;
    private LinkedList<MusicListExample> daftarMusik = new LinkedList<>();
    private UserLogin userLogin;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_list);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(ContextCompat.checkSelfPermission(MusicPlayerListActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MusicPlayerListActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(MusicPlayerListActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(MusicPlayerListActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        } else {
            doStuff();
            Log.d("id", "OnCreate");
        }

        userLogin = UserLogin.getInstance();
        if(!userLogin.getUserLogin()){
            startActivity(new Intent(MusicPlayerListActivity.this, Login_Activity.class));
        }
        openDialog();
    }

    public void openDialog(){
        WellcomeDialog wellcomeDialog = new WellcomeDialog();
        wellcomeDialog.show(getSupportFragmentManager(), "Wellcoming Dialog");
    }

    public void isiDaftarMusik(){
//        daftarMusik.add(new MusicListExample("Kenangan Manis - Pamungkas",
//                "android.resource://"+getPackageName()+"/"+R.raw.kenangan_manis));
//        daftarMusik.add(new MusicListExample("To The Bone - Pamungkas",
//                "android.resource://"+getPackageName()+"/"+R.raw.to_the_bone));

        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        if(songCursor != null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songPath = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            Log.d("id", "My Judul angka : " + songTitle);
            do{
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                String currentPath = songCursor.getString(songPath);
                daftarMusik.add(new MusicListExample(currentTitle + " - " + currentArtist, "" + currentPath));
//                Log.d("id", "My Judul : " + currentTitle);
            }while (songCursor.moveToNext());
        }
    }

    public void doStuff(){

        rvDaftarMusik = (RecyclerView) findViewById(R.id.recyclerView);
        isiDaftarMusik();
        mAdapter = new MusicPlayerAdapter(this, daftarMusik);
        rvDaftarMusik.setAdapter(mAdapter);
        rvDaftarMusik.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode) {
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                        Log.d("id", "OnREQ");
                        doStuff();
                    }
                } else {
                    Log.d("id", "OnREQGAGAL");
                    Toast.makeText(this, "No permission granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }
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
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent) ;
    }
    private void logOut() {
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
        if(!userLogin.getUserLogin()) {
            super.onBackPressed();
        }
    }
}