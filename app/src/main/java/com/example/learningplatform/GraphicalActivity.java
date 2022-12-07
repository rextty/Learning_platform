package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.learningplatform.Model.POJO.Record;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivityGraphicalBinding;
import com.example.learningplatform.databinding.CardViewExamResultBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GraphicalActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private SharedPreferencesHelper preferencesHelper;
    private ActivityGraphicalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGraphicalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        preferencesHelper = new SharedPreferencesHelper(this);

        xtext();
        ytext();

        Legend legend = binding.chart.getLegend();
        legend.setTextSize(16);

        //設定沒資料時顯示訊息的方法
        binding.chart.setNoDataText("Select the button to show chapter grades.");
        binding.chart.setNoDataTextColor(Color.BLUE);

        Set<String> arraySpinner = new HashSet<>();
        Map<String, ArrayList<Integer>> data = new LinkedHashMap<>();
        getRecordsList(records -> {
            ArrayList<Integer> scores = new ArrayList<>();
            String pre_subsection = records.get(0).getSubsectionName();
            for (int i = 0; i < records.size(); i ++) {
                Record record = records.get(i);
                String cur_subsection = record.getSubsectionName();

                arraySpinner.add(record.getSubsectionName());
                if (!pre_subsection.equals(cur_subsection) || i == records.size() - 1) {
                    data.put(pre_subsection, scores);
                    scores = new ArrayList<>();
                    pre_subsection = cur_subsection;
                }

                scores.add(record.getStudent_score());
            }

            // TODO: split different student result
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(arraySpinner));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner.setAdapter(adapter);
            binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ArrayList<Entry> points = new ArrayList<>();
                    String selected = parent.getSelectedItem().toString();
                    ArrayList<Integer> scores = data.get(selected);
                    for (int i = 0; i < scores.size(); i++) {
                        points.add(new Entry(i, scores.get(i)));
                    }
                    text_all(points);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });
    }

    //設定x軸
    private void xtext() {
        XAxis xAxis = binding.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setDrawGridLines(true);
        xAxis.setSpaceMin(0.5f);
        xAxis.setSpaceMax(0.5f);
    }

    //設定y軸
    private void ytext() {
        YAxis rightAxis = binding.chart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setLabelCount(4);
    }

    private void text_all(ArrayList<Entry> values1) {
        //LineDataSet為設定線數資料顯示方式
        LineDataSet set1;
        set1=new LineDataSet(values1,"ScoreData");
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER); //LineDataSet.Mode.CUBIC_BEZIER為立方曲線
        set1.setLineWidth(5);
        set1.setCircleRadius(4);  //setCircleRadius為設置焦點圓心的大小
        set1.enableDashedHighlightLine(5,5,0); //enableDashedHighlightLine(線寬度,分隔寬度,階段)為點擊後的虛線顯示樣式
        set1.setHighlightLineWidth(2);
        set1.setHighlightEnabled(true); //setHighlightEnabled為是否禁用點擊高亮線
        set1.setHighLightColor(Color.rgb(0,170,189));
        set1.setValueTextSize(20);
        set1.setDrawFilled(false);  //setDrawFilled為設置禁用範圍背景填充

        LineData data=new LineData(set1);
        binding.chart.setData(data);
        binding.chart.invalidate(); //invalidate()為繪製圖表

    }

    interface OnRecordListReceivedListener {
        void OnRecordListReceived(ArrayList<Record> records);
    }

    private void getRecordsList(OnRecordListReceivedListener listener) {
        ArrayList<Record> records = new ArrayList<>();
        Set<String> students = preferencesHelper.readStringSet("students");

        mDatabase.child("record").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful())
                Log.e("firebase", "Error getting data", task.getException());
            else {
                for (DataSnapshot child : task.getResult().getChildren()) {
                    String childId = child.getKey();
                    child.getChildren().forEach(dataSnapshot -> {
                        Record record = dataSnapshot.getValue(Record.class);
                        students.forEach((studentId) -> {
                            if (childId.equals(studentId))
                                records.add(record);
                            // TODO: Edit to Map
                        });
                    });
                }
                listener.OnRecordListReceived(records);
            }
        });
    }
}

