package co3.greenfoodchallenge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import co3.greenfoodchallenge.R;

import static android.graphics.Color.YELLOW;

public class Pledge extends AppCompatActivity implements View.OnClickListener{

    // Initialize PieChart
    //PieChart pieChart;
    // Navi bar Initialize:
    //UI Object
    private TextView txt_home;
    private TextView txt_progress;
    private TextView txt_community;
    private TextView txt_profile;

    //Initialize btn
    private Button comfirm_btn;
    private Button previous_goals_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge);

        comfirm_btn = findViewById(R.id.comfirm_btn);
        comfirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pledge.this, ShowMyPledgeResult.class);
                startActivity(intent);
            }
        });

        previous_goals_btn = findViewById(R.id.previous_goals_btn);
        previous_goals_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pledge.this, ShowMyPledgeResult.class);
                startActivity(intent);
            }
        });
//        pieChart = (PieChart) findViewById(R.id.pieChart);
//
//        pieChart.setUsePercentValues(true);
//        pieChart.getDescription().setEnabled(false);
//        pieChart.setExtraOffsets(5,10,5,5);
//        pieChart.setDragDecelerationFrictionCoef(0.95f);
//        pieChart.setDrawHoleEnabled(true);
//        pieChart.setHoleColor(Color.WHITE);
//        pieChart.setEntryLabelColor(Color.BLACK);
//        pieChart.setEntryLabelTextSize(15f);
//        pieChart.setTransparentCircleRadius(61f);
//
//        ArrayList<PieEntry> yValues = new ArrayList<>();
//        yValues.add(new PieEntry(34f, "Vancouver"));
//        //yValues.add(new PieEntry(34f, "North Vancouver"));
//        //yValues.add(new PieEntry(34f, "West Vancouver"));
//        yValues.add(new PieEntry(34f, "Burnaby"));
//        yValues.add(new PieEntry(34f, "Coquitlam"));
//        //yValues.add(new PieEntry(34f, "Port Moody"));
//        //yValues.add(new PieEntry(34f, "Maple Ridge"));
//        yValues.add(new PieEntry(34f, "Surrey"));
//        //yValues.add(new PieEntry(34f, "New Westminster"));
//        yValues.add(new PieEntry(34f, "Richmond"));
//        //yValues.add(new PieEntry(34f, "Delta"));
//        //yValues.add(new PieEntry(34f, "Langley"));
//
//
//        PieDataSet dataSet = new PieDataSet(yValues, "");
//        dataSet.setSliceSpace(3f);
//        dataSet.setFormSize(15f);
//        dataSet.setSelectionShift(5f);
//        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//
//        PieData data = new PieData(dataSet);
//        data.setValueTextSize(15f);
//        data.setValueTextColor(Color.BLACK);
//
//        pieChart.setData(data);

        //Simulate a click, select the first item after going in
        bindViews();
    }


    // Navi bar:
    // Package the initial UI component and the event
    private void bindViews() {
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_progress = (TextView) findViewById(R.id.txt_progress);
        txt_community = (TextView) findViewById(R.id.txt_community);
        txt_profile = (TextView) findViewById(R.id.txt_profile);

        txt_home.setOnClickListener(this);
        txt_progress.setOnClickListener(this);
        txt_community.setOnClickListener(this);
        txt_profile.setOnClickListener(this);
    }
    // Reset all text
    private void setSelected(){
        txt_home.setSelected(false);
        txt_progress.setSelected(false);
        txt_community.setSelected(false);
        txt_profile.setSelected(false);
    }


    @Override
    public void onClick(View v)
    {
        // hideAllFragment(transaction);
        switch (v.getId()){
            // Follow the syntax to add the activities
            case R.id.txt_home:
                setSelected();
                txt_home.setSelected(true);
                Intent intent1 = new Intent(Pledge.this, SelectSpecifiedFoodActivity.class);
                startActivity(intent1);
                break;

            case R.id.txt_progress:
                setSelected();
                txt_progress.setSelected(true);
                Intent intent2 = new Intent(Pledge.this, SummaryActivity.class);
                startActivity(intent2);
                break;

            case R.id.txt_profile:
                setSelected();
                txt_profile.setSelected(true);
                Intent intent4 = new Intent(Pledge.this, ProfileActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
