import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static ArrayList<recipe> rec = new ArrayList<recipe>();
    private Validator validator = new Validator();
    Ingredients ingredients = new Ingredients();
    Steps step = new Steps();
    Scanner sc = new Scanner(System.in);

    public Main() {
        int opt = 0;
        do {
            try {
                System.out.println("\n");
                System.out.println("*************************************");
                System.out.println("*    WELCOME TO THE RECIPE SYSTEM   *");
                System.out.println("*      Explore, Cook, and Enjoy!    *");
                System.out.println("*************************************");
                System.out.println("===========");
                System.out.println("1. Create New");
                System.out.println("2. See All");
                System.out.println("3. Update Recipe");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.print(">");
                opt = sc.nextInt();
                sc.nextLine();

                switch (opt) {
                    case 1:
                        addRecipe();
                        break;
                    case 2:
                        seeAll();
                        break;
                    case 3:
                        updateRecipe();
                        break;
                    case 4:
                        deleteRecipe();
                        break;
                    case 5:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter a valid option.");
                sc.nextLine();
            }
        } while (opt != 5);
    }

    private void addRecipe() {
        String inputName;
        inputName = inputName();

        String inputCat;
        inputCat = inputCat();

        String inputType = null;
        String inputVegan = null;
        if (inputCat.equals("Food")) {
            inputType = inputTypeFood();
            inputVegan = inputVegan();

        } else if (inputCat.equals("Beverage")) {
            inputType = inputTypeBeverage();
        }

        String inputprepTime;
        inputprepTime = inputPrepTime();

        String inputcookTime;
        inputcookTime = inputCookTime();

        inputListIngrePerRecipe();

        inputListStepsPerRecipe();

        String inputID = generateID(inputCat);

        if (inputCat.equalsIgnoreCase("Food")) {
            rec.add(new food(inputID, inputName, inputCat, inputType, inputVegan, inputprepTime, inputcookTime));
        } else if (inputCat.equalsIgnoreCase("Beverage")) {
            rec.add(new beverage(inputID, inputName, inputCat, inputType, inputprepTime, inputcookTime));
        }

        System.out.println("========================");
        System.out.println(" Add new recipe success!");
        System.out.println("========================");
        System.out.println("Press ENTER to continue...");
        sc.nextLine();
    }

    private void seeAll() {
        if (rec.isEmpty()) {
            displayNoData();
        } else {
            displayAll();

            String inputView;
            inputView = inputID();

            recipe obj1 = getRecipeById(inputView);
            if (obj1 != null) {
                System.out.println("Here is the detail!");
                System.out.println();
                displayRecipeDetails(obj1);
            } else {
                displayCodeWrong();
            }
        }
    }

    private void updateRecipe() {
        String updateID = null;

        if (rec.isEmpty()) {
            displayNoData();
        } else {
            displayAll();
        }

        do {
            updateID = inputID();
            if (getRecipeById(updateID) == null) {
                break;
            }
            recipe obj = getRecipeById(updateID);
            if (obj == null) displayCodeWrong();
            else {
                int opt;
                do {
                    displayRecipeDetails(obj);
                    opt = displayMenuUpdate(obj);
                    sc.nextLine();

                    switch (opt) {
                        case 1:
                            String updateName = inputName();
                            obj.setName(updateName);
                            displayUpdate();
                            break;
                        case 2:
                            if (obj instanceof food) {
                                String updateFoodType = inputTypeFood();
                                ((food) obj).setType(updateFoodType);
                                displayUpdate();
                            } else if (obj instanceof beverage) {
                                String updateBeverageType = inputTypeBeverage();
                                ((beverage) obj).setType(updateBeverageType);
                                displayUpdate();
                            }
                            break;
                        case 3:
                            String updatePrepTime = inputPrepTime();
                            obj.setPrepTime(updatePrepTime);
                            displayUpdate();
                            break;
                        case 4:
                            String updateCookTime = inputCookTime();
                            obj.setCookTime(updateCookTime);
                            displayUpdate();
                            break;
                        case 5:
                            updateIngredients(obj);
                            displayUpdate();
                            break;
                        case 6:
                            updateSteps(obj);
                            displayUpdate();
                            break;
                        case 7:
                            if (obj instanceof food) {
                                String updateVegan = inputVegan();
                                ((food) obj).setVegan(updateVegan);
                                displayUpdate();
                            }
                        default:
                            break;
                    }
                } while (opt != 0);
            }
        } while (validator.isValidFormatID(updateID));
    }

    private int displayMenuUpdate(recipe obj) {
        int opt;
        System.out.println();
        System.out.println("What do you want to change");
        System.out.println();
        System.out.println("1. Recipe Name");
        System.out.println("2. Type");
        System.out.println("3. Preparation Time");
        System.out.println("4. Cook Time");
        System.out.println("5. Ingredients");
        System.out.println("6. Steps");

        if (obj instanceof food) {
            System.out.println("7. Vegan/Non Vegan");
        }

        System.out.println("0. Back to Main");
        System.out.print(">");
        opt = sc.nextInt();
        return opt;
    }

    private void deleteRecipe() {
        if (rec.isEmpty()) displayNoData();
        else {
            displayAll();

            String deleteID;
            deleteID = inputID();

            int dataIndex = findDeleteIndex(deleteID);
            if (dataIndex != -1) {
                rec.remove(dataIndex);
                ingredients.removIngreList(dataIndex);
                step.removIngreList(dataIndex);

                System.out.println("================");
                System.out.println(" Delete success!");
                System.out.println("================");
                System.out.println("Press ENTER to continue...");
                sc.nextLine();
            } else {
                displayCodeWrong();
            }
        }
    }

    private String inputID() {
        String updateCode;
        recipe obj;
        do {
            System.out.print("Input ID [0 for back to Menu]: ");
            updateCode = sc.nextLine();
            obj = getRecipeById(updateCode);
            if (updateCode.equals("0")) {
                new Main();
                break;
            }
        } while (validator.isValidFormatID(updateCode) == false || obj == null);
        return updateCode;
    }

    private void inputListStepsPerRecipe() {
        ArrayList<String> listStepsPerRecipe = new ArrayList<>();
        while (true) {
            System.out.println("Enter steps [Input '0' if done]: ");
            String addSteps = sc.nextLine();
            if (addSteps.equals("0")) {
                break;
            }
            listStepsPerRecipe.add(addSteps);
        }
        step.addNewStepsList(listStepsPerRecipe);
    }

    private void inputListIngrePerRecipe() {
        ArrayList<String> listIngrePerRecipe = new ArrayList<>();
        while (true) {
            System.out.println("Enter ingredients [Input '0' if done]: ");
            String addIngre = sc.nextLine();
            if (addIngre.equals("0")) {
                break;
            }
            listIngrePerRecipe.add(addIngre);
        }
        ingredients.addNewIngreList(listIngrePerRecipe);
    }

    private String inputCookTime() {
        String inputcookTime;
        do {
            System.out.print("Cooking Time (must end with [sec | min | hours]): ");
            inputcookTime = sc.nextLine();
        } while (!(validator.isValidTime(inputcookTime)));
        return inputcookTime;
    }

    private String inputPrepTime() {
        String inputprepTime;
        do {
            System.out.print("Preparation Time (must end with [sec | min | hours]): ");
            inputprepTime = sc.nextLine();
        } while (!(validator.isValidTime(inputprepTime)));
        return inputprepTime;
    }

    private String inputTypeBeverage() {
        String inputTypeB;
        do {
            System.out.print("Choose type [Soda | Tea | Coffee | Fruits]: ");
            inputTypeB = sc.nextLine();
        } while (!(validator.isValidTypeBeverage(inputTypeB)));
        return inputTypeB;
    }

    private String inputVegan() {
        String inputVegan;
        do {
            System.out.print("Vegan | Non Vegan (Sensitive Case): ");
            inputVegan = sc.nextLine();
        } while (!(validator.isValidVegan(inputVegan)));
        return inputVegan;
    }

    private String inputTypeFood() {
        String inputTypeF;
        do {
            System.out.print("[Desserts | Main Course](Case Sensitive): ");
            inputTypeF = sc.nextLine();
        } while (!(validator.isValidType(inputTypeF)));
        return inputTypeF;
    }

    private String inputCat() {
        String inputCat;
        do {
            System.out.print("Choose Category [Food | Beverage]: ");
            inputCat = sc.nextLine();
        } while (!(validator.isValidCat(inputCat)));
        return inputCat;
    }

    private String inputName() {
        String inputName;
        do {
            System.out.print("Recipe Name [>=3]: ");
            inputName = sc.nextLine();
        } while (!(validator.isValidName(inputName)));
        return inputName;
    }

    private void updateIngredients(recipe obj) {
        int indexRecipe = rec.indexOf(obj);

        int opt;
        do {
            int array_size = ingredients.getRecipeIngredients(indexRecipe).size();
            System.out.println();
            ingredients.displayRecipeIngredients(indexRecipe);
            opt = displayMenuUpdateInner();
            sc.nextLine();

            switch (opt) {
                case 1: // Change ingredients
                    updateInnerIngreList(indexRecipe, array_size);
                    break;

                case 2: // Input new step
                    inputInnerIngreList(indexRecipe);
                    break;

                case 3: // Delete step
                    deleteInnerIngreList(indexRecipe, array_size);
                    break;

                case 0: // Back to update menu
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (opt != 0);
    }

    private void updateSteps(recipe obj) {
        int indexRecipe = rec.indexOf(obj);

        int opt;
        do {
            int array_size = step.getRecipeSteps(indexRecipe).size();
            System.out.println();
            step.displayRecipeSteps(indexRecipe);

            opt = displayMenuUpdateInner();
            sc.nextLine();

            switch (opt) {
                case 1: // Change step
                    updateInnerStepsList(indexRecipe, array_size);
                    break;

                case 2: // Input new step
                    inputInnerStepsList(indexRecipe);
                    break;

                case 3: // Delete step
                    deleteInnerStepsList(indexRecipe, array_size);
                    break;

                case 0: // Back to update menu
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (opt != 0);
    }

    private int displayMenuUpdateInner() {
        int opt;
        System.out.println();
        System.out.println("1. Change");
        System.out.println("2. Input new");
        System.out.println("3. Delete");
        System.out.println("0. Back to update menu");
        System.out.print(">");
        opt = sc.nextInt();
        return opt;
    }

    private void inputInnerIngreList(int indexRecipe) {
        String newIngre;
        System.out.print("Enter new ingredients: ");
        newIngre = sc.nextLine();

        ingredients.addIngretotheList(newIngre, indexRecipe);
        displayUpdate();
    }

    private void updateInnerIngreList(int indexRecipe, int array_size) {
        int chooseIngre;
        do {
            System.out.print("Choose ingredients that want to be changed:");
            chooseIngre = sc.nextInt();
            sc.nextLine();
        } while ((validator.chooseValid(chooseIngre, array_size)));

        String updateIngre;
        System.out.print("Enter updated ingredients: ");
        updateIngre = sc.nextLine();

        ingredients.setIngreInnerList(indexRecipe, chooseIngre - 1, updateIngre);
        displayUpdate();
    }

    private void deleteInnerIngreList(int indexRecipe, int array_size) {
        int indexDeleteIngreList;
        do {
            System.out.println("Enter number of ingredient that want to be deleted:");
            indexDeleteIngreList = sc.nextInt();
        } while ((validator.chooseValid(indexDeleteIngreList, array_size)));

        ingredients.removeInnerList(indexRecipe, indexDeleteIngreList - 1);
        displayUpdate();
    }

    private void inputInnerStepsList(int indexRecipe) {
        String newStep;
        System.out.print("Enter new ingredients: ");
        newStep = sc.nextLine();

        step.addSteptotheList(newStep, indexRecipe);
        displayUpdate();
    }

    private void deleteInnerStepsList(int indexRecipe, int array_size) {
        int indexDeleteStepList;
        do {
            System.out.println("Enter number of step that want to be deleted:");
            indexDeleteStepList = sc.nextInt();
        } while ((validator.chooseValid(indexDeleteStepList, array_size)));

        step.removeInnerList(indexRecipe, indexDeleteStepList - 1);
        displayUpdate();
    }

    private void updateInnerStepsList(int indexRecipe, int array_size) {
        int chooseStep;
        do {
            System.out.print("Choose steps that want to be changed:");
            chooseStep = sc.nextInt();
            sc.nextLine();
        } while ((validator.chooseValid(chooseStep, array_size)));

        String updateStep;
        System.out.print("Enter updated steps: ");
        updateStep = sc.nextLine();

        step.setStepInnerList(indexRecipe, chooseStep - 1, updateStep);
        displayUpdate();
    }

    public void displayAll() {
        System.out.println("\n");
        System.out.println("Food:");
        System.out.println("===========================================================");
        System.out.printf("%6s | %10s |%10s | %10s | %10s | %n", "ID", "Nama", "Category", "Time Prep", "Cook Time");
        System.out.println("===========================================================");

        for (recipe recipess : rec) {
            if (recipess instanceof food) {
                System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());

            }
        }

        System.out.println("\n");
        System.out.println("Beverage:");
        System.out.println("===========================================================");
        System.out.printf("%6s | %10s |%10s | %10s | %10s | %n", "ID", "Nama", "Category", "Time Prep", "Cook Time");
        System.out.println("===========================================================");

        for (recipe recipess : rec) {
            if (recipess instanceof beverage) {
                System.out.printf("%6s | %10s | %10s | %10s | %10s\n", recipess.ID, recipess.getName(), recipess.getCategory(), recipess.getPrepTime(), recipess.getCookTime());
            }
        }
        System.out.println("\n");
    }

    public void displayCodeWrong() {
        System.out.println("================");
        System.out.println(" Code is wrong! ");
        System.out.println("================");
        System.out.println("Press ENTER to continue...");
        sc.nextLine();
    }

    public void displayNoData() {
        System.out.println("===================");
        System.out.println(" There is no data! ");
        System.out.println("===================");
        System.out.println("Press ENTER to continue...");
        sc.nextLine();
    }

    public void displayUpdate() {
        System.out.println("===================");
        System.out.println(" Update success! ");
        System.out.println("===================");
        System.out.println("Press ENTER to continue...");
        sc.nextLine();
    }

    private void displayRecipeDetails(recipe obj) {
        int indexRecipe = rec.indexOf(obj);
        System.out.println(indexRecipe);
        System.out.println("Recipe Name: " + obj.getName());
        System.out.println("Category: " + obj.getCategory());
        if (obj instanceof food) {
            food food = (food) obj;
            System.out.println("Type: " + food.getType());
            System.out.println("Vegan / Non Vegan: " + food.getVegan());
        } else if (obj instanceof beverage) {
            beverage beverage = (beverage) obj;
            System.out.println("Type: " + beverage.getType());
        }
        System.out.println("Preparation Time: " + obj.getPrepTime());
        System.out.println("Cook Time: " + obj.getCookTime());

        ingredients.displayRecipeIngredients(indexRecipe);
        step.displayRecipeSteps(indexRecipe);
    }

    public recipe getRecipeById(String id) {
        for (recipe r : rec) {
            if (r.getID().equals(id)) {
                return r;
            }
        }
        return null;
    }

    private String generateID(String cat) {
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        int z = (int) (Math.random() * 10);
        return (cat.equalsIgnoreCase("Food") ? "F" : "B") + x + y + z;
    }

    private int findDeleteIndex(String deleteID) {
        for (int i = 0; i < rec.size(); i++) {
            if (rec.get(i).getID().equals(deleteID)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new Main();
        System.out.println("===============================");
        System.out.println("          Thank You ! ");
        System.out.println("===============================");
    }
}