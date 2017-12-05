package com.ken.litepaltest.adapter;

import android.content.DialogInterface;
import android.icu.text.AlphabeticIndex;
import android.os.Message;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ken.litepaltest.R;
import com.ken.litepaltest.javabean.Human;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author  by Ken on 2017/12/5.
 */

public class HumanAdapter extends RecyclerView.Adapter<HumanAdapter.ViewHolder> {
    private List<Human> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        TextView tv1,tv2,tv3;
        public ViewHolder(View view){
            super(view);
           this.itemView = view;
            tv1= (TextView)view.findViewById(R.id.tv_name);
            tv2 = (TextView)view.findViewById(R.id.tv_sex);
            tv3 = (TextView)view.findViewById(R.id.tv_age);

        }
    }
    public HumanAdapter(List<Human> orderList){
        mList = orderList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_human,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Human human = mList.get(position);
        holder.tv1.setText("姓名    |    "+human.getName());
        holder.tv2.setText("性别    |    "+human.getSex());
        holder.tv3.setText("年龄    |    "+human.getAge());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("更改这条信息？");
                dialog.setMessage("姓名----"+human.getName());
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("删除这条信息？");
                dialog.setMessage("姓名----"+human.getName());
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataSupport.deleteAll(Human.class,"name = ? and sex = ? and age = ?",human.getName(),human.getSex(), String.valueOf(human.getAge()));
//                        DataSupport.delete(Human.class,position+1);
                        Log.e("HumanAdapter", "----" + human.getId());
                        Toast.makeText(view.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
