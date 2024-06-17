package ru.mts.graduation_project.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.deposit.entity.TypesPercentPayment;

/**
 * Репозиторий для работы с типами процентных выплат.
 */
@Repository
public interface TypesPercentPaymentRepository extends JpaRepository<TypesPercentPayment, Integer> {
}
