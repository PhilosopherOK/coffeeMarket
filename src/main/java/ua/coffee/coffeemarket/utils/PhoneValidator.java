package ua.coffee.coffeemarket.utils;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final String PHONE_PATTERN = "^380(50|66|95|99|63|93|67|96|97|98|68|39|91|92|94|89)[0-9]{7}$";
    private static final Pattern pattern = Pattern.compile(PHONE_PATTERN);

    public static boolean validate(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return pattern.matcher(phoneNumber).matches();
    }
}
