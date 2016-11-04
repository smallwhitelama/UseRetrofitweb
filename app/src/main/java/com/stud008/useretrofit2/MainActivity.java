package com.stud008.useretrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()   //利用內建的建立Bulider
                .baseUrl("https://api.github.com/")  //呼叫對應網址
                .addConverterFactory(GsonConverterFactory.create()) //把json轉換 ,再放入
                .build(); //呼叫API

        GitHubService service = retrofit.create(GitHubService.class);//產生可以實作的介面   做好後放入service

        Call<List<Repo>> repos = service.listRepos("octocat");  //若要用listRepos  先產生Call,以便後面可以呼叫
                        //當repos呼叫網路
        //非同步呼叫
        repos.enqueue(new Callback<List<Repo>>() {
            @Override       //呼叫網路後傳回來
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> result = response.body();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
            //這邊是失敗處
                t.printStackTrace(); //列出錯誤
            }
        }); //enqueue 序列,排列,會在背景執行,程式不會被網路拖住
    }
}
interface GitHubService {
//建立介面
    @GET("users/{user}/repos")  //GET 跟http協定有關
    Call<List<Repo>> listRepos(@Path("user") String user);  //listRepos 當成JAVA的方法來使用,user要同上一行user 因為代表參數
    //建立Repo的list
}

class  Repo{
    int id ;
    String name,full_name;


}