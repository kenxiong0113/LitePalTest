package com.ken.litepaltest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ken.litepaltest.R;
import com.ken.litepaltest.javabean.Human;

import org.litepal.tablemanager.Connector;

/**
 * @author by ken
 */
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

    /**
     * 初始化控件
     */
    void initWidget(){
        etName = (EditText)findViewById(R.id.et_name);
        etAge = (EditText)findViewById(R.id.et_age);
        spSex = (Spinner)findViewById(R.id.sp_sex);
        btnAdd = (Button)findViewById(R.id.btn_updata);
        btnQuery = (Button)findViewById(R.id.btn_query);
    }

    /**
     * 设置监听事件
     */
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
            case R.id.btn_updata:
                //获取输入框的数据
                name = etName.getText().toString();
                age = etAge.getText().toString();
                if ("".equals(name)||"".equals(age)){
                    Toast.makeText(this, "基本信息不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        /**
                         * 创建数据库 ，并保存输入框中的数据
                         */
                        Connector.getDatabase();
                        Human human = new Human();
                        human.setName(name);
                        human.setSex(sex);
                        human.setAge(Integer.parseInt(age));
                        human.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        /**
                         * 清空文本输入框
                         */
                        etName.setText(null);
                        etAge.setText(null);
                        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();

                    }
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

    @Override
    protected void onResume() {
        super.onResume();
    }
}
