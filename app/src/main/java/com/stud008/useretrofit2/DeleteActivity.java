package com.stud008.useretrofit2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();
        String cName= (String) intent.getExtras().getSerializable("cName"); //Serializable 是可序列化
        System.out.println(cName);

        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(cName);

        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        TextView textViewSex = (TextView) findViewById(R.id.textViewSex);

        TextView textViewBirthday = (TextView) findViewById(R.id.textViewBirthday);

        TextView textViewEmail = (TextView) findViewById(R.id.textViewEmail);

        TextView textViewPhone = (TextView) findViewById(R.id.textViewPhone);

        TextView textViewAddr = (TextView) findViewById(R.id.textViewAddr);




    }
    public void  cancel(View view){

        Intent result = getIntent();
        setResult(Activity.RESULT_CANCELED,result);
        finish(); //結束頁面,會跳回 onActivityResult

    }
    public void  delete(View view){

        Intent result = getIntent();
        setResult(Activity.RESULT_OK,result);
        finish();

    }

}



