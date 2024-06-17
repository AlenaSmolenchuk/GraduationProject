package ru.mts.graduation_project.deposit.controller;

import org.springframework.web.bind.annotation.*;
import ru.mts.graduation_project.deposit.annotation.Logging;
import ru.mts.graduation_project.deposit.entity.Deposit;
import ru.mts.graduation_project.deposit.service.DepositService;

import java.util.List;

/**
 * Контроллер для управления  депозитами.
 */
@RestController
@RequestMapping("/api/deposits")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    /**
     * Метод для получения депозитов по идентификатору банковского счета.
     *
     * @param idAccount айди банковского счета.
     * @return Объект депозита с указанным идентификатором.
     */
    @Logging(value = "Получение всех депозитов по идентификатору счета",
            logParams = true,
            logResult = true,
            exit = true,
            enter = true)
    @GetMapping("/getByAccount/{idAccount}")
    public List<Deposit> getAllByIdAccount(@PathVariable Integer idAccount) {
        return depositService.getAll(idAccount);
    }

    /**
     * Метод для получения депозита по его идентификатору.
     *
     * @param id Идентификатор депозита.
     * @return Объект депозита с указанным идентификатором.
     */
    @Logging(value = "Получение депозита по его идентификатору",
            logParams = true,
            logResult = true,
            exit = true,
            enter = true)
    @GetMapping("/{id}")
    public Deposit getOne(@PathVariable Integer id) {
        return depositService.getDepositById(id);
    }

    @PostMapping("/create")
    public void createNew(@RequestHeader("Authorization") String token, @RequestBody Deposit deposit) {
        depositService.save(deposit, token);
    }

    @GetMapping("/close/{id}")
    public void close(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        depositService.closeDeposit(id, token);
    }
}
