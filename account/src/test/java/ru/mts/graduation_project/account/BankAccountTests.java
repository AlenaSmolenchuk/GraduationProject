package ru.mts.graduation_project.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.mts.graduation_project.account.entity.BankAccount;
import ru.mts.graduation_project.account.exception.AccountNotFoundException;
import ru.mts.graduation_project.account.exception.IllegalMoneyTypeException;
import ru.mts.graduation_project.account.exception.NotEnoughCashException;
import ru.mts.graduation_project.account.repository.BankAccountRepository;
import ru.mts.graduation_project.account.service.BankAccountService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class BankAccountTests {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
        account.setNumBankAccount(BigDecimal.valueOf(12345));
        account.setAmount(BigDecimal.valueOf(1000));
        bankAccountRepository.save(account);
    }

    @Test
    void testRefill() {
        BigDecimal refillAmount = BigDecimal.valueOf(500);
        BankAccount updatedAccount = bankAccountService.refill(
                account.getIdBankAccount(),
                refillAmount);

        assertNotNull(updatedAccount);
        assertEquals(BigDecimal.valueOf(1500), updatedAccount.getAmount());
    }

    @Test
    void testWithdraw() {
        BigDecimal withdrawAmount = BigDecimal.valueOf(500);
        BankAccount updatedAccount = bankAccountService.withdraw(
                account.getIdBankAccount(),
                withdrawAmount);

        assertNotNull(updatedAccount);
        assertEquals(BigDecimal.valueOf(500), updatedAccount.getAmount());
    }

    @Test
    void testRefillWithNullAmount() {
        assertThrows(IllegalMoneyTypeException.class,
                () -> bankAccountService.refill(account.getIdBankAccount(), null));
    }

    @Test
    void testRefillWithNegativeAmount() {
        assertThrows(IllegalMoneyTypeException.class,
                () -> bankAccountService.refill(account.getIdBankAccount(), BigDecimal.valueOf(-100)));
    }

    @Test
    void testWithdrawWithNullAmount() {
        assertThrows(IllegalMoneyTypeException.class,
                () -> bankAccountService.withdraw(account.getIdBankAccount(), null));
    }

    @Test
    void testWithdrawWithNegativeAmount() {
        assertThrows(IllegalMoneyTypeException.class,
                () -> bankAccountService.withdraw(account.getIdBankAccount(), BigDecimal.valueOf(-100)));
    }

    @Test
    void testWithdrawWithInsufficientFunds() {
        BigDecimal withdrawAmount = BigDecimal.valueOf(1500);
        assertThrows(NotEnoughCashException.class,
                () -> bankAccountService.withdraw(account.getIdBankAccount(), withdrawAmount));
    }

    @Test
    void testAccountNotFound() {
        assertThrows(AccountNotFoundException.class,
                () -> bankAccountService.refill(999, BigDecimal.valueOf(100)));
        assertThrows(AccountNotFoundException.class,
                () -> bankAccountService.withdraw(999, BigDecimal.valueOf(100)));
    }
}
