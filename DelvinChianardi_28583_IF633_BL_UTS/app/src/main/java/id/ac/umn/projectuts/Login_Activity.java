package id.ac.umn.projectuts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.umn.projectuts.MusicPlayer.MusicPlayerListActivity;

public class Login_Activity extends AppCompatActivity {
    private UserLogin user;
    Context context = this;
    EditText editText_username, editText_password;
    Button button_login;
    ActionBar actionBar;
    String id = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        user = UserLogin.getInstance();

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(user.getUserLogin()){
            startActivity(new Intent(Login_Activity.this, MusicPlayerListActivity.class));
        }
        Login();
    }

    void Login() {
        editText_username = (EditText) findViewById(R.id.et_username);
        editText_password = (EditText) findViewById(R.id.et_password);
        button_login = (Button) findViewById(R.id.btn_login);

        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                id = editText_username.getText().toString();
                password = editText_password.getText().toString();

                Log.d("id", "My ID: " + id);
                Log.d("id", "My Pass: " + password);
                user = UserLogin.getInstance();

                if(id.equals("uasmobile") && password.equals("uasmobilegenap")){
                    user.userLogin();
                    startActivity(new Intent(Login_Activity.this, MusicPlayerListActivity.class));
                    setResult(123);
                    finish();
                }
                else{
                    Toast.makeText(context, "Your Credential doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
//        if(!userLogin.getUserLogin()) {
        super.onBackPressed();
//        }
    }

}