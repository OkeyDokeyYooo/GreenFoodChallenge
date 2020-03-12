package co3.greenfoodchallenge;

public class FoodTypeUI {
    int background;
    String foodType, foodDescription;

    public FoodTypeUI(int background, String foodType, String foodDescription) {
        this.background = background;
        this.foodType = foodType;
        this.foodDescription = foodDescription;
    }

    public int getBackground() {
        return background;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
}
