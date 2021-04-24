package id.ac.umn.week11_28583;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.umn.week11_28583.posts.ApiClient;
import id.ac.umn.week11_28583.posts.PostAdapter;
import id.ac.umn.week11_28583.posts.PostHelper;
import id.ac.umn.week11_28583.posts.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvPostLists;
    PostAdapter adapter;
    PostHelper posthelper;

    ArrayList<PostModel> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPostLists = findViewById(R.id.rvPostLists);
        rvPostLists.setLayoutManager(new LinearLayoutManager(this));

        posthelper = ApiClient.getClient().create(PostHelper.class);

        getData();
    }

    public void getData() {
        Call<ArrayList<PostModel>> postsCallback = posthelper.getPosts();
        postsCallback.enqueue(new Callback<ArrayList<PostModel>>() {

            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                posts = response.body();
                Log.d("onResponse", "success");

                adapter = new PostAdapter(posts);
                rvPostLists.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                Log.d("onFailure", "err");
                Toast.makeText(MainActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
            }
        });
    }
}