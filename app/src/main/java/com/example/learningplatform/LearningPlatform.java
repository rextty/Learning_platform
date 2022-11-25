package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.learningplatform.databinding.ActivityLearningplatformBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class LearningPlatform extends AppCompatActivity {

    private MyExpandableListAdapter myExpandableListAdapter;

    //父層總陣列
    private HashMap<String, ArrayList> mainArray = new HashMap<>();
    //父層標題
    private ArrayList<String> itemName = new ArrayList<>();
    //子層陣列
    private ArrayList<HashMap<String,String>> childArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLearningplatformBinding binding = ActivityLearningplatformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setData();

        //可以先不加入，這是去子層底線用的
        myExpandableListAdapter = new MyExpandableListAdapter();
//        binding.listView.setAdapter(myExpandableListAdapter);
    }

    private void setData() {
        for (int i = 0;i<6;i++){//子項目的內容
            HashMap<String,String> childName = new HashMap<>();//子層內容
            childName.put("Child1","項目"+i);
            childName.put("Child2","內容"+i);
            childArray.add(childName);
        }

        for (int i=0;i<4;i++){//父項目的內容
            itemName.add(i,"標題"+i);

            mainArray.put(itemName.get(i),childArray);
        }

        Log.d(TAG, "setData: "+mainArray);

    }

    public void ChooseChapter(View view) {
        Intent intent = new Intent(this, ChooseChapter.class);
        startActivity(intent);
    }

    private class MyExpandableListAdapter extends BaseExpandableListAdapter{
        @Override
        public int getGroupCount() {//父陣列長度
            return mainArray.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {//子陣列長度
            return childArray.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded , View convertView, ViewGroup parent) {
            //設置父項目的View
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) LearningPlatform.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.expandlistview_item, null);
            }
            convertView.setTag(R.layout.expandlistview_item,groupPosition);
            convertView.setTag(R.layout.expandlistview_item,-1);
            TextView textView = convertView.findViewById(R.id.textView_ItemTitle);
            textView.setText(itemName.get(groupPosition));

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null){//設置子項目的View
                LayoutInflater inflater = (LayoutInflater) LearningPlatform.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.expandlistview_child,null);
            }
            convertView.setTag(R.layout.expandlistview_child,groupPosition);
            convertView.setTag(R.layout.expandlistview_child,-1);
            TextView child1 = convertView.findViewById(R.id.textView_child1);
            TextView child2 = convertView.findViewById(R.id.textView_child2);
            child1.setText(childArray.get(childPosition).get("Child1"));
            child2.setText(childArray.get(childPosition).get("Child2"));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {//設置子項目是否可點擊
            return true;
        }
    }
}