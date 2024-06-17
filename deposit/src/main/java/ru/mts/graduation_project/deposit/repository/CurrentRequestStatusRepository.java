package ru.mts.graduation_project.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.deposit.entity.CurrentRequestStatus;

/**
 * Репозиторий для работы с текущим статусом запросов.
 */
@Repository
public interface CurrentRequestStatusRepository extends JpaRepository<CurrentRequestStatus, Integer> {
}
