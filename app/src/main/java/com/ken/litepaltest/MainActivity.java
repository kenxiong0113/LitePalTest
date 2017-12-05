package com.ken.litepaltest;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ken.litepaltest.javabean.Human;

import org.litepal.tablemanager.Connector;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etName,etAge;
    private Button btnAdd,btnQuery;
    private Spinner spSex;
    String name = null,sex = null,age = null;
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

    void setOnClick() {
        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        //获取下拉框的数据
        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sex = (String) spSex.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                try {
                    Connector.getDatabase();
                    Human human = new Human();
                    human.setName(name);
                    human.setSex(sex);
                    human.setAge(Integer.parseInt(age));
                    human.save();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_query:
                Intent intent = new Intent(this,QueryDataActivity.class);
                startActivity(intent);
                break;
            default:

                break;

        }
    }
}
