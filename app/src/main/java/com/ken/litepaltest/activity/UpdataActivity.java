package com.ken.litepaltest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ken.litepaltest.R;
import com.ken.litepaltest.javabean.Human;

public class UpdataActivity extends AppCompatActivity implements OnClickListener{
    EditText editText,editText1;
    Spinner spinner;
    Button button;
    String name,sex,age;
    String upName,upSex,upAge;

    public String getUpSex() {
        return upSex;
    }

    public void setUpSex(String upSex) {
        this.upSex = upSex;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        name = (String)getIntent().getSerializableExtra("name");
        sex = (String)getIntent().getSerializableExtra("sex");
        age = (String)getIntent().getSerializableExtra("age");
        initWidget();
        initData();
    }

    void initWidget(){
        editText = (EditText)findViewById(R.id.et_name);
        editText1 = (EditText)findViewById(R.id.et_age);
        spinner = (Spinner)findViewById(R.id.sp_sex);
        button = (Button)findViewById(R.id.btn_updata);
        button.setOnClickListener(this);
    }

    void initData(){
        editText.setText(name);
        editText1.setText(age);
        if ("男".equals(sex)){
            spinner.setSelection(0);
        }else if("女".equals(sex)){
            spinner.setSelection(1);
        }else{
            spinner.setSelection(2);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                upSex = (String) spinner.getSelectedItem();
                setUpSex(upSex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_updata:
                upName = editText.getText().toString();
                upAge = editText1.getText().toString();
                Human human = new Human();
                Log.e("UpdataActivity----sex", getUpSex());
                human.setName(upName);
                human.setSex(getUpSex());
                human.setAge(Integer.parseInt(upAge));
                human.updateAll("name = ? and age = ? and sex = ?",name,age,sex);
                Intent intent  = new Intent(this,QueryDataActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
