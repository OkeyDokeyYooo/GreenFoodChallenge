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
                case Beans:
                    amount /= 1012; /* 1kg ~~ 1012 cal */
                    break;
                case Beef:
                    amount /= 2356; /* 1kg ~~ 2356 cal */
                    break;
                case Chicken:
                    amount /= 2231; /* 1kg ~~ 2231 cal */
                    break;
                case Eggs:
                    amount /= 1584; /* 1kg ~~ 1584 cal */
                    break;
                case Fish:
                    amount /= 1518; /* 1kg ~~ 1518 cal */
                    break;
                case Pork:
                    amount /= 2068; /* 1kg ~~ 2068 cal */
                    break;
                case Veggies:
                    amount /= 437; /* 1kg ~~ 437 cal */
                    break;
            }
        } else if (unit == FoodUnit.Servings) {
            /* The following data has been taken from WolframAlpha, and should be regarded
             * as an estimate: http://www.wolframalpha.com */
            switch (type) {
                case Beans:
                    amount *= 0.130; /* 1 serving ~~ 0.130kg */
                    break;
                case Beef:
                    amount *= 0.085; /* 1 serving ~~ 0.085kg */
                    break;
                case Chicken:
                    amount *= 0.085; /* 1 serving ~~ 0.085kg */
                    break;
                case Eggs:
                    amount *= 0.035; /* 1 egg ~~ 0.035kg */
                    break;
                case Fish:
                    amount *= 0.085; /* 1 serving ~~ 0.085kg */
                    break;
                case Pork:
                    amount *= 0.085; /* 1 serving ~~ 0.085kg */
                    break;
                case Veggies:
                    amount *= 0.140; /* 1 serving ~~ 0.140kg */
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

    public FoodType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public FoodUnit getUnit() {
        return unit;
    }

    public double getAmountAsKg() {
        convertToKg();
        return amount;
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