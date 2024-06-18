public class recipe {
    String ID;
    String name;
    String category;
    String prepTime;
    String cookTime;

    recipe(String inputID, String inputname, String inputCategory, String inputprepTime, String inputcookTime) {
        this.ID = inputID;
        this.name = inputname;
        this.category = inputCategory;
        this.prepTime = inputprepTime;
        this.cookTime = inputcookTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        this.ID = iD;
    }
}

class food extends recipe {
    String type;
    String vegan;

    food(String inputID, String inputName, String inputCat, String inputType, String inputVegan, String inputprepTime, String inputcookTime) {

        super(inputID, inputName, inputCat, inputprepTime, inputcookTime);
        this.vegan = inputVegan;
        this.type = inputType;
    }

    String getVegan() {
        return vegan;
    }

    public void setVegan(String vegan) {
        this.vegan = vegan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class beverage extends recipe {
    String type;

    beverage(String inputID, String inputName, String inputCat, String inputType, String inputprepTime, String inputcookTime) {
        super(inputID, inputName, inputCat, inputprepTime, inputcookTime);
        this.type = inputType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
