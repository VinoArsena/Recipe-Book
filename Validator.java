import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean isValidName(String name) {
        return name.length() >= 3;
    }

    public boolean isValidFormatID(String code) {
        return code.length() == 4 && (code.charAt(0) == 'F' || code.charAt(0) == 'B');
    }

    public boolean isValidCat(String cat) {
        return cat.equals("Food") || cat.equals("Beverage");
    }

    public boolean isValidTime(String time) {
        Pattern pattern = Pattern.compile("\\d+ (sec|min|hours|hour)");
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public boolean isValidVegan(String vegan) {
        return vegan.equals("Vegan") || vegan.equals("Non Vegan");
    }

    public boolean isValidType(String type) {
        return type.equals("Desserts") || type.equals("Main Course");
    }

    public boolean isValidTypeBeverage(String type) {
        return type.equals("Soda") || type.equals("Tea") || type.equals("Coffee") || type.equals("Fruits");
    }

    public boolean isIndexValid(int recipeIndex, int size) {
        return recipeIndex < 0 || recipeIndex >= size;
    }

    public boolean chooseValid(int innerIndex, int size) {
        return innerIndex < 1 || innerIndex > size;
    }
}