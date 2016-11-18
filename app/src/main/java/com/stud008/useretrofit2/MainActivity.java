package com.stud008.useretrofit2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        String[] data = {"test1","test2","test3"};
        int layoutID = android.R.layout.simple_list_item_1;//adroid內建簡單的layout!!!!!!!!!!!
        final  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,layoutID);
        ListView item_list = (ListView) findViewById(R.id.item_list);
        item_list.setAdapter(adapter);

        //當item被點擊時
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //position代表你點擊的是哪一個
//                Toast.makeText(MainActivity.this,"你點擊的是第"+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,DeleteActivity.class);//拿來丟東西,可以連結頁面
//                intent.putExtra("cName",adapter.getItem(position));  //前面是key後面是value
                intent.putExtra("postion",position);
//                startActivity(intent);
                startActivityForResult(intent,0);//因為有兩個按鈕

            }
        };
        item_list.setOnItemClickListener(onItemClickListener);

        Retrofit retrofit = new Retrofit.Builder()   //利用內建的建立Bulider
                .baseUrl("http://192.168.58.22:8081/")  //呼叫對應網址
                .addConverterFactory(GsonConverterFactory.create()) //把json轉換 ,再放入
                .build(); //呼叫API

        GitHubService service = retrofit.create(GitHubService.class);//產生可以實作的介面   做好後放入service

        Call<List<Repo>> repos = service.listRepos();  //若要用listRepos  先產生Call,以便後面可以呼叫
        //<此為泛型> 因為用到很多所以用Call<泛型A>,泛型A之中還有<泛型B>,彈性便很大             //當repos呼叫網路
        //非同步呼叫
        repos.enqueue(new Callback<List<Repo>>() {
            @Override       //呼叫網路後傳回來
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Myapp myapp = (Myapp)getApplicationContext(); //會把Myapp class抓近來
                myapp.result =response.body();

//                System.out.println(result.get(0).name); //列出第0個
//                System.out.println(result.get(1).name);

                Iterator it = myapp.result.iterator();//疊代器   因為改用myapp的變數,所以前面要改
//                while(it.hasNext()) {  //用來檢查疊代器 有沒有東西
//                    System.out.println(((Repo) it.next()).cName); //利用cast,在類別裡面可以用cast轉化型別
//            }
            while (it.hasNext()){
                adapter.add(((Repo)it.next()).cName);

            }

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
            //這邊是失敗時呼叫
                t.printStackTrace(); //列出錯誤
            }
        }); //enqueue 序列,排列,會在背景執行,程式不會被網路拖住

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("onActivityResult");

        if(resultCode == Activity.RESULT_CANCELED){
            System.out.println("Cancel");
        }

        if(resultCode == Activity.RESULT_OK){
            System.out.println("Delete");
        }

    }
}
//interface GitHubService {
////建立介面
//    @GET("11-0/11-14_project/api/read_data.php")  //GET 跟http協定有關
//    Call<List<Repo>> listRepos();  //listRepos 當成JAVA的方法來使用,user要同上一行user 因為代表參數
//    //建立Repo的list
//}

//class  Repo{
//    int id,cID ;
//    String cName,full_name,cSex;
//}