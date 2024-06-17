package ru.mts.graduation_project.deposit.exception;

/**
 * Исключение, выбрасываемое в случае недостаточного количесва средств.
 */
public class NotEnoughMoneyException extends DepositException {
    public NotEnoughMoneyException(String s) {
        super(s);
    }
}
