package com.example.week4day3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.week4day3.model.user.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvLogin;
    TextView tvFollowers;
    TextView tvPublicRepos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvLogin = findViewById(R.id.tvLogIn);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvPublicRepos = findViewById(R.id.tvPublicRepos);

        RetrofitHelper retrofitHelper = new RetrofitHelper();
        //Start Retrofit in a async way to get our pojo response
        retrofitHelper.getRandomUsers("1").enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String login = response.body().getLogin();
                Integer followers = response.body().getFollowers();
                Integer publicRepos = response.body().getPublicRepos();
                tvLogin.setText(login);
                tvFollowers.setText("Num of Followers: "+followers);
                tvPublicRepos.setText("Num of repos: "+publicRepos);

                Log.d("TAG_RETROFIT", "onResponse: " + login);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }



}
