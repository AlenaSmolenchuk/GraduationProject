package ru.mts.graduation_project.account.exception;

/**
 * Исключение, выбрасываемое в случае неправильного значения денежных средств.
 */
public class IllegalMoneyTypeException extends AccountException {
    public IllegalMoneyTypeException(String message) {
        super(message);
    }
}
