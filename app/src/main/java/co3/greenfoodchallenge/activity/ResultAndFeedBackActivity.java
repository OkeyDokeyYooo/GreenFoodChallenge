package co3.greenfoodchallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import co3.greenfoodchallenge.CO2eCalculator;
import co3.greenfoodchallenge.DBHelper;
import co3.greenfoodchallenge.R;

    public class ResultAndFeedBackActivity extends AppCompatActivity {

        private double totalCO2e;
        private double yearlyCO2e;
        private double co2eAsTrees;
        private double co2eAsMiles;
        private double co2eAsGas;

        private Toolbar mActionBarToolbar;
        private TextView totalCO2e_UI;
        private TextView totalCO2eSavings_UI;
        private TextView cityCO2eSavings_UI;
        private TextView treesCO2eSavings_UI;
        private TextView milesCO2eSavings_UI;
        private TextView gasCO2eSavings_UI;
        private Button backToSelectFood;
        private ImageView iv;
        private TextView tvRecipeName;
        private TextView tvRecipeDescription;
        private CO2eCalculator mCalculator;
        private DBHelper sql;
        private Button shareFB;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initiateVariables(); // Please DO NOT Change the order of this method call or the class WILL NOT build and crash
            sql = new DBHelper(this);
            mCalculator = new CO2eCalculator(sql.foodDBtoFoodArray());

            Log.d("TotalCo2",String.valueOf(mCalculator.getTotalCO2eAmount()));
            setSupportActionBar(mActionBarToolbar);
            getSupportActionBar().setTitle("Result");
        /*
        Intent intent = getIntent();
        totalCO2e = intent.getDoubleExtra("totalCO2e", 0);
        yearlyCO2e = intent.getDoubleExtra("totalCO2eYearly", 0);
        co2eAsTrees = intent.getDoubleExtra("CO2eAsTrees", 0);
        co2eAsMiles = intent.getDoubleExtra("CO2eAsMileage", 0);
        co2eAsGas = intent.getDoubleExtra("CO2eAsGasoline", 0);
        */

            totalCO2e_UI.setText(String.valueOf(roundToNDigits(mCalculator.getTotalCO2eAmount(),2)));
            totalCO2eSavings_UI.setText(String.valueOf(roundToNDigits(mCalculator.getTotalCO2eYearly(),2)));
            cityCO2eSavings_UI.setText(String.valueOf(roundToNDigits(mCalculator.getTotalCO2eYearly(),2)));
            treesCO2eSavings_UI.setText(String.valueOf(roundToNDigits(mCalculator.equalTrees(),2)));
            milesCO2eSavings_UI.setText(String.valueOf(roundToNDigits(mCalculator.equalMiles(),2)));
            gasCO2eSavings_UI.setText(String.valueOf(roundToNDigits(mCalculator.equalGas(),2)));

            backToSelectFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllValueDefault();
                    sql.deleteFoodTable();
                    sql.close();
                    finish();
                }
            });

            shareFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ResultAndFeedBackActivity.this, SocialFBActivity.class));
                }
            });

        /*
        initial the random int to select a recipe to the user
         */

            Random rand = new Random();
            int n = rand.nextInt(4);

            if (n == 0) {
                iv.setImageResource(R.drawable.fruitsalad);
                tvRecipeName.setText("Fruit Salad");
                tvRecipeDescription.setText(R.string.recipe_fruitsalad);
            }

            if (n == 1) {
                iv.setImageResource(R.drawable.garlic_lettuce);
                tvRecipeName.setText("Garlic Lettuce");
                tvRecipeDescription.setText(R.string.recipe_garliclettuce);
            }

            if (n == 2) {
                iv.setImageResource(R.drawable.tomatoeggs);
                tvRecipeName.setText("Stir-fried Tomato");
                tvRecipeDescription.setText(R.string.recipe_tomatoeggs);
            }

            if (n == 3) {
                iv.setImageResource(R.drawable.oysterbroccoli);
                tvRecipeName.setText("Oyster Broccoli");
                tvRecipeDescription.setText(R.string.recipe_oysterbroccoli);
            }


        }
        public void setAllValueDefault(){
            totalCO2e_UI.setText(String.valueOf(0.0));
            totalCO2eSavings_UI.setText(String.valueOf( 0.0));
            cityCO2eSavings_UI.setText(String.valueOf(0.0));
            treesCO2eSavings_UI.setText(String.valueOf(0.0));
            milesCO2eSavings_UI.setText(String.valueOf(0.0));
            gasCO2eSavings_UI.setText(String.valueOf(0.0));
        }

        /* declare all the variable HERE and make them private to the entire class */
        public void initiateVariables(){
            setContentView(R.layout.activity_result_and_feed_back);
            mActionBarToolbar =  findViewById(R.id.result_toolbar);
            totalCO2e_UI = (TextView)findViewById(R.id.TotalCO2e);
            totalCO2eSavings_UI = (TextView)findViewById(R.id.totalCO2eSavings);
            cityCO2eSavings_UI = (TextView)findViewById(R.id.totalCityCO2eSavings);
            treesCO2eSavings_UI = (TextView)findViewById(R.id.CO2eSavingsTrees);
            milesCO2eSavings_UI = (TextView)findViewById(R.id.CO2eSavingsMiles);
            gasCO2eSavings_UI = (TextView)findViewById(R.id.CO2eSavingsGas);
            backToSelectFood = (Button) findViewById(R.id.back_to_food_select_btn);
            iv = (ImageView) findViewById(R.id.recipe_recommend_picture_iv);
            tvRecipeName = (TextView) findViewById(R.id.recipe_recommend_name_tv);
            tvRecipeDescription = (TextView) findViewById(R.id.recipe_recommend_recipe_tv);
            shareFB = (Button) findViewById(R.id.buttonFB);
        }


        public static Intent makeIntent(Context context){
            return new Intent(context,ResultAndFeedBackActivity.class);
        }

        private double roundToNDigits(double value, int nDigits) {
            return Math.round(value * (10 ^ nDigits)) / (double) (10 ^ nDigits);
        }
    }
