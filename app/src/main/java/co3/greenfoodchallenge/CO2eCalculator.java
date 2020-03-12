package co3.greenfoodchallenge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CO2eCalculator {
    private List<Food> foodList;
    private double co2eTotalAmount;
    private double co2eAmount;

    //constructor
    public CO2eCalculator(){
        foodList = new ArrayList<>();
        co2eTotalAmount = 0;
    }

    public CO2eCalculator(ArrayList<Food> initList) {
        if (initList == null) {
            co2eTotalAmount = 0;
            return;
        }
        foodList = new ArrayList<>();
        foodList.addAll(initList);
        for (int i = 0; i < foodList.size(); ++i)
            co2eTotalAmount += getFoodCO2e(foodList.get(i));
    }

    public int size() { return foodList.size(); };

    public void insertFood(Food new_food){
        foodList.add(new_food);
    }

    public void deleteFood(Food food_delete){
        Iterator<Food> iterator = foodList.iterator();
        while (iterator.hasNext()){
            Food next_food = iterator.next();
            if (equals(next_food == food_delete)){
                foodList.remove(food_delete);
            }
        }
    }

    /*all data are from "Meat Eater's Guide" */
    //get Food's CO2e from food type;
    public double getFoodCO2e(Food food1) {
        co2eAmount = food1.getAmountAsKg();
        FoodType type = food1.getType();
        switch (type) {
            case Eggs:
                co2eAmount *= 4.8; /* 1kg ~~ 4.8kg CO2e */
                break;
            case Milk: /* 1kg ~~ 1.9 kg CO2e */
                co2eAmount *= 1.9;
                break;
            case Cheese:
                co2eAmount *= 13.5; /* 1kg ~~ 13.5 kg CO2e */
                break;
            case Rice:
                co2eAmount *= 2.7; /* 1kg ~~ 2.7 kg CO2e */
                break;
            case Bread:
                co2eAmount *= 2.7; /* 1kg ~~ 2.7 kg CO2e */
                break;
            case Spaghetti:
                co2eAmount *= 2.7; /* 1kg ~~ 2.7 kg CO2e */
                break;
            case Pork:
                co2eAmount *= 12.1; /* 1kg ~~ 12.1 kg CO2e */
                break;
            case Beef:
                co2eAmount *= 27.0; /* 1kg ~~ 27.0 kg CO2e */
                break;
            case Chicken:
                co2eAmount *= 6.9; /* 1kg ~~ 6.9 kg CO2e */
                break;
            case Shrimp:
                co2eAmount *= 10.0; /* 1kg ~~ 11.9 kg CO2e */
                break;
            case Crab:
                co2eAmount *= 10.0; /* 1kg ~~ 11.9 kg CO2e */
                break;
            case Fish:
                co2eAmount *= 11.9; /* 1kg ~~ 11.9 kg CO2e */
                break;
            case Spinach:
                co2eAmount *= 2.0;
                break;
            case Kale:
                co2eAmount *= 2.0;
                break;
            case Apples:
                co2eAmount *= 2.0;
                break;
        }
        return co2eAmount;
    }
    public double getTotalCO2eAmount(){

        return co2eTotalAmount;
    }

    public void calculateTotalCO2e(){
        int size = foodList.size();
        for(int counter = 0; counter < size;  counter++){
            Food food1 = foodList.get(counter);
            co2eTotalAmount += getFoodCO2e(food1);
        }
    }

    public double getTotalCO2eYearly(){
        double co2e_amount_yearly = co2eTotalAmount * 365;
        return co2e_amount_yearly;
    }

    public double equalTrees(){
        double tree;
        tree = co2eTotalAmount * 0.038573;
        return tree;
    }

    public double equalMiles(){
        double mile;
        mile = co2eTotalAmount * 2.5;
        return mile;
    }

    public double equalGas(){
        double gallon;
        gallon = co2eTotalAmount * 0.113;
        return gallon;
    }
}
