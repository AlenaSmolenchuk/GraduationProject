package ru.mts.graduation_project.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.account.entity.BankAccount;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Репозиторий для работы с банковскими счетами в базе данных.
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    /**
     * Метод для поиска банковского счета по его идентификатору.
     *
     * @param id Идентификатор банковского счета.
     * @return Объект банковского счета с указанным идентификатором.
     */
    Optional<BankAccount> findByIdBankAccount(Integer id);

}
