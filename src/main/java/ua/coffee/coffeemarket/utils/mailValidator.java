package ua.coffee.coffeemarket.utils;
import org.apache.commons.validator.EmailValidator;

public class mailValidator {
    public static boolean isValidEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
