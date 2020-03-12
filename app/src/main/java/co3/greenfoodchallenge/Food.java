/* Copyright (C) 2018  Seiji Story */

package co3.greenfoodchallenge;

public class Food {
    /* Private Data Fields */
    private FoodType type;
    private double amount;
    private FoodUnit unit;

    /* Constructors */
    /* Food(FoodType, double): constructs a food class with the default unit being servings */
    public Food(FoodType initType, double initAmount) {
        type = initType;
        amount = initAmount >= 0 ? initAmount : 0;
        unit = FoodUnit.Servings;
        convertToKg();
    }
    /* Food(FoodType, double): constructs a food class with the specified parameters */
    public Food(FoodType initType, double initAmount, FoodUnit initUnit) {
        type = initType;
        amount = initAmount >= 0 ? initAmount : 0;
        unit = initUnit;
        convertToKg();
    }

    /* Public Methods */
    /* convertToKg(): converts whatever the current amount and units are to kg, keeping the amount
     *                correctly scaled; if the units are already kilograms, the amount value must
     *                not be modified
     */
    public void convertToKg() {
        if (unit == FoodUnit.g) {
            amount /= 1000; /* 1kg = 1000g */
        } else if (unit == FoodUnit.oz) {
            amount /= 35.27; /* 1kg = 35.27 oz */
        } else if (unit == FoodUnit.lbs) {
            amount /= 2.205; /* 1kg = 2.205 lbs */
        } else if (unit == FoodUnit.cal) {
            switch (type) {
                /* The following data has been taken from WolframAlpha, and should be regarded
                 * as an estimate: http://www.wolframalpha.com */
                case Eggs:
                    amount /= 1584; /* 1kg ~~ 1584 cal */
                    break;
                case Milk:
                    amount /= 617;
                    break;
                case Cheese:
                    amount /= 2923;
                    break;
                case Rice:
                    amount /= 1274;
                    break;
                case Bread:
                    amount /= 2668;
                    break;
                case Spaghetti:
                    amount /= 1909;
                    break;
                case Pork:
                    amount /= 2068;
                    break;
                case Beef:
                    amount /= 2356;
                    break;
                case Chicken:
                    amount /= 2231;
                    break;
                case Shrimp:
                    amount /= 882;
                    break;
                case Crab:
                    amount /= 976;
                    break;
                case Fish:
                    amount /= 1518;
                    break;
                case Spinach:
                    amount /= 230;
                    break;
                case Kale:
                    amount /= 500;
                    break;
                case Apples:
                    amount /= 500;
                    break;
            }
        } else if (unit == FoodUnit.Servings) {
            /* The following data has been taken from WolframAlpha, and should be regarded
             * as an estimate: http://www.wolframalpha.com */

            switch (type) {
                case Eggs:
                    amount *= 0.035; /* 1 egg ~~ 0.035kg */
                    break;
                case Milk:
                    amount *= 0.256;
                    break;
                case Cheese:
                    amount *= 0.030;
                    break;
                case Rice:
                    amount *= 0.202;
                    break;
                case Bread:
                    amount *= 0.050;
                    break;
                case Spaghetti:
                    amount *= 0.257;
                    break;
                case Pork:
                    amount *= 0.085; /* 1 serving ~~ 0.085kg */
                    break;
                case Beef:
                    amount *= 0.085; /* 1 serving ~~ 0.085kg */
                    break;
                case Chicken:
                    amount *= 0.085; /* 1 serving ~~ 0.085kg */
                    break;
                case Shrimp:
                    amount *= 0.091;
                    break;
                case Crab:
                    amount *= 0.127;
                    break;
                case Fish:
                    amount *= 0.085;
                    break;
                case Spinach:
                    amount *= 0.140;
                    break;
                case Kale:
                    amount /= 0.085;
                    break;
                case Apples:
                    amount /= 0.182;
                    break;
            }
        }
        unit = FoodUnit.kg;
    }

    public void setAndConvertToKg(double newAmount, FoodUnit newUnit) {
        amount = newAmount >= 0 ? newAmount : 0;
        unit = newUnit;
        convertToKg();
    }

    public void setAndConvertToKg(FoodType newType, double newAmount, FoodUnit newUnit) {
        type = newType;
        amount = newAmount >= 0 ? newAmount : 0;
        unit = newUnit;
        convertToKg();
    }

    public FoodType getType() { return type; }
    public double getAmount() { return amount; }
    public FoodUnit getUnit() { return unit; }

    public double getAmountAsKg() {
        convertToKg();
        return amount;
    }

    @Override
    public String toString(){
        return("Type:" + getType() + "Amount:" + getAmount() + "Unit:" + getUnit());
    }

    /* Warning: using any direct setter functions should be IMMEDIATELY followed by a call to
     * convertToKg() to ensure proper units
     */
    /*
    public void setType(FoodType newType) { type = newType; }
    public void setAmount(double newAmount) {
        amount = newAmount >= 0 ? newAmount : 0;
    }
    public void setUnit(FoodUnit newUnit) { unit = newUnit; }
    /**/
}
