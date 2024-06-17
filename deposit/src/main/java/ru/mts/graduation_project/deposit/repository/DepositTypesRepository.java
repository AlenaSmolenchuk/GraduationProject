package ru.mts.graduation_project.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.deposit.entity.DepositTypes;

/**
 * Репозиторий для работы с типами депозитов.
 */
@Repository
public interface DepositTypesRepository extends JpaRepository<DepositTypes, Integer> {
}
