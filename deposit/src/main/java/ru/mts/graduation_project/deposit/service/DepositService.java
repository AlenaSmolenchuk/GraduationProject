package ru.mts.graduation_project.deposit.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.mts.graduation_project.deposit.entity.Deposit;
import ru.mts.graduation_project.deposit.exception.WrongCodeException;
import ru.mts.graduation_project.deposit.jwt.JwtUtil;
import ru.mts.graduation_project.deposit.repository.DepositRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static ru.mts.graduation_project.deposit.util.constants.UtilConstants.CODE_EXPIRATION_TIME;

/**
 * Сервис для управления операциями с депозитами.
 */
@Log4j2
@Service
public class DepositService {
    private Integer generatedCode;
    private long codeGenerationTime;
    private final DepositRepository depositRepository;
    private final JwtUtil jwtUtil;

    public DepositService(DepositRepository depositRepository, JwtUtil jwtUtil) {
        this.depositRepository = depositRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Получить все депозиты пользователя.
     *
     * @param idAccount идентификатор банковского счета пользователя
     * @return Список всех депозитов пользователя.
     */
    public List<Deposit> getAll(Integer idAccount) {
        return depositRepository.findByDepositAccountId(idAccount);
    }

    /**
     * Получить депозит по его идентификатору.
     *
     * @param id Идентификатор депозита.
     * @return Optional, содержащий найденный депозит, или пустой Optional, если депозит не найден.
     */
    public Deposit getDepositById(Integer id) {
        return depositRepository.findByIdDeposit(id);
    }

    /**
     * Закрыть депозит по его идентификатору.
     *
     * @param id Идентификатор депозита для закрытия.
     */
    public void closeDeposit(Integer id, String token) {
        if (confirmOperation(sendCode(getPhoneNumber(token)))) {
            depositRepository.deleteByIdDeposit(id);
        } else {
            throw new WrongCodeException("Неверный код");
        }
    }

    /**
     * Сохранить информацию о депозите.
     *
     * @param deposit Депозит для сохранения.
     */
    public void save(Deposit deposit, String token) {
        if (confirmOperation(sendCode(getPhoneNumber(token)))) {
            depositRepository.save(deposit);
        } else {
            throw new WrongCodeException("Неверный код");
        }
    }

    /**
     * Отправить код подтверждения на указанный номер телефона.
     *
     * @param phoneNumber Номер телефона, на который отправляется код подтверждения.
     */
    public Integer sendCode(String phoneNumber) {
        generatedCode = ThreadLocalRandom.current().nextInt(1000, 9999);
        codeGenerationTime = System.currentTimeMillis();
        log.info("Код подтверждения для пользователя: {} : {}", phoneNumber, generatedCode);
        return generatedCode;
    }

    /**
     * Проверить правильность подтверждающего кода операции.
     *
     * @param code Подтверждающий код, введенный пользователем.
     * @return true, если код верный и действителен, false в противном случае.
     */
    public boolean confirmOperation(Integer code) {
        if (generatedCode != null && (System.currentTimeMillis() - codeGenerationTime) <= CODE_EXPIRATION_TIME) {
            if (generatedCode.equals(code)) {
                generatedCode = null;
                return true;
            } else {
                log.info("Неверный код подтверждения");
                return false;
            }
        } else {
            log.info("Срок действия кода истек");
            return false;
        }
    }

    public String getPhoneNumber(String token) {
        return jwtUtil.extractPhoneNumber(token);
    }

    //    /**
//     * Пополнить депозит.
//     */
//    public void refill(Integer id, BigDecimal money) {
//        Deposit depositToRefill = depositRepository.findById(id).get();
//        AccountDTO accountDTO = depositClient.fetchAccount(id);
//        if (accountDTO.getAmount().compareTo(money) < 0) {
//            log.error("Недостаточно средств на счету для пополнения на {} рублей  вклада {}",
//                    money,
//                    id);
//            throw new NotEnoughMoneyException("Недостаточно средств на счету");
//        } else {
//            depositToRefill.setDepositAmount(accountDTO.getAmount().add(money));
//            depositRepository.save(depositToRefill);
//        }
//    }
//
//    /**
//     * Снять средства с депозита.
//     */
//    public void withdraw(Integer id, BigDecimal money) {
//        Deposit depositToRefill = depositRepository.findById(id).get();
//        AccountDTO accountDTO = depositClient.fetchAccount(id);
//        if (accountDTO.getAmount().compareTo(money) < 0) {
//            log.error("Недостаточно средств на счету для пополнения на {} рублей  вклада {}",
//                    money,
//                    id);
//            throw new NotEnoughMoneyException("Недостаточно средств на счету");
//        } else {
//            depositToRefill.setDepositAmount(accountDTO.getAmount().subtract(money));
//            depositRepository.save(depositToRefill);
//        }
//    }
}

