package co3.greenfoodchallenge.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co3.greenfoodchallenge.CO2eCalculator;
import co3.greenfoodchallenge.DBHelper;
import co3.greenfoodchallenge.Food;
import co3.greenfoodchallenge.FoodType;
import co3.greenfoodchallenge.FoodUnit;
import co3.greenfoodchallenge.R;

public class SummaryActivity extends AppCompatActivity {

    private Button addBtn;
    private Button calculate_btn;

    private ArrayList<Food> foodObjectList = new ArrayList<>();
    private DBHelper sql;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list;
    private ArrayList<Long> idList;
    private String tag = "SummaryActivityTag";
    private Toolbar mActionBarToolbar;
    private ListView itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiateVariables(); // Please Do NOT Change the order of this method call or the class WILL NOT build and crash
        sql = new DBHelper(this);
        list = new ArrayList<String>(Arrays.asList(sql.foodDBtoStringArray())); // a list to store all the food
        idList = new ArrayList<Long>(sql.foodId());  // a list to store all the ID
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        itemsList.setAdapter(arrayAdapter);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Today Summary");
        registerClick();
//        parseToFoodArray();

/*
        Intent intent = getIntent();
        final List<String> foodList = intent.getStringArrayListExtra("userInputResult");
        for (String s : foodList){strfoodList += s + "\t";}
        if (foodList == null)
            return;
*/

    }
    /* This Method initiate all the variables and populate the them in to the ArrayList view */
    private void initiateVariables(){
        setContentView(R.layout.activity_summary);
        addBtn = findViewById(R.id.add_more_btn);
        mActionBarToolbar = findViewById(R.id.summary_toolbar);
        calculate_btn = findViewById(R.id.caculate_btn);
        itemsList = findViewById(R.id.summary_list);

    }
    /* This method groups all the button clicks together, all button implementation goes here */
    private void registerClick(){
        ListView itemsList = findViewById(R.id.summary_list);

        /* Delete function for the list_view implemented with the database */
        itemsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(SummaryActivity.this);
                        adb.setTitle("Delete?");
                        final int positionToRemove = position;
                        adb.setMessage("Are you sure you want to delete " + arrayAdapter.getItem(positionToRemove));
                        adb.setNegativeButton("Cancel",null);
                        adb.setPositiveButton("ok", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sql.deleteFood(idList.get(positionToRemove));
                                Log.i("Removed ID:",Long.toString(idList.get(positionToRemove)));
                                arrayAdapter.remove(arrayAdapter.getItem(positionToRemove));
                                arrayAdapter.notifyDataSetChanged();
                                idList.remove(positionToRemove);
                                for (String member : list){
                                    Log.i(tag, member);
                                }
                                for (Long member : idList){
                                    Log.i(tag, Long.toString(member));
                                }
                            }
                        });
                        adb.show();

                    }
                }
        );




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.close();
                finish();
            }
        });

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                CO2eCalculator calc = new CO2eCalculator(sql.foodDBtoFoodArray());
                intent.putExtra("totalCO2e", calc.getTotalCO2eAmount());
                intent.putExtra("totalCO2eYearly", calc.getTotalCO2eYearly() - 2.0 * 365);
                intent.putExtra("CO2eAsTrees", 2e6 * (calc.equalTrees() - 2.0 * 0.038573));
                intent.putExtra("CO2eAsMileage", 2e6 * (calc.equalMiles() - 2.0 * 2.5));
                intent.putExtra("CO2eAsGasoline", 2e6 * (calc.equalGas() - 2.0 * 0.113));
                */
                Intent intent = ResultAndFeedBackActivity.makeIntent(SummaryActivity.this);
                sql.close();
                startActivity(intent);
                finish();
            }
        });
    }



    public static Intent makeIntent(Context context){
        return new Intent(context,SummaryActivity.class);
    }

//    private void parseToFoodArray() {
//        Intent intent = getIntent();
//        List<String> foodList = intent.getStringArrayListExtra("userInputResult");
//        for (int i = 0; i < foodList.size(); ++i) {
//            String splitString[] = foodList.get(i).replaceAll(":","").split(" ");
//            FoodType type;
//            double amount = Double.valueOf(splitString[1]);
//            FoodUnit unit;
//            try {
//                type = FoodType.valueOf(splitString[0]);
//                unit = FoodUnit.valueOf(splitString[2]);
//            } catch (IllegalArgumentException e) {
//                return;
//            }
//            foodObjectList.add(new Food(type, amount, unit));
//        }
//    }





}
