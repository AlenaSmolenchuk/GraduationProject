package ru.mts.graduation_project.account.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.mts.graduation_project.account.entity.BankAccount;
import ru.mts.graduation_project.account.exception.AccountNotFoundException;
import ru.mts.graduation_project.account.exception.IllegalMoneyTypeException;
import ru.mts.graduation_project.account.exception.NotEnoughCashException;
import ru.mts.graduation_project.account.repository.BankAccountRepository;

import java.math.BigDecimal;

/**
 * Сервис для работы с банковскими счетами.
 */
@Log4j2
@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * Метод для получения банковского счета по его идентификатору.
     *
     * @param id Идентификатор банковского счета.
     * @throws AccountNotFoundException Если банковский счет с указанным id не найден.
     * @return Объект банковского счета с указанным идентификатором.
     */
    public BankAccount getBankAccountById(Integer id) {
        return bankAccountRepository.findByIdBankAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Банковский счет с id:" + id + " не найден"));
    }

    /**
     * Метод для пополнения банковского счета на указанную сумму.
     *
     * @param id Идентификатор банковского счета.
     * @param money Сумма пополнения.
     * @throws IllegalMoneyTypeException Если сумма пополнения пустая или неположительная.
     * @throws AccountNotFoundException Если банковский счет с указанным id не найден.
     * @return Банковский счет с обновленным балансом.
     */
    public BankAccount refill(Integer id, BigDecimal money) {
        checkMoney(money);

        BankAccount account = bankAccountRepository.findByIdBankAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Банковский счет с id:" + id + " не найден"));
        account.setAmount(account.getAmount().add(money));
        return bankAccountRepository.save(account);
    }

    /**
     * Метод для снятия средств с банковского счета.
     *
     * @param id Идентификатор банковского счета.
     * @param money Сумма, которая должна быть снята со счета.
     * @throws IllegalMoneyTypeException Если сумма снятия пустая или неположительная.
     * @throws NotEnoughCashException Если на счету недостаточно средств для снятия.
     * @throws AccountNotFoundException Если банковский счет с указанным id не найден.
     * @return Банковский счет с обновленным балансом, если средств достаточно.
     */
    public BankAccount withdraw(Integer id, BigDecimal money) {
        checkMoney(money);

        BankAccount account = bankAccountRepository.findByIdBankAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Банковский счет с id:" + id + " не найден"));
        if (account.getAmount().compareTo(money) < 0) {
            log.error("Недостаточно средств на счету для снятия {} рублей с аккаунта, ваш счет составляет: {}",
                    money,
                    account.getAmount());
            throw new NotEnoughCashException("Недостаточно средств на счету");
        } else {
            account.setAmount(account.getAmount().subtract(money));
            return bankAccountRepository.save(account);
        }
    }

    /**
     * Метод для полечения текущего баланса на банковском счету.
     *
     * @param id Идентификатор банковского счета.
     * @throws AccountNotFoundException Если банковский счет с указанным id не найден.
     * @return Банковский счет с текущим балансом.
     */
    public BigDecimal balance(Integer id) {
        BankAccount account = bankAccountRepository.findByIdBankAccount(id)
                .orElseThrow(() -> new AccountNotFoundException("Банковский счет с id:" + id + " не найден"));
        return account.getAmount();
    }

    // Вспомогательный метод проверки значения поля money
    private void checkMoney(BigDecimal money) {
        if (money == null || money.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalMoneyTypeException("Сумма транзакции должна быть положительной и непустой");
        }
    }
}
