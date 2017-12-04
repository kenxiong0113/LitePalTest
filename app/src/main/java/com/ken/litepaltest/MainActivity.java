package com.ken.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etName,etAge;
    private Button btnAdd,btnQuery;
    private Spinner spSex;
    String name,sex,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setOnClick();
    }

    void initWidget(){
        etName = (EditText)findViewById(R.id.et_name);
        etAge = (EditText)findViewById(R.id.et_age);
        spSex = (Spinner)findViewById(R.id.sp_sex);
        btnAdd = (Button)findViewById(R.id.btn_add);
        btnQuery = (Button)findViewById(R.id.btn_query);
    }

    void setOnClick(){
        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        //获取下拉框的数据
        spSex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sex = (String) spSex.getSelectedItem();

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                //获取输入框的数据
                name = etName.getText().toString();
                age = etAge.getText().toString();

                break;
            case R.id.btn_query:

                break;
            default:

                break;

        }
    }
}
