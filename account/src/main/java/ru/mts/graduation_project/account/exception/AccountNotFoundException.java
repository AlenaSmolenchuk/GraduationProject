package ru.mts.graduation_project.account.exception;

/**
 * Исключение, выбрасываемое в случае не нахождения банковского счета.
 */
public class AccountNotFoundException extends AccountException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
