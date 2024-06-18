import java.util.ArrayList;

public class Ingredients {
    private ArrayList<ArrayList<String>> ingreAllRecipe;

    Validator validator = new Validator();

    public Ingredients() {
        ingreAllRecipe = new ArrayList<>();
    }

    public void addNewIngreList(ArrayList<String> newRecipe) {
        ingreAllRecipe.add(newRecipe);
    }

    public void addIngretotheList(String ingredient, int recipeIndex) {
        if (validator.isIndexValid(recipeIndex, ingreAllRecipe.size())) {
            System.out.println("ID salah!");
            return;
        }
        ingreAllRecipe.get(recipeIndex).add(ingredient);
    }

    public ArrayList<String> getRecipeIngredients(int recipeIndex) {
        System.out.println(ingreAllRecipe.size());
        if (validator.isIndexValid(recipeIndex, ingreAllRecipe.size())) {
            System.out.println("Resep tidak ditemukan.");
            return new ArrayList<>();
        }
        return new ArrayList<>(ingreAllRecipe.get(recipeIndex));
    }

    public void setIngreInnerList(int recipeIndex, int ingredientIndex, String newIngredient) {
        if (validator.isIndexValid(recipeIndex, ingreAllRecipe.size()) || ingredientIndex < 0 || ingredientIndex >= ingreAllRecipe.get(recipeIndex).size()) {
            System.out.println("Index tidak valid.");
            return;
        }
        ingreAllRecipe.get(recipeIndex).set(ingredientIndex, newIngredient);
    }

    public void displayRecipeIngredients(int recipeIndex) {
        if (validator.isIndexValid(recipeIndex, ingreAllRecipe.size())) {
            System.out.println("Resep tidak ditemukan.");
            return;
        }
        System.out.println("Ingredients " + ":");
        int num = 1;
        for (String ingredient : ingreAllRecipe.get(recipeIndex)) {
            System.out.println(num + "." + ingredient);
            num++;
        }
    }

    public void removeInnerList(int recipeIndex, int ingredientIndex) {
        if (validator.isIndexValid(recipeIndex, ingreAllRecipe.size()) || ingredientIndex < 0 || ingredientIndex >= ingreAllRecipe.get(recipeIndex).size()) {
            System.out.println("Index tidak valid.");
            return;
        }
        ingreAllRecipe.get(recipeIndex).remove(ingredientIndex);
    }

    public void removIngreList(int recipeIndex) {
        if (validator.isIndexValid(recipeIndex, ingreAllRecipe.size())) {
            System.out.println("Index tidak valid.");
            return;
        }
        ingreAllRecipe.remove(recipeIndex);
    }
}
