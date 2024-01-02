package ru.job4j.early;

/**
 * Создать класс PasswordValidator, который занимается проверкой пароля:
 */
public class PasswordValidator {
    private static final String[] FORBIDDEN = {"qwerty", "12345", "password", "admin", "user"};
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 32;

    /**
     * Метод проверят пароль по правилам:
     * 1. Если password null, то выбросить исключение "Password can't be null";
     * 2. Длина пароля находится в диапазоне [8, 32], если нет то "Password should be length [8, 32]";
     * 3. Пароль содержит хотя бы один символ в верхнем регистре, если нет то "Password should contain at least one uppercase letter");
     * 4. Пароль содержит хотя бы один символ в нижнем регистре, если нет то "Password should contain at least one lowercase letter";
     * 5. Пароль содержит хотя бы одну цифру, если нет то"Password should contain at least one figure");
     * 6. Пароль содержит хотя бы один спец. символ (не цифра и не буква), если нет то "Password should contain at least one special symbol");
     * 7. Пароль не содержит подстрок без учета регистра: qwerty, 12345, password, admin, user.
     * Без учета регистра, значит что, например, ни qwerty ни QWERTY ни qwErty и т.п.
     * если да, то "Password shouldn't contain substrings: qwerty, 12345, password, admin, user".
     *
     * @param password Пароль
     * @return Вернет пароль или выбросит исключение.
     */
    public static String validate(String password) {
        checkPasswordForNull(password);
        checkPasswordLength(password);
        checkPasswordRules(password);
        checkPasswordForbiddenWord(password);

        return password;
    }

    private static void checkPasswordForbiddenWord(String password) {
        for (String forbiddenWord : FORBIDDEN) {
            if (password.toLowerCase().contains(forbiddenWord)) {
                throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
            }
        }
    }

    /**
     * проверка пароля на соответствие правилам.
     * в нем должны содержаться каждого хотябы по 1 символу Заглавный, строчный, число, спецсимвол.
     *
     * @param password - пароль
     */
    private static void checkPasswordRules(String password) {
        boolean hasUpCase = false;
        boolean hasLowCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char symbol : password.toCharArray()) {
            if (Character.isDigit(symbol)) {
                hasDigit = true;
            } else if (Character.isLowerCase(symbol)) {
                hasLowCase = true;
            } else if (Character.isUpperCase(symbol)) {
                hasUpCase = true;
            } else {
                hasSpecial = true;
            }
        }

        if (!hasUpCase) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!hasLowCase) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!hasDigit) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!hasSpecial) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
    }

    /**
     * проверка пароля на null
     *
     * @param password - пароль
     */
    private static void checkPasswordForNull(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
    }

    /**
     * проверка пароля на длину [MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH].
     *
     * @param password - пароль
     */
    private static void checkPasswordLength(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("Password should be length [%d, %d]",
                            MIN_PASSWORD_LENGTH,
                            MAX_PASSWORD_LENGTH));
        }
    }
}