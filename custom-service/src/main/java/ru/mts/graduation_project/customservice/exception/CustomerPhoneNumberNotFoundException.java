package ru.mts.graduation_project.customservice.exception;

/**
 * Исключение, выбрасываемое в случае не нахождения номера телефона (username) пользователя.
 */
public class CustomerPhoneNumberNotFoundException extends CustomerException {
    public CustomerPhoneNumberNotFoundException(String s) {
        super(s);
    }
}
