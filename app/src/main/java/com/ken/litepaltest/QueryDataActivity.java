package com.ken.litepaltest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ken.litepaltest.adapter.HumanAdapter;
import com.ken.litepaltest.javabean.Human;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class QueryDataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private List<Human> humanList = new ArrayList<>();
    HumanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_data);
        initData();
        initWidget();

    }
    void initWidget(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(refreshListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HumanAdapter(humanList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    void initData(){
        List<Human> humans = DataSupport.findAll(Human.class);
        for (Human human :humans){
            Human data = new Human(human.getName(),human.getSex(),human.getAge());
            humanList.add(data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        humanList.clear();
        initData();
        initWidget();
    }
    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
           onResume();
            refreshLayout.setRefreshing(false);
        }
    };
}
