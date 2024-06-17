package ru.mts.graduation_project.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.graduation_project.deposit.entity.RequestStatuses;

/**
 * Репозиторий для работы со статусами запросов.
 */
@Repository
public interface RequestStatusesRepository extends JpaRepository<RequestStatuses, Integer> {
}
