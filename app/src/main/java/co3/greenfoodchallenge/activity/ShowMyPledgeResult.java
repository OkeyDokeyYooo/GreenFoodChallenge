package co3.greenfoodchallenge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import co3.greenfoodchallenge.R;

public class ShowMyPledgeResult extends AppCompatActivity implements View.OnClickListener{

    PieChart pieChart;
    // Navi bar Initialize:
    //UI Object
    private TextView txt_home;
    private TextView txt_progress;
    private TextView txt_community;
    private TextView txt_profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_pledge_result);

        pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(15f);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(0f, "Done"));
        yValues.add(new PieEntry(34f, "Keek it up!"));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setFormSize(15f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);

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
                Intent intent1 = new Intent(ShowMyPledgeResult.this, SelectSpecifiedFoodActivity.class);
                startActivity(intent1);
                break;

            case R.id.txt_progress:
                setSelected();
                txt_progress.setSelected(true);
                Intent intent2 = new Intent(ShowMyPledgeResult.this, SummaryActivity.class);
                startActivity(intent2);
                break;

            case R.id.txt_community:
                setSelected();
                txt_community.setSelected(true);
                Intent intent3 = new Intent(ShowMyPledgeResult.this, Pledge.class);
                startActivity(intent3);
                break;

            case R.id.txt_profile:
                setSelected();
                txt_profile.setSelected(true);
                Intent intent4 = new Intent(ShowMyPledgeResult.this, ProfileActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
