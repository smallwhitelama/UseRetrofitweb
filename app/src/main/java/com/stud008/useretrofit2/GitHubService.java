package com.stud008.useretrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yvtc on 2016/11/18.
 */

public interface GitHubService {
//  @get("網址")
    @GET("11-0/11-14_project/api/read_data.php")  //GET 跟http協定有關
    Call<List<Repo>> listRepos();  //listRepos 當成JAVA的方法來使用,user要同上一行user 因為代表參數
    //建立Repo的list
}
