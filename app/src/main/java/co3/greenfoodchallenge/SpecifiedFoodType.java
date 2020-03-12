package co3.greenfoodchallenge;

public class SpecifiedFoodType {
    int typePicture;
    String typeName, typeDescription;

    public SpecifiedFoodType(int meatPicture, String meatName, String meatDescritption) {
        this.typePicture = meatPicture;
        this.typeName = meatName;
        this.typeDescription = meatDescritption;
    }

    public int getTypePicture() {
        return typePicture;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypePicture(int typePicture) {
        this.typePicture = typePicture;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
}
