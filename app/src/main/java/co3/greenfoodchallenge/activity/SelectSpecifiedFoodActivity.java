package co3.greenfoodchallenge.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co3.greenfoodchallenge.MyFragmentNaviBar;
import co3.greenfoodchallenge.R;
import co3.greenfoodchallenge.adapter.SpecifiedFoodAdapter;
import co3.greenfoodchallenge.SpecifiedFoodType;

public class SelectSpecifiedFoodActivity extends AppCompatActivity implements View.OnClickListener{

    // Navi bar Initialize:
    //UI Object
    // private TextView txt_topbar;
    private TextView txt_home;
    private TextView txt_progress;
    private TextView txt_community;
    private TextView txt_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //use for navi bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_select_food);
        getIncomingIntent();

            /* Bug exits have not fix yet


        Button goToSummaryBTN = (Button) findViewById(R.id.go_to_summary_btn);
        goToSummaryBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SummaryActivity.makeIntent(SelectSpecifiedFoodActivity.this);
                startActivity(intent);
                finish();
            }

        });
        */

        // Navi bar: Simulate a click, select the first item after going in

        bindViews();

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("Food_Position")) {
            int position = getIntent().getIntExtra("Food_Position", 0);
            if (position == 0) goToDairy();
            if (position == 1) goToGrain();
            if (position == 2) goToMeat();
            if (position == 3) goToSeafood();
            if (position == 4) goToPlants();
        }
    }

    private void goToDairy() {
        RecyclerView rv = findViewById(R.id.food_rv);
        List<SpecifiedFoodType> specifiedFoodTypes = new ArrayList<>();

        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.eggs,
                getResources().getString(R.string.selection_dairy_eggs_title),
                getResources().getString(R.string.selection_dairy_eggs_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.milk,
                getResources().getString(R.string.selection_dairy_milk_title),
                getResources().getString(R.string.selection_dairy_milk_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.cheese,
                getResources().getString(R.string.selection_dairy_cheese_title),
                getResources().getString(R.string.selection_dairy_cheese_desc)));

        SpecifiedFoodAdapter adapter = new SpecifiedFoodAdapter(this, specifiedFoodTypes);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void goToGrain() {
        RecyclerView rv = findViewById(R.id.food_rv);
        List<SpecifiedFoodType> specifiedFoodTypes = new ArrayList<>();

        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.rice,
                getResources().getString(R.string.selection_grain_rice_title),
                getResources().getString(R.string.selection_grain_rice_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.bread,
                getResources().getString(R.string.selection_grain_bread_title),
                getResources().getString(R.string.selection_grain_bread_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.spaghetti,
                getResources().getString(R.string.selection_grain_spaghetti_title),
                getResources().getString(R.string.selection_grain_spaghetti_desc)));

        SpecifiedFoodAdapter adapter = new SpecifiedFoodAdapter(this, specifiedFoodTypes);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void goToMeat() {
        RecyclerView rv = findViewById(R.id.food_rv);
        List<SpecifiedFoodType> specifiedFoodTypes = new ArrayList<>();

        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.pork,
                getResources().getString(R.string.selection_meat_pork_title),
                getResources().getString(R.string.selection_meat_pork_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.beef,
                getResources().getString(R.string.selection_meat_beef_title),
                getResources().getString(R.string.selection_meat_beef_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.chicken,
                getResources().getString(R.string.selection_meat_chicken_title),
                getResources().getString(R.string.selection_meat_chicken_desc)));

        SpecifiedFoodAdapter adapter = new SpecifiedFoodAdapter(this, specifiedFoodTypes);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void goToSeafood() {
        RecyclerView rv = findViewById(R.id.food_rv);
        List<SpecifiedFoodType> specifiedFoodTypes = new ArrayList<>();

        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.shrimp,
                getResources().getString(R.string.selection_seafood_shrimp_title),
                getResources().getString(R.string.selection_seafood_shrimp_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.crab,
                getResources().getString(R.string.selection_seafood_crab_title),
                getResources().getString(R.string.selection_seafood_crab_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.fish,
                getResources().getString(R.string.selection_seafood_fish_title),
                getResources().getString(R.string.selection_seafood_fish_desc)));

        SpecifiedFoodAdapter adapter = new SpecifiedFoodAdapter(this, specifiedFoodTypes);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void goToPlants() {
        RecyclerView rv = findViewById(R.id.food_rv);
        List<SpecifiedFoodType> specifiedFoodTypes = new ArrayList<>();

        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.spinach,
                getResources().getString(R.string.selection_plant_spinach_title),
                getResources().getString(R.string.selection_plant_spinach_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.kale,
                getResources().getString(R.string.selection_plant_kale_title),
                getResources().getString(R.string.selection_plant_kale_desc)));
        specifiedFoodTypes.add(new SpecifiedFoodType(
                R.drawable.apple,
                getResources().getString(R.string.selection_plant_apple_title),
                getResources().getString(R.string.selection_plant_apple_desc)));

        SpecifiedFoodAdapter adapter = new SpecifiedFoodAdapter(this, specifiedFoodTypes);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    // Navi bar:
    // Package the initial UI component and the event
    private void bindViews() {
        // txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_progress = (TextView) findViewById(R.id.txt_progress);
        txt_community = (TextView) findViewById(R.id.txt_community);
        txt_profile = (TextView) findViewById(R.id.txt_profile);

        txt_home.setOnClickListener((View.OnClickListener) this);
        txt_progress.setOnClickListener((View.OnClickListener) this);
        txt_community.setOnClickListener((View.OnClickListener) this);
        txt_profile.setOnClickListener((View.OnClickListener) this);
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
        android.app.FragmentTransaction transaction=getFragmentManager().beginTransaction();
        // hideAllFragment(transaction);
        switch (v.getId()){
            // Follow the syntax to add the activities
            case R.id.txt_home:
                setSelected();
                txt_home.setSelected(true);
                Intent intent1 = new Intent(SelectSpecifiedFoodActivity.this, SelectFoodTypeActivity.class );
                startActivity(intent1);
                break;
            case R.id.txt_progress:
                setSelected();
                txt_progress.setSelected(true);
                Intent intent2 = new Intent(SelectSpecifiedFoodActivity.this, SummaryActivity.class);
                startActivity(intent2);
                break;
            case R.id.txt_community:
                setSelected();
                txt_community.setSelected(true);
                Intent intent3 = new Intent(SelectSpecifiedFoodActivity.this, Pledge.class);
                startActivity(intent3);
                break;

            // Link the profile "bottom" to ProcileActivity.class, but sth wrong with the class.
            case R.id.txt_profile:
                setSelected();
                txt_profile.setSelected(true);
                Intent intent4 = new Intent(SelectSpecifiedFoodActivity.this, ProfileActivity.class);
                startActivity(intent4);
                break;
        }
    }

}
