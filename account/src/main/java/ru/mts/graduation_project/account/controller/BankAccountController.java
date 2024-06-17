package ru.mts.graduation_project.account.controller;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.graduation_project.account.annotation.Logging;
import ru.mts.graduation_project.account.entity.BankAccount;
import ru.mts.graduation_project.account.service.BankAccountService;

import java.math.BigDecimal;

/**
 * Контроллер для управления банковскими счетами.
 */
@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /**
     * Метод для получения банковского счета по его идентификатору.
     *
     * @param id айди банковского счета.
     * @return Объект банковского счета с указанным идентификатором.
     */
    @Logging(value = "Получение счета по идентификатору",
    logParams = true,
    logResult = true,
    exit = true,
    enter = true)
    @GetMapping("/{id}")
    public BankAccount getAccount(@PathVariable  Integer id) {
        return bankAccountService.getBankAccountById(id);
    }

    /**
     * Метод для пополнения банковского счета на указанную сумму.
     *
     * @param id айди банковского счета, на который осуществляется пополнение.
     * @param amount Сумма пополнения.
     */
    @Logging(value = "Пополнение счета",
            logParams = true,
            logResult = true,
            exit = true,
            enter = true)
    @SneakyThrows
    @PostMapping("/{id}/refill")
    public ResponseEntity<BankAccount> refill(@RequestHeader("Authorization") String token,
                                              @PathVariable Integer id,
                                              @RequestParam BigDecimal amount) {
        BankAccount account = bankAccountService.refill(id, amount);
        return ResponseEntity.ok(account);
    }

    /**
     * Метод для снятия средств с банковского счета.
     *
     * @param id айди банковского счета, с которого осуществляется снятие средств.
     * @param amount Сумма, которая должна быть снята со счета.
     */
    @Logging(value = "Снятие денег со счета",
            logParams = true,
            logResult = true,
            exit = true,
            enter = true)
    @SneakyThrows
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<BankAccount> withdraw(@RequestHeader("Authorization") String token,
                                                @PathVariable Integer id,
                                                @RequestParam BigDecimal amount) {
        BankAccount account = bankAccountService.withdraw(id, amount);
        return ResponseEntity.ok(account);
    }

    /**
     * Метод для полечения текущего баланса на банковском счету.
     *
     * @param id Идентификатор банковского счета.
     */
    @Logging(value = "Получение текущего баланса",
            logParams = true,
            logResult = true,
            exit = true,
            enter = true)
    @SneakyThrows
    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> balance(@RequestHeader("Authorization") String token,
                                              @PathVariable Integer id) {
        BigDecimal balance = bankAccountService.balance(id);
        return ResponseEntity.ok(balance);
    }
}
