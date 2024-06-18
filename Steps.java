import java.util.ArrayList;

public class Steps {
    private ArrayList<ArrayList<String>> stepsAllRecipe;

    public Steps() {
        stepsAllRecipe = new ArrayList<>();
    }

    private boolean isStepIndexValid(int stepIndex) {
        return stepIndex < 0 || stepIndex >= stepsAllRecipe.size();
    }

    public void addNewStepsList(ArrayList<String> newSteps) {
        stepsAllRecipe.add(newSteps);
    }

    public void addSteptotheList(String step, int stepIndex) {
        if (isStepIndexValid(stepIndex)) {
            System.out.println("ID salah!");
            return;
        }
        stepsAllRecipe.get(stepIndex).add(step);
    }

    public ArrayList<String> getRecipeSteps(int recipeIndex) {
        System.out.println(stepsAllRecipe.size());
        if (isStepIndexValid(recipeIndex)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(stepsAllRecipe.get(recipeIndex));
    }

    public void setStepInnerList(int recipeIndex, int stepIndex, String newStep) {
        if (isStepIndexValid(recipeIndex) || stepIndex < 0 || stepIndex >= stepsAllRecipe.get(recipeIndex).size()) {
            System.out.println("Index tidak valid.");
            return;
        }
        stepsAllRecipe.get(recipeIndex).set(stepIndex, newStep);
    }

    public void displayRecipeSteps(int recipeIndex) {
        if (isStepIndexValid(recipeIndex)) {
            System.out.println("Resep tidak ditemukan.");
            return;
        }
        System.out.println("Steps " + ":");
        int num = 1;
        for (String step : stepsAllRecipe.get(recipeIndex)) {
            System.out.println(num + "." + step);
            num++;
        }
    }

    public void removeInnerList(int recipeIndex, int stepIndex) {
        if (isStepIndexValid(recipeIndex) || stepIndex < 0 || stepIndex >= stepsAllRecipe.get(recipeIndex).size()) {
            System.out.println("Index tidak valid.");
            return;
        }
        stepsAllRecipe.get(recipeIndex).remove(stepIndex);
    }

    public void removIngreList(int recipeIndex) {
        if (isStepIndexValid(recipeIndex)) {
            System.out.println("Index tidak valid.");
            return;
        }
        stepsAllRecipe.remove(recipeIndex);
    }
}
