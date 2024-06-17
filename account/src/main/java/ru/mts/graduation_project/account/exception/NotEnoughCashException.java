package ru.mts.graduation_project.account.exception;

/**
 * Исключение, выбрасываемое в случае недостаточного количесва средств на банковском счету.
 */
public class NotEnoughCashException extends AccountException {
    public NotEnoughCashException(String s) {
        super(s);
    }
}
