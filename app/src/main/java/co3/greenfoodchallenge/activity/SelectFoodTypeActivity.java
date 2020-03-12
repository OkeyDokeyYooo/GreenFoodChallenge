package co3.greenfoodchallenge.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import co3.greenfoodchallenge.DBHelper;
import co3.greenfoodchallenge.Food;
import co3.greenfoodchallenge.FoodType;
import co3.greenfoodchallenge.adapter.FoodTypeAdapter;
import co3.greenfoodchallenge.FoodTypeUI;
import co3.greenfoodchallenge.FoodUnit;
import co3.greenfoodchallenge.MyFragmentNaviBar;
import co3.greenfoodchallenge.R;

public class SelectFoodTypeActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int SPECIFIC_FOOD_REQUEST_CODE = 1;
    ArrayList<Food> userInputResult = new ArrayList<>();

    // Navi bar Initialize:
    //UI Object
    private TextView txt_home;
    private TextView txt_progress;
    private TextView txt_community;
    private TextView txt_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //use for navi bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_select_food_class);
//        Toolbar myToolbar = findViewById(R.id.summary_toolbar);
//        setSupportActionBar(myToolbar);
        RecyclerView rv = findViewById(R.id.food_list_rv);
        List<FoodTypeUI> foodTypeList =  new ArrayList<>();
        foodTypeList.add(new FoodTypeUI(
                R.drawable.food_type_dairy,
                "Dairy",
                getResources().getString(R.string.selection_dairy_desc)));
        foodTypeList.add(new FoodTypeUI(
                R.drawable.food_type_grain,
                getResources().getString(R.string.selection_grain_title),
                getResources().getString(R.string.selection_grain_desc)));
        foodTypeList.add(new FoodTypeUI(
                R.drawable.food_type_meat,
                getResources().getString(R.string.selection_meat_title),
                getResources().getString(R.string.selection_meat_desc)));
        foodTypeList.add(new FoodTypeUI(
                R.drawable.food_type_seafood,
                getResources().getString(R.string.selection_seafood_title),
                getResources().getString(R.string.selection_seafood_desc)));
        foodTypeList.add(new FoodTypeUI(
                R.drawable.food_type_plants,
                getResources().getString(R.string.selection_plants_title),
                getResources().getString(R.string.selection_plants_desc)));
        FoodTypeAdapter adapter = new FoodTypeAdapter(this, foodTypeList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //Button goToSummary = findViewById(R.id.go_to_summary_btn);
//        goToSummary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = SummaryActivity.makeIntent(SelectFoodTypeActivity.this);
//                ArrayList<String> inputListAsString = new ArrayList<String>();
//                for (int j = 0; j < userInputResult.size(); ++j) {
//                    inputListAsString.add(String.format(
//                            "%s: %.1f %s",
//                            userInputResult.get(j).getType().name(),
//                            userInputResult.get(j).getAmount(),
//                            userInputResult.get(j).getUnit().name()));
//                }
//                intent.putExtra("userInputResult", inputListAsString);
//                startActivity(intent);
//            }
//        });

        //Simulate a click, select the first item after going in
        bindViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        DBHelper sql = new DBHelper(this);
        if (requestCode != SPECIFIC_FOOD_REQUEST_CODE)
            return;
        if (resultCode != RESULT_OK)
            return;
        String foodType = data.getStringExtra("FoodType");
        double foodAmount = data.getDoubleExtra("FoodAmount", 0);
        String foodUnit = data.getStringExtra("FoodUnit");
        try {
            System.out.println("Adding to SelectFoodTypeActivity");
            Food h = new Food(
                    FoodType.valueOf(foodType),
                    foodAmount,
                    FoodUnit.valueOf(foodUnit));
            System.out.printf("%s: %.2f %s\n",
                    h.getType().name(),
                    h.getAmount(),
                    h.getUnit().name());
            userInputResult.add(h);
            sql.addFoodInfo(h);
            sql.close();
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context,SelectFoodTypeActivity.class);
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
//
//    // Hide all Fragment
//    private void hideAllFragment(FragmentTransaction fragmentTransaction){
//        if(fg1 != null)fragmentTransaction.hide(fg1);
//        if(fg2 != null)fragmentTransaction.hide(fg2);
//        if(fg3 != null)fragmentTransaction.hide(fg3);
//        if(fg4 != null)fragmentTransaction.hide(fg4);
//    }

    @Override
    public void onClick(View v)
    {
        // hideAllFragment(transaction);
        switch (v.getId()){
            // Follow the syntax to add the activities
            //case R.id.txt_home:
            // do nothing.
            //break;
            case R.id.txt_progress:
                setSelected();
                txt_progress.setSelected(true);
                Intent intent2 = new Intent(SelectFoodTypeActivity.this, SummaryActivity.class);
                startActivity(intent2);
                break;
            case R.id.txt_community:
                setSelected();
                txt_community.setSelected(true);
                Intent intent3 = new Intent(SelectFoodTypeActivity.this, Pledge.class);
                startActivity(intent3);
                break;

            // Link the profile "bottom" to ProcileActivity.class, but sth wrong with the class.
            case R.id.txt_profile:
                setSelected();
                txt_profile.setSelected(true);
                Intent intent4 = new Intent(SelectFoodTypeActivity.this, ProfileActivity.class);
                startActivity(intent4);
                break;
        }
    }

}
