package ru.mts.graduation_project.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.deposit.entity.Deposit;

import java.util.List;

/**
 * Репозиторий для работы с депозитами.
 */
@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    /**
     * Найти депозит по его идентификатору.
     *
     * @param id Идентификатор депозита.
     * @return Optional, содержащий найденный депозит, или пустой Optional, если депозит не найден.
     */
    Deposit findByIdDeposit(Integer id);

    /**
     * Найти депозит по идентификатору банковского счета.
     *
     * @param id Идентификатор банковского счета.
     * @return Optional, содержащий найденный депозит, или пустой Optional, если депозит не найден.
     */
    List<Deposit> findByDepositAccountId(Integer id);

    /**
     * Удалить депозит по его идентификатору.
     *
     * @param id Идентификатор депозита для удаления.
     */
    void deleteByIdDeposit(Integer id);

}
