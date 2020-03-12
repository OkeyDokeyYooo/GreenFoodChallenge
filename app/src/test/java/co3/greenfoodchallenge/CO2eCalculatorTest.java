package co3.greenfoodchallenge;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CO2eCalculatorTest {
    double result;
    double answer;
    Food temp_food;
    Food new_food = new Food(FoodType.Beef, 1, FoodUnit.kg );//1kg beef ~~ 27.0 co2e
    Food new_food1 = new  Food(FoodType.Kale, 1, FoodUnit.kg); //1kg beans ~~2.0 co2e
    Food new_food2 = new Food(FoodType.Chicken, 1000, FoodUnit.g); // 1kg ~~ 6.9kg co2e
    CO2eCalculator calculator = new CO2eCalculator();


    @Test
    public void calculator_test() {
        //final double TEST_AMOUNT = 1;
        Food food_beef = new Food(FoodType.Beef, 1000, FoodUnit.g);
        CO2eCalculator foodlist = new CO2eCalculator();
        foodlist.getFoodCO2e(food_beef);
    }

    @Test
    public void insertFood() {
        int answer_insert = 1;
        calculator.insertFood(new_food);
        int size = calculator.size();
        assertTrue(size == answer_insert);

        //1kg beans ~~ 2.0 kg co2
        calculator.insertFood(new_food1);
        size = calculator.size();
        answer_insert = 2;
        assertTrue(size == answer_insert);
    }

    @Test
    public void deleteFood() {
        calculator.insertFood(new_food);
        calculator.insertFood(new_food1);
        calculator.insertFood(new_food2);

        int answer_delete = 2;
        calculator.deleteFood(new_food);
        int size = calculator.size();
        assertTrue(size == answer_delete);

        calculator.deleteFood(new_food);
        size = calculator.size();
        assertTrue(size == answer_delete);

        answer_delete = 1;
        calculator.deleteFood(new_food1);
        size = calculator.size();
        assertTrue(size == answer_delete);
    }

    @Test
    public void getFoodCO2e() {
        result = calculator.getFoodCO2e(new_food);
        answer = 27.0;
        assertTrue((result <= answer + 0.01 )&&(
        result >= answer - 0.01));

        result = calculator.getFoodCO2e(new_food1);
        answer = 2.0;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));
    }

    @Test
    public void calculateTotalCO2eYearly() {
        calculator.insertFood(new_food1);
        result = calculator.calculateTotalCO2eYearly();
        answer = 365 * 2;
//        System.out.println(result + "\n");
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));

        calculator.insertFood(new_food);
        result = calculator.calculateTotalCO2eYearly();
//        System.out.println(result + "\n");
        answer = 365 * 29;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));
    }

    @Test
    public void equalTrees(){
        calculator.insertFood(new_food1);
        result = calculator.equalTrees();
        answer = 2 * 0.038573;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));

        calculator.insertFood(new_food);
        result = calculator.equalTrees();
        answer = 29 * 0.038573;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));
    }

    @Test
    public void equalMiles(){
        calculator.insertFood(new_food1);
        result = calculator.equalMiles();
        answer = 2 * 2.5;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));

        calculator.insertFood(new_food);
        result = calculator.equalMiles();
        answer = 29 * 2.5;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));

    }

    @Test
    public void equalGas(){
        calculator.insertFood(new_food1);
        result = calculator.equalGas();
        answer = 2 * 0.113;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));

        calculator.insertFood(new_food);
        result = calculator.equalGas();
        answer = 29 * 0.113;
        assertTrue((result <= answer + 0.01 )&&(
                result >= answer - 0.01));
    }
}