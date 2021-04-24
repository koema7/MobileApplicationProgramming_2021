package id.ac.umn.week11_28583.posts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostHelper {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();
}
