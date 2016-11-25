package com.stud008.useretrofit2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }





    public void  cancel(View view){

        Intent result = getIntent();
        setResult(Activity.RESULT_CANCELED,result);
        finish(); //結束頁面,會跳回 onActivityResult

    }
    public void  add(View view){
        Myapp myApp = (Myapp)getApplicationContext();
        Repo repo=new Repo();
        repo.cName="mike";
        repo.cAddr="Earth";
        repo.cBirthday="1999/08/07";
        repo.cEmail="asve@gmail.com";
        repo.cPhone="09032165106";
        repo.cSex="male";
        myApp.addByFormPost = myApp.service.addByFormPost(repo.cName,repo.cSex,repo.cBirthday,repo.cEmail,repo.cPhone,repo.cAddr);
        myApp.addByFormPost.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("Add by form post OK!");

//
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Add  by form post fail......");
            }
        });



        Intent result = getIntent();
        //result.putExtra("postion",postion);
        setResult(Activity.RESULT_OK,result);
        finish();

    }
}
